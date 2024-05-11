package Piece;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Queen extends Piece{
    private Position position;
    private int value;
    private int player;
    private String name;
    private BufferedImage image;
    public Queen(Position position, int player){
        this.value = 3;
        this.player = player;
        this.position = position;
        this.name = "Queen";

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
        ArrayList<Position> moves = getValidMoves(board);
        for (Position move : moves){
            if (move.getX() == position.getX() && move.getY() == position.getY()) return true;
        }
        return false;
    }
    
    @Override
    public int getPlayer(){
        return this.player;
    }

    public ArrayList<Position> getValidMoves(Piece[][] board){
        ArrayList<Position> output = new ArrayList<Position>();
        int x;
        int y;

        //up
        x = this.position.getX() - 1;
        y = this.position.getY();
        while(super.inBound(x, y, board)){
            output.add(new Position(x, y));
            x--;
        }

        //down
        x = this.position.getX() + 1;
        y = this.position.getY();
        while(super.inBound(x, y, board)){
            output.add(new Position(x, y));
            x++;
        }

        //right
        x = this.position.getX();
        y = this.position.getY() + 1;
        while(super.inBound(x, y, board)){
            output.add(new Position(x, y));
            y++;
        }

        //left
        x = this.position.getX();
        y = this.position.getY() - 1;
        while(super.inBound(x, y, board)){
            output.add(new Position(x, y));
            y--;
        }

        //diagonal
        x = this.position.getX() + 1;
        y = this.position.getY() + 1;
        while(super.inBound(x, y, board)){
            output.add(new Position(x, y));
            x++;
            y++;
        }
        
        x = this.position.getX() + 1;
        y = this.position.getY() - 1;
        while(super.inBound(x, y, board)){
            output.add(new Position(x, y));
            x++;
            y--;
        }

        x = this.position.getX() - 1;
        y = this.position.getY() + 1;
        while(super.inBound(x, y, board)){
            output.add(new Position(x, y));
            x--;
            y++;
        }

        x = this.position.getX() - 1;
        y = this.position.getY() - 1;
        while(super.inBound(x, y, board)){
            output.add(new Position(x, y));
            x--;
            y--;
        }

        return output;
    }
    @Override
    public Position getPosition(){
        return this.getPosition();
    }
}
