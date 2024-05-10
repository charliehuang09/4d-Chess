public class Pawn implements Piece{
    private Position position;
    private int value;
    private int player;
    public Pawn(Position position, int player){
        this.value = 1;
        this.player = player;
        this.position = position;
    }
    @Override
    public boolean isValidMove(Position position, Piece[][] board){
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        //pawn can move forward
        if (this.position.getX() + dx[player] == this.position.getX() && this.position.getY() + dy[player] == position.getY() && board[position.getX()][position.getY()] == null){
            return true;
        }
        
        //pawn can caputer peices

        //left
        dx = new int[]{-1, 1,  1, -1};
        dy = new int[]{ 1, 1, -1, -1};
        if (this.position.getX() + dx[player] == this.position.getX() && this.position.getY() + dy[player] == position.getY() && board[position.getX()][position.getY()] != null){
            if (board[position.getX()][position.getY()].getPlayer() != player){
                return true;
            }
            return false;
        }

        //right
        dx = new int[]{1, 1,  -1, -1};
        dy = new int[]{1, -1, -1,  1};
        if (this.position.getX() + dx[player] == position.getX() && this.position.getY() + dy[player] == position.getY() && board[position.getX()][position.getY()] != null){
            if (board[position.getX()][position.getY()].getPlayer() != player){
                return true;
            }
            return false;
        }
        return false;
    }
    @Override
    public int getPlayer(){
        return this.player;
    }
    /* 
    @Override
    public boolean getNull() {
        return false;
    }
    */
}
