package Piece;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Pawn extends Piece {
    private Position position;
    private int value;
    private int player;
    private String name;
    private BufferedImage image;
    private boolean startingPos;
    private boolean hasMoved;

    public Pawn(Position position, int player) {
        this.value = 1;
        this.player = player;
        this.position = position;
        this.name = "Pawn";
        this.startingPos = true;
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
    
    @Override
    public BufferedImage getImage(){
        return this.image;     
    }
    
    @Override
    public boolean hasMoved() {
        return this.hasMoved;
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
        return new Pawn(this.position.clone(), this.player);
    }

    @Override
    public void updatePos(Position pos) {
        position = pos;
    }

    @Override
    public ArrayList<Position> getAttackingMoves(BoardSquare[][] board) {
        ArrayList<Position> output = new ArrayList<Position>();
        int[] dx = new int[] { 0, 1, 0, -1 };
        int[] dy = new int[] { 1, 0, -1, 0 };

        // diagonal capture
        dx = new int[] { -1, 1, 1, -1 };
        dy = new int[] { 1, 1, -1, -1 };
        if (!board[position.getX() + dx[player]][position.getY() + dy[player]].isBlank()
                && board[position.getX() + dx[player]][position.getY() + dy[player]].getPlayer() != player
                && !board[position.getX() + dx[player]][position.getY() + dy[player]].isNull())
            output.add(new Position(position.getX() + dx[player], position.getY() + dy[player]));

        dx = new int[] { 1, 1, -1, -1 };
        dy = new int[] { 1, -1, -1, 1 };
        if (!board[position.getX() + dx[player]][position.getY() + dy[player]].isBlank()
                && board[position.getX() + dx[player]][position.getY() + dy[player]].getPlayer() != player
                && !board[position.getX() + dx[player]][position.getY() + dy[player]].isNull())
            output.add(new Position(position.getX() + dx[player], position.getY() + dy[player]));

        return output;
    }

    @Override
    public ArrayList<Position> getValidMoves(BoardSquare[][] board) {
        ArrayList<Position> output = new ArrayList<Position>();
        int[] dx = new int[] { 0, 1, 0, -1 };
        int[] dy = new int[] { 1, 0, -1, 0 };
        if (board[position.getX() + dx[player]][position.getY() + dy[player]].isBlank()) {
            output.add(new Position(position.getX() + dx[player], position.getY() + dy[player]));
            if (startingPos && board[position.getX() + dx[player] * 2][position.getY() + dy[player] * 2].isBlank())
                output.add(new Position(position.getX() + dx[player] * 2, position.getY() + dy[player] * 2));
        }

        // diagonal capture
        dx = new int[] { -1, 1, 1, -1 };
        dy = new int[] { 1, 1, -1, -1 };
        if (!board[position.getX() + dx[player]][position.getY() + dy[player]].isBlank()
                && board[position.getX() + dx[player]][position.getY() + dy[player]].getPlayer() != player
                && !board[position.getX() + dx[player]][position.getY() + dy[player]].isNull())
            output.add(new Position(position.getX() + dx[player], position.getY() + dy[player]));

        dx = new int[] { 1, 1, -1, -1 };
        dy = new int[] { 1, -1, -1, 1 };
        if (!board[position.getX() + dx[player]][position.getY() + dy[player]].isBlank()
                && board[position.getX() + dx[player]][position.getY() + dy[player]].getPlayer() != player
                && !board[position.getX() + dx[player]][position.getY() + dy[player]].isNull())
            output.add(new Position(position.getX() + dx[player], position.getY() + dy[player]));

        return output;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public String getName() {
        return "Pawn";
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
            if (move.equals(position))
                return true;
        }
        return false;
    }

    @Override
    public int getPlayer() {
        return this.player;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public void move(BoardSquare[][] board) {
        this.startingPos = false;
        this.hasMoved = true;
    }
}
