package Piece;
import java.awt.*;
import java.util.ArrayList;


public class BoardSquare{ //make a class object for each square to make it easier to draw, can explain in person 
    private Piece piece;
    private boolean black;
    private String select;
    private boolean nullSquare;
    private int width;
    private int height;


    public BoardSquare(Piece pieceP, boolean nullS, String selectP, boolean blackP) {
        black = blackP;
        select = selectP;
        piece = pieceP;
        nullSquare = nullS;
        width = 50;
        height = 50;
    }
    private boolean doPawnPromotion(){
        if (piece.getName() != "Pawn") return false;
        int[] dx = new int[] {-1, 7, -1, 6};
        int[] dy = new int[] {7, -1, 6, -1};
        if (piece.getPosition().getX() == dx[piece.getPlayer()] || piece.getPosition().getY() == dy[piece.getPlayer()]) return true;
        return false;
    }
    public boolean returnValidMoves(Position position, BoardSquare[][] board) {
        return piece.isValidMove(position,board);
    }
    public ArrayList<Position> returnValidMoveSet(Position position, BoardSquare[][] board) {
        return piece.getValidMoves(board);
    }
    public void updatePosition(Position pos) {
        piece.updatePos(pos);
        if (doPawnPromotion()){
            piece = new Queen(piece.getPosition(), piece.getPlayer());
        }
    }
    public void changeSelect(String pass) {
        select = pass;
    }
    public boolean getNull() {
        return nullSquare;
    }
    public void setPiece(Piece pass) {
        piece = pass;
    }
    //only for instantiation when making the board, ignore it after instantiation
    public void startUpdate(Piece pieceP, boolean nulls) {
        piece = pieceP;
        nullSquare = nulls;
    }
    public Piece getPiece() {
        return piece;
    }
    public String getName() {
        return piece.getName();
    }

    public void drawBoard(Graphics g, int y, int x){
        if (nullSquare) return;
        x = x * 50 + 200;
        y = y * 50 + 10;
        if (black == true) {
            g.setColor( new Color(62,49,49));
        } else {
            g.setColor (new Color(161, 102, 47));
        }
        if (select == "current") {
            g.setColor (new Color(25, 128, 121));
        } else if (select == "next") {
            g.setColor (new Color(245, 239, 86));
        }

        
        g.fillRect( x , y, width , height);
        g.setColor( Color.BLACK);
        g.drawRect( x, y, width, height);
    }

    public void drawMe(Graphics g, int x, int y, BoardSquare board[][]) {
        piece.drawMe(g,x,y,black, select, width, height, board);
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getPlayer(){
        return piece.getPlayer();
    }
    public boolean isBlank(){
        return piece.isBlank();
    }
    public boolean isNull(){
        return piece.isNull();
    }
    
    public void drawMoves(Graphics g, BoardSquare[][] board){
        this.piece.drawMoves(g, select, board);
    }
    public void move(){
        this.piece.move();
    }
    public int getValue(){
        return this.piece.getValue();
    }
}
