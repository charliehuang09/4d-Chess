import java.awt.Graphics;
import java.awt.Color;
public abstract class Piece {
    public abstract boolean isValidMove(Position position, Piece[][] board);
    public abstract int getPlayer();
    public void drawMe(Graphics g, int x, int y, boolean black, int width, int height) {
        if (black == true) {
            g.setColor( new Color(62,49,49));
        } else {
            g.setColor (new Color(161, 102, 47));
        }
        
        g.fillRect( x , y, width , height);
        g.setColor( Color.BLACK);
        g.drawRect( x, y, width, height);
    }
    //public boolean getNull(); //im changing the null system //nevermind, im changning it again
}