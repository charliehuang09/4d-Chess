import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
        int x = 450;
        int y = 10;

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c].getNull() == false) {
                    board[r][c].drawMe(g,x,y);
                }
                x = x + 50;
            }
            x = 450;
            y = y + 50;
        }
        

    }
}