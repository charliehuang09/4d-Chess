public interface Piece {
    public boolean isValidMove(Position position, Piece[][] board);
    public int getPlayer();
    //public boolean getNull(); //im changing the null system //nevermind, im changning it again
}