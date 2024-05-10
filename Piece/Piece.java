package Piece;
import java.awt.image.BufferedImage;
import java.awt.*;
public abstract class Piece {
    public abstract boolean isValidMove(Position position, Piece[][] board);
    public abstract int getPlayer();
    public void drawMe(Graphics g, int x, int y, boolean black, boolean select, int width, int height) {
        if (black == true) {
            g.setColor( new Color(62,49,49));
        } else {
            g.setColor (new Color(161, 102, 47));
        }
        if (select == true) {
            g.setColor (new Color(25, 128, 121));
        }

        
        g.fillRect( x , y, width , height);
        g.setColor( Color.BLACK);
        g.drawRect( x, y, width, height);

    }

    
    public static BufferedImage resize(BufferedImage image){
        Image temp = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(temp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
    //public boolean getNull(); //im changing the null system //nevermind, im changning it again
}