public class Knight implements Piece{
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
    }
    @Override
    public int getPlayer(){
        return this.player;
    }
}
