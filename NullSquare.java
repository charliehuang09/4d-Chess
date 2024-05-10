import Piece.Piece;
import Piece.Position;

public class NullSquare extends Piece{ //to represent a null square
    public boolean isValidMove(Position position, Piece[][] board) {
        return false;
    }
    public int getPlayer() {
        return -1;
    }
    public boolean getNull() {
        return true;
    }
}
