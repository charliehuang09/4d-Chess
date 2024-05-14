package Piece;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
    public void kill(){
        String path = "Assets" + "/" + "Dead" + "/" + this.name + ".png";
        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Failed");
        }
        this.image = super.resize(image);
        this.value = 0;
    }
    public boolean inCheck(BoardSquare[][] board, Position position){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if (!board[i][j].isNull() && !board[i][j].isBlank() && board[i][j].getPlayer() != this.player && !board[i][j].getName().equals("King")){
                    ArrayList<Position> moves = board[i][j].getAttackingMoves(board);
                    for (Position move : moves){
                        if (move.equals(position)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean inCheck(BoardSquare[][] board){
        return this.inCheck(board, this.position.clone());
    }
    @Override
    public Piece clone(){
        return new King(this.position, this.player);
    }
    @Override
    public ArrayList<Position> getValidMoves(BoardSquare[][] board){
        ArrayList<Position> output = new ArrayList<Position>();
        int[] dx = new int[]{1, -1, 0, 0, 1, 1, -1, -1};
        int[] dy = new int[]{0, 0, 1, -1, -1, 1, 1, -1};
        for (int i = 0; i < 8; i++){
            if (super.inBound(position.getX() + dx[i], position.getY() + dy[i], board) && !board[position.getX() + dx[i]][position.getY() + dy[i]].isNull() && board[position.getX() + dx[i]][position.getY() + dy[i]].getPlayer() != player && !inCheck(board, new Position(position.getX() + dx[i], position.getY() + dy[i]))) output.add(new Position(position.getX() + dx[i], position.getY() + dy[i]));
        }
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
    public String getName(){
        return "King";
    }
    @Override
    public void updatePos(Position pos) {
        position = pos;
    }
    @Override
    public int getValue(){
        return this.value;
    }
}
