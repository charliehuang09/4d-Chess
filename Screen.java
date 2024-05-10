import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
public class Screen extends JPanel {
    private Board boardClass;
    private BoardSquare[][] board;
    public Screen() {
        boardClass = new Board();
        board = boardClass.getBoard();

    }
    public Dimension getPreferredSize(){
        return new Dimension(1920,1080);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 200; //the starting positions of the top left corner of the board, including the null squares
        int y = 10;

        int tempX = x;

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
        

    }
}