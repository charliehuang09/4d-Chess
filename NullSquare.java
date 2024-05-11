import java.awt.Graphics;

import Piece.BoardSquare;
import Piece.Piece;
import Piece.Position;

public class NullSquare extends Piece{ //to represent a null square
    private Position position;
    public boolean isValidMove(Position position, BoardSquare[][] board) {
        this.position = position;
        return false;
    }
    public int getPlayer() {
        return -1;
    }
    public boolean getNull() {
        return true;
    }
    @Override
    public Position getPosition(){
        return this.position;
    }
    @Override
    public boolean isBlank(){
        return true;
    }
    
    @Override
    public void drawMe(Graphics g, int x, int y, boolean black, String select, int width, int height, BoardSquare[][] board) {}

    @Override
    public int getValue(){
        return -1;
    }
}
