package Piece;
import java.awt.image.BufferedImage;
import java.awt.*;
public abstract class Piece {
    public abstract boolean isValidMove(Position position, BoardSquare[][] board);
    public abstract int getPlayer();
    public abstract Position getPosition();
    public void drawMe(Graphics g, int x, int y, boolean black, String select, int width, int height, BoardSquare[][] board) {
        //to be deleted
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
    //public boolean getNull(); //im changing the null system //nevermind, im changning it again
}