package Piece;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Queen extends Piece {
    private Position position;
    private int value;
    private int player;
    private String name;
    private BufferedImage image;
    private boolean hasMoved;

    public Queen(Position position, int player) {
        this.value = 9;
        this.player = player;
        this.position = position;
        this.name = "Queen";
        this.hasMoved = false;

        String[] teams = new String[] { "Blue", "Green", "Red", "Yellow" };
        String path = "Assets" + "/" + teams[player] + "/" + this.name + ".png";
        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Failed");
        }
        this.image = super.resize(image);
    }

    public Queen(Position position, int player, int points) {
        this.value = points;
        this.player = player;
        this.position = position;
        this.name = "Queen";

        String[] teams = new String[] { "Blue", "Green", "Red", "Yellow" };
        String path = "Assets" + "/" + teams[player] + "/" + this.name + ".png";
        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Failed");
        }
        this.image = super.resize(image);
    }

    @Override
    public BufferedImage getImage(){
        return this.image;     
    }

    @Override
    public boolean hasMoved() {
        return this.hasMoved;
    }

    @Override
    public void move(BoardSquare[][] board) {
        this.hasMoved = true;
    }

    @Override
    public void kill() {
        String path = "Assets" + "/" + "Dead" + "/" + this.name + ".png";
        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Failed");
        }
        this.image = super.resize(image);
        this.value = 0;
    }

    @Override
    public Piece clone() {
        return new Queen(this.position.clone(), this.player);
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public String getName() {
        return "Queen";
    }

    @Override
    public void updatePos(Position pos) {
        position = pos;
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public void drawMe(Graphics g, int x, int y, boolean black, String select, int width, int height,
            BoardSquare[][] board) {
        g.drawImage(this.image, x, y, null);
    }

    @Override
    public boolean isValidMove(Position position, BoardSquare[][] board) {
        ArrayList<Position> moves = getValidMoves(board);
        for (Position move : moves) {
            if (move.getX() == position.getX() && move.getY() == position.getY())
                return true;
        }
        return false;
    }

    @Override
    public int getPlayer() {
        return this.player;
    }

    public ArrayList<Position> getValidMoves(BoardSquare[][] board) {
        ArrayList<Position> output = new ArrayList<Position>();
        int x;
        int y;

        // up
        x = this.position.getX() - 1;
        y = this.position.getY();
        while (super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].isBlank()) {
            output.add(new Position(x, y));
            x--;
        }
        if (super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].getPlayer() != player)
            output.add(new Position(x, y));

        // down
        x = this.position.getX() + 1;
        y = this.position.getY();
        while (super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].isBlank()) {
            output.add(new Position(x, y));
            x++;
        }
        if (super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].getPlayer() != player)
            output.add(new Position(x, y));

        // right
        x = this.position.getX();
        y = this.position.getY() + 1;
        while (super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].isBlank()) {
            output.add(new Position(x, y));
            y++;
        }
        if (super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].getPlayer() != player)
            output.add(new Position(x, y));

        // left
        x = this.position.getX();
        y = this.position.getY() - 1;
        while (super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].isBlank()) {
            output.add(new Position(x, y));
            y--;
        }
        if (super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].getPlayer() != player)
            output.add(new Position(x, y));

        // diagonal
        x = this.position.getX() + 1;
        y = this.position.getY() + 1;
        while (super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].isBlank()) {
            output.add(new Position(x, y));
            x++;
            y++;
        }
        if (super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].getPlayer() != player)
            output.add(new Position(x, y));

        x = this.position.getX() + 1;
        y = this.position.getY() - 1;
        while (super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].isBlank()) {
            output.add(new Position(x, y));
            x++;
            y--;
        }
        if (super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].getPlayer() != player)
            output.add(new Position(x, y));

        x = this.position.getX() - 1;
        y = this.position.getY() + 1;
        while (super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].isBlank()) {
            output.add(new Position(x, y));
            x--;
            y++;
        }
        if (super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].getPlayer() != player)
            output.add(new Position(x, y));

        x = this.position.getX() - 1;
        y = this.position.getY() - 1;
        while (super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].isBlank()) {
            output.add(new Position(x, y));
            x--;
            y--;
        }
        if (super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].getPlayer() != player)
            output.add(new Position(x, y));

        return output;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public int getValue() {
        return this.value;
    }
}
