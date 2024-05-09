public interface Piece {
    public boolean isValidMove(Position position, Piece[][] board);
    public int getPlayer();
}