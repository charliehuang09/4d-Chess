package Piece;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Bishop extends Piece{
    private Position position;
    private int value;
    private int player;
    private String name;
    private BufferedImage image;
    public Bishop(Position position, int player){
        this.value = 3;
        this.player = player;
        this.position = position;
        this.name = "Bishop";

        String[] teams = new String[] {"Blue", "Green", "Red", "Yellow"};
        String path = "Assets" + "/" + teams[player] + "/" + this.name + ".png";
        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Failed");
        }
        this.image = super.resize(image);
    }
    @Override public ArrayList<Position> getValidMoves(BoardSquare[][] board){
        ArrayList<Position> output = new ArrayList<Position>();
        return output;
    }
    @Override
    public boolean isNull() {
        return false;
    }
    @Override
    public boolean isBlank(){
        return false;
    }
    @Override
    public void drawMe(Graphics g, int x, int y, boolean black, String select, int width, int height, BoardSquare[][] board) {
        g.drawImage(this.image, x, y, null);
    }

    @Override
    public boolean isValidMove(Position position, BoardSquare[][] board){
        return false;
    }
    
    @Override
    public int getPlayer(){
        return this.player;
    }
    @Override
    public Position getPosition(){
        return this.position;
    }
    @Override
    public int getValue(){
        return this.value;
    }
}
