package Piece;
public class Position {
    private int x;
    private int y;
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Position clone(){
        return new Position(this.x, this.y);
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getCoordX(){
        return this.x * 50 + 10 + 20;
    }
    public int getCoordY(){
        return this.y * 50 + 200 + 20;
    }
    public String toString(){
        return "X: " + this.x + " Y: " + this.y;
    }
    public boolean equals(Position position){
        if (position == null) return false;
        return this.getX() == position.getX() && this.getY() == position.getY();
    }
}
