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
        return this.getPosition();
    }
}
