package Piece;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.ArrayList;
import Piece.Piece;
public abstract class Piece {
    public abstract boolean isValidMove(Position position, BoardSquare[][] board);
    public abstract int getPlayer();
    public abstract Position getPosition();
    public abstract void drawMe(Graphics g, int x, int y, boolean black, String select, int width, int height, BoardSquare[][] board);
    public abstract int getValue();
    public abstract boolean isNull();
    public abstract ArrayList<Position> getValidMoves(BoardSquare[][] board);
    public abstract String getName();
    public abstract void updatePos(Position pos);
    public abstract Piece clone();
    public abstract boolean hasMoved();
    
    public void kill(){ }
    public void drawMoves(Graphics g, String select, BoardSquare[][] board, King[] kings){
        if (select == "current"){
            ArrayList<Position> moves = getValidMoves(board);

            for (Position move : moves){
                // if (isValidMove(this.getPosition(), move, board, kings))
                g.fillOval(move.getCoordY(), move.getCoordX(), 10, 10); //remove for efficiancy
            }
        }
    }
    private boolean isValidMove(Position currentSelect, Position nextSelect, BoardSquare[][] inputBoard, King[] kings){
        BoardSquare[][] board = new BoardSquare[inputBoard.length][inputBoard[0].length];
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = inputBoard[i][j].clone();
            }
        }
        board[currentSelect.getX()][currentSelect.getY()].move(board);
        board[nextSelect.getX()][nextSelect.getY()].setPiece(board[currentSelect.getX()][currentSelect.getY()].getPiece());
        board[currentSelect.getX()][currentSelect.getY()].setPiece(new BlankSquare());
        return !kings[board[nextSelect.getX()][nextSelect.getY()].getPlayer()].inCheck(board);
    }
    
    public static BufferedImage resize(BufferedImage image){
        Image temp = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(temp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    public boolean isBlank(){
        return false;
    }
    public static boolean inBound(int x, int y, BoardSquare[][] board){//need to fix so it accouns for the empty spaces
        if (x < 0) return false;
        if (y < 0) return false;
        if (x >= board.length) return false;
        if (y >= board[0].length) return false;
        return true;
    }
    public String toString(){
        return this.getPlayer() + ": " + this.getName() + this.getPosition().toString();
    }
    public ArrayList<Position> getAttackingMoves(BoardSquare[][] board){
        return this.getValidMoves(board);
    }
    public void move(BoardSquare[][] board) { }
    
}