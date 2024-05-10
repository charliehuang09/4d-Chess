import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import Piece.Position;
public class Screen extends JPanel implements MouseListener{
    private Board boardClass;
    private BoardSquare[][] board;
    
    private int x;
    private int y;
    public Screen() {
        boardClass = new Board();
        board = boardClass.getBoard();
        x = 200;
        y = 10;
        addMouseListener(this);
    }
    public Dimension getPreferredSize(){
        return new Dimension(1920,1080);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int tempX = x;
        int tempY = y;

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c].getNull() == false) { //this draws everything that is not a null square
                    board[r][c].drawMe(g,x,y);
                }
                x = x + board[0][0].getWidth();
            }
            x = tempX;
            y = y + board[0][0].getHeight();
        }
        x = tempX;
        y = tempY;
        

    }

    public Position returnLocation(int mX, int mY) {
        int tempX = x;
        int tempY = y;
        Position location = null;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if ((x <= mX && mX <= (x + board[0][0].getWidth())) && (y <= mY && mY <= (y + x+board[0][0].getHeight()))) {
                    location = new Position(r,c);
                } else {
                    
                    x = x + x + board[0][0].getWidth();
                }
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
            board[pos.getX()][pos.getY()].changeSelect();
        }
        repaint();
    }


    public void mouseReleased(MouseEvent e) {}


    public void mouseEntered(MouseEvent e) {
        
    }


    public void mouseExited(MouseEvent e) {}


    public void mouseClicked(MouseEvent e) {}
}