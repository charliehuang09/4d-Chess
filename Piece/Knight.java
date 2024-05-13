package Piece;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Knight extends Piece{
    private Position position;
    private int value;
    private int player;
    private String name;
    private BufferedImage image;
    public Knight(Position position, int player){
        this.value = 3;
        this.player = player;
        this.position = position;
        this.name = "Knight";

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
    public Piece clone(){
        return new Knight(this.position.clone(), this.player);
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
    public ArrayList<Position> getValidMoves(BoardSquare[][] board){
        int[] dx = new int[]{2, 2, -2, -2, -1, 1, -1, 1};
        int[] dy = new int[]{-1, 1, -1, 1, 2, 2, -2, -2};
        ArrayList<Position> output = new ArrayList<Position>();
        for (int i = 0; i < 8; i++){
                if (inBound(this.position.getX() + dx[i], this.position.getY() + dy[i], board) && !board[this.position.getX() + dx[i]][this.position.getY() + dy[i]].isNull() && board[this.position.getX() + dx[i]][this.position.getY() + dy[i]].getPlayer() != player) output.add(new Position(this.position.getX() + dx[i], this.position.getY() + dy[i]));
        }
        return output;
    }
    @Override
    public boolean isValidMove(Position position, BoardSquare[][] board){
        int[] dx = new int[]{2, 2, -2, -2, -1, 1, -1, 1};
        int[] dy = new int[]{-1, 1, -1, 1, 2, 2, -2, -2};
        for (int i = 0; i < 8; i++){
            if (this.position.getX() + dx[i] == position.getX() && this.position.getY() + dy[i] == position.getY()) return true;
        }
        return false;
    }
    @Override
    public int getPlayer(){
        return this.player;
    }
    @Override
    public String getName(){
        return "Knight";
    }
    @Override
    public void updatePos(Position pos) {
        position = pos;
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
