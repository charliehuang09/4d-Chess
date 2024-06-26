package Piece;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.ArrayList;

public class BlankSquare extends Piece { // a class to represent a square that is empty
    public boolean isValidMove(Position position, BoardSquare[][] board) {
        return false;
    }

    @Override
    public BufferedImage getImage(){
        return null;     
    }

    @Override
    public boolean hasMoved() {
        return true;
    }

    @Override
    public Piece clone() {
        return new BlankSquare();
    }

    @Override
    public ArrayList<Position> getValidMoves(BoardSquare[][] board) {
        ArrayList<Position> output = new ArrayList<Position>();
        return output;
    }

    @Override
    public boolean isNull() {
        return false;
    }

    public int getPlayer() {
        return -1;
    }

    @Override
    public boolean isBlank() {
        return true;
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public String getName() {
        return "BlankSquare";
    }

    @Override
    public void updatePos(Position pos) {
        // nothing
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
