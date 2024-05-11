import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.MouseEvent;

import Piece.Bishop;
import Piece.BoardSquare;
import Piece.Position;
import Piece.Queen;
import Piece.Rook;
import Piece.King;
import Piece.Piece;
public class Screen extends JPanel implements MouseListener{
    private Board boardClass;
    private BoardSquare[][] board;
    private int x;
    private int y;
    private int tempX;
    private int tempY;
    private Position currentSelect;
    private Position nextSelect;
    public Screen() {
        boardClass = new Board();
        board = boardClass.getBoard();
        board[6][6].setPiece(new King(new Position(6, 6), 0));
        board[6][7].setPiece(new Queen(new Position(6, 7), 0));
        board[8][8].setPiece(new Rook(new Position(8, 8), 0));
        board[9][9].setPiece(new Bishop(new Position(9, 9), 2));
        x = 200; //HERES THE COORDINATES FOR WHERE THE GRID STARTS
        y = 10;
        tempX = x;
        tempY = y;
        currentSelect = null;
        nextSelect = null;
        addMouseListener(this);
    }
    public Dimension getPreferredSize(){
        return new Dimension(1920,1080);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j].drawBoard(g, i, j);
            }
        }

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c].getNull() == false) { //this draws everything that is not a null square
                    board[r][c].drawMe(g, x, y, board);
                }
                x = x + board[0][0].getWidth();
            }
            x = tempX;
            y = y + board[0][0].getHeight();
        }
        x = tempX;
        y = tempY;


        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j].drawMoves(g, board);
            }
        }
    }

    public Position returnLocation(int mX, int mY) { //goes through every single index and if the mouse coordinates are within the rnages, it returns the position
        x = tempX;
        y = tempY;
        Position location = null;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if ((x <= mX && mX <= (x + board[0][0].getWidth())) && (y <= mY && mY <= (y + board[0][0].getHeight()))) {
                    location = new Position(r,c);
                }
                x = x + board[0][0].getWidth();
            }
            x = tempX;
            y = y + board[0][0].getHeight();
        }
        y = tempY;
        x = tempX;
        return location;
    }
    public void move() { //current only for moving to empty sqaures
        if (nextSelect != null) {
            ArrayList<Position> moves = board[currentSelect.getX()][currentSelect.getY()].returnValidMoveSet(currentSelect,board);//getValidMoves(board);
            for (Position move : moves){
                if (move.equals(nextSelect)) {
                    board[currentSelect.getX()][currentSelect.getY()].move();
                    Piece temp = board[currentSelect.getX()][currentSelect.getY()].getPiece();
                    board[currentSelect.getX()][currentSelect.getY()].setPiece(board[nextSelect.getX()][nextSelect.getY()].getPiece());
                    board[nextSelect.getX()][nextSelect.getY()].setPiece(temp);
                    System.out.println(board[currentSelect.getX()][currentSelect.getY()].getName() + " moved from: (" + currentSelect.getX() + "," + currentSelect.getY() + ") to (" + nextSelect.getX() + "," + nextSelect.getY() + ")");
                    board[currentSelect.getX()][currentSelect.getY()].updatePosition(currentSelect);
                    board[nextSelect.getX()][nextSelect.getY()].updatePosition(nextSelect);
                    board[currentSelect.getX()][currentSelect.getY()].changeSelect("clear");
                    board[nextSelect.getX()][nextSelect.getY()].changeSelect("clear");
                    currentSelect = null;
                    nextSelect = null;
                }
            }
        }
    }
    public void mousePressed(MouseEvent e) {

        int mX = e.getX();
        int mY = e.getY();
        //Print location of x and y
        System.out.println("Clicked X: " + mX + ", Y: " + mY);
        Position pos = returnLocation(mX, mY);
        if (pos != null) {
            if (currentSelect == null) { //selection of an empty square
                if (board[pos.getX()][pos.getY()].isBlank() == false) {
                    System.out.println("set current");
                    board[pos.getX()][pos.getY()].changeSelect("current");
                    currentSelect = pos;
                }
            } else if (nextSelect == null) { //piece to an empty square
                System.out.println("set next");
                board[pos.getX()][pos.getY()].changeSelect("next");
                nextSelect = pos;
            } else if (currentSelect != null){ //if the current selection is already made and want to be canceled
                System.out.println("reset current and next");
                board[currentSelect.getX()][currentSelect.getY()].changeSelect("clear");
                board[nextSelect.getX()][nextSelect.getY()].changeSelect("clear");
                
                currentSelect = null;
                nextSelect = null;
            }
        } else {
            System.out.println("Position is null");
        }
        move();
        repaint();
    }


    public void mouseReleased(MouseEvent e) {}


    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}


    public void mouseClicked(MouseEvent e) {}
}