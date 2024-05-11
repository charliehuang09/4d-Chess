import java.awt.Graphics;

import Piece.Piece;



public class BoardSquare{ //make a class object for each square to make it easier to draw, can explain in person 
    private Piece piece;
    private boolean black;
    private boolean select;
    private boolean nullSquare;
    private int width;
    private int height;


    public BoardSquare(Piece pieceP, boolean nullS, boolean selectP, boolean blackP) {
        black = blackP;
        select = selectP;
        piece = pieceP;
        nullSquare = nullS;
        width = 50;
        height = 50;
    }
    public void changeSelect() {
        select = !select;
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

    public void drawMe(Graphics g, int x, int y) {
        piece.drawMe(g,x,y,black, select, width, height);
        

    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    
    
}
