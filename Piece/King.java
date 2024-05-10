package Piece;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class King extends Piece{
    private Position position;
    private int value;
    private int player;
    private String name;
    private BufferedImage image;
    public King(Position position, int player){
        this.value = 3;
        this.player = player;
        this.position = position;
        this.name = "King";

        String[] teams = new String[] {"Blue", "Green", "Red", "Yellow"};
        String path = "Assets" + "/" + teams[player] + "/" + this.name + ".png";
        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Failed");
        }
        this.image = super.resize(image);
    }
    @Override
    public void drawMe(Graphics g, int x, int y, boolean black, boolean select, int width, int height) {
        super.drawMe(g,x,y,black, select, width, height);
        g.drawImage(this.image, x, y, null);
    }

    @Override
    public boolean isValidMove(Position position, Piece[][] board){
        return false;
    }
    
    @Override
    public int getPlayer(){
        return this.player;
    }
}
