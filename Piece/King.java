package Piece;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class King extends Piece {
    private Position position;
    private int value;
    private int player;
    private String name;
    private BufferedImage image;
    private boolean hasMoved;

    public King(Position position, int player) {
        this.value = 3;
        this.player = player;
        this.position = position;
        this.name = "King";
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
    public boolean hasMoved() {
        return this.hasMoved;
    }

    @Override
    public void move(BoardSquare[][] board) {
        if (!this.hasMoved) {
            System.out.println("------");
            System.out.println(this.position);
            System.out.println("------");
            int[] dx;
            int[] dy;
            dx = new int[] { 4, 0, 9, 13 };
            dy = new int[] { 0, 4, 13, 9 };
            if (position.getX() == dx[player] && position.getY() == dy[player]) {// short castle
                dx = new int[] { 3, 0, 10, 13 };
                dy = new int[] { 0, 3, 13, 10 };
                Rook rook = (Rook) board[dx[player]][dy[player]].getPiece();
                board[dx[player]][dy[player]].setPiece(new BlankSquare());
                dx = new int[] { 5, 0, 8, 13 };
                dy = new int[] { 0, 5, 13, 8 };
                board[dx[player]][dy[player]].setPiece(rook);
            }
            dx = new int[] { 8, 0, 5, 13 };
            dy = new int[] { 0, 8, 13, 5 };
            if (position.getX() == dx[player] && position.getY() == dy[player]) {// long castle
                dx = new int[] { 10, 0, 3, 13 };
                dy = new int[] { 0, 10, 13, 3 };
                Rook rook = (Rook) board[dx[player]][dy[player]].getPiece();
                board[dx[player]][dy[player]].setPiece(new BlankSquare());
                dx = new int[] { 7, 0, 6, 13 };
                dy = new int[] { 0, 7, 13, 6 };
                board[dx[player]][dy[player]].setPiece(rook);
            }

        }
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

    public boolean inCheck(BoardSquare[][] board, Position position) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!board[i][j].isNull() && !board[i][j].isBlank() && board[i][j].getPlayer() != this.player
                        && !board[i][j].getName().equals("King")) {
                    ArrayList<Position> moves = board[i][j].getAttackingMoves(board);
                    for (Position move : moves) {
                        if (move.equals(position)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean inCheck(BoardSquare[][] board) {
        return this.inCheck(board, this.position.clone());
    }

    @Override
    public Piece clone() {
        return new King(this.position, this.player);
    }

    @Override
    public ArrayList<Position> getAttackingMoves(BoardSquare[][] board) {
        ArrayList<Position> output = new ArrayList<Position>();
        int[] dx = new int[] { 1, -1, 0, 0, 1, 1, -1, -1 };
        int[] dy = new int[] { 0, 0, 1, -1, -1, 1, 1, -1 };
        for (int i = 0; i < 8; i++) {
            if (super.inBound(position.getX() + dx[i], position.getY() + dy[i], board)
                    && !board[position.getX() + dx[i]][position.getY() + dy[i]].isNull()
                    && board[position.getX() + dx[i]][position.getY() + dy[i]].getPlayer() != player
                    && !inCheck(board, new Position(position.getX() + dx[i], position.getY() + dy[i])))
                output.add(new Position(position.getX() + dx[i], position.getY() + dy[i]));
        }
        return output;
    }

    @Override
    public ArrayList<Position> getValidMoves(BoardSquare[][] board) {
        ArrayList<Position> output = new ArrayList<Position>();
        int[] dx = new int[] { 1, -1, 0, 0, 1, 1, -1, -1 };
        int[] dy = new int[] { 0, 0, 1, -1, -1, 1, 1, -1 };
        for (int i = 0; i < 8; i++) {
            if (super.inBound(position.getX() + dx[i], position.getY() + dy[i], board)
                    && !board[position.getX() + dx[i]][position.getY() + dy[i]].isNull()
                    && board[position.getX() + dx[i]][position.getY() + dy[i]].getPlayer() != player
                    && !inCheck(board, new Position(position.getX() + dx[i], position.getY() + dy[i])))
                output.add(new Position(position.getX() + dx[i], position.getY() + dy[i]));
        }
        dx = new int[] { -1, 0, 1, 0 };
        dy = new int[] { 0, -1, 0, 1 };
        if (!this.hasMoved()) {
            if (board[this.position.getX() + dx[player]][this.position.getY() + dy[player]].isBlank()
                    && board[this.position.getX() + dx[player] * 2][this.position.getY() + dy[player] * 2].isBlank()
                    && !board[this.position.getX() + dx[player] * 3][this.position.getY() + dy[player] * 3].hasMoved())
                output.add(new Position(this.position.getX() + dx[player] * 2, this.position.getY() + dy[player] * 2));
        }
        dx = new int[] { 1, 0, -1, 0 };
        dy = new int[] { 0, 1, 0, -1 };
        if (!this.hasMoved()) {
            if (board[this.position.getX() + dx[player]][this.position.getY() + dy[player]].isBlank()
                    && board[this.position.getX() + dx[player] * 2][this.position.getY() + dy[player] * 2].isBlank()
                    && board[this.position.getX() + dx[player] * 3][this.position.getY() + dy[player] * 3].isBlank()
                    && !board[this.position.getX() + dx[player] * 4][this.position.getY() + dy[player] * 4].hasMoved())
                output.add(new Position(this.position.getX() + dx[player] * 2, this.position.getY() + dy[player] * 2));
        }
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
    public void drawMe(Graphics g, int x, int y, boolean black, String select, int width, int height,
            BoardSquare[][] board) {
        g.drawImage(this.image, x, y, null);
    }

    @Override
    public boolean isValidMove(Position position, BoardSquare[][] board) {
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
    public String getName() {
        return "King";
    }

    @Override
    public void updatePos(Position pos) {
        position = pos;
    }

    @Override
    public int getValue() {
        return this.value;
    }
}
