import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import Piece.BoardSquare;
import Piece.Position;
import Piece.Rook;
public class Screen extends JPanel implements MouseListener{
    private Board boardClass;
    private BoardSquare[][] board;
    private int x;
    private int y;
    private int tempX;
    private int tempY;
    private boolean selecting;
    private Position currentSelect;
    private Position nextSelect;
    public Screen() {
        boardClass = new Board();
        board = boardClass.getBoard();
        board[11][8].setPiece(new Rook(new Position(8, 8), 0));
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
    public void mousePressed(MouseEvent e) {

        int mX = e.getX();
        int mY = e.getY();
        //Print location of x and y
        System.out.println("Clicked X: " + mX + ", Y: " + mY);
        Position pos = returnLocation(mX, mY);
        if (pos != null) {
            if (currentSelect == null) {
                if (board[pos.getX()][pos.getY()].isBlank() == false) {
                    System.out.println("set current");
                    board[pos.getX()][pos.getY()].changeSelect("current");
                    currentSelect = pos;
                }
            } else if (nextSelect == null) {
                System.out.println("set next");
                board[pos.getX()][pos.getY()].changeSelect("next");
                nextSelect = pos;
            } else if (currentSelect != null){
                System.out.println("reset current and next");
                board[currentSelect.getX()][currentSelect.getY()].changeSelect("clear");
                board[nextSelect.getX()][nextSelect.getY()].changeSelect("clear");
                
                currentSelect = null;
                nextSelect = null;
            }
        } else {
            System.out.println("Position is null");
        }
        repaint();
    }


    public void mouseReleased(MouseEvent e) {}


    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}


    public void mouseClicked(MouseEvent e) {}
}