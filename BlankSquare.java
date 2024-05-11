import java.awt.Graphics;

import Piece.BoardSquare;
import Piece.Piece;
import Piece.Position;

public class BlankSquare extends Piece{ //a class to represent a square that is empty
    public boolean isValidMove(Position position, BoardSquare[][] board) {
        return false;
    }
    public int getPlayer() {
        return -1;
    }
    @Override
    public boolean isBlank(){
        return true;
    }

    @Override
    public Position getPosition(){
        return null;
    }
    @Override
    public void drawMe(Graphics g, int x, int y, boolean black, String select, int width, int height, BoardSquare[][] board) {}

    @Override
    public int getValue(){
        return -1;
    }

}
