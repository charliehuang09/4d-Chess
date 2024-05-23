import java.awt.Graphics;
import java.util.ArrayList;

import Piece.BoardSquare;
import Piece.Piece;
import Piece.Position;
import java.awt.image.BufferedImage;

public class NullSquare extends Piece { // to represent a null square
    private Position position;

    @Override
    public BufferedImage getImage(){
        return null;     
    }

    public boolean isValidMove(Position position, BoardSquare[][] board) {
        this.position = position;
        return false;
    }

    public boolean hasMoved() {
        return false;
    }

    public int getPlayer() {
        return -1;
    }

    @Override
    public Piece clone() {
        return new NullSquare();
    }

    @Override
    public String getName() {
        return "NullSquare";
    }

    @Override
    public void updatePos(Position pos) {
        position = pos;
    }

    @Override
    public ArrayList<Position> getValidMoves(BoardSquare[][] board) {
        ArrayList<Position> output = new ArrayList<Position>();
        return output;
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public void drawMe(Graphics g, int x, int y, boolean black, String select, int width, int height,
            BoardSquare[][] board) {
    }

    @Override
    public int getValue() {
        return 0;
    }
}
