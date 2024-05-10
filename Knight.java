public class Knight extends Piece{
    private Position position;
    private int value;
    private int player;
    public Knight(Position position, int player){
        this.value = 3;
        this.player = player;
        this.position = position;
    }
    @Override
    public boolean isValidMove(Position position, Piece[][] board){
        //I temporarily set this to false so the program would compile, you can get rid
        return false;
    }
    @Override
    public int getPlayer(){
        return this.player;
    }
}
