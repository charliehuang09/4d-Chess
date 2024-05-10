public class BlankSquare extends Piece{ //a class to represent a square that is empty
    
    public boolean isValidMove(Position position, Piece[][] board) {
        return false;
    }
    public int getPlayer() {
        return -1;
    }

}
