package Piece;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Rook extends Piece{
    private Position position;
    private int value;
    private int player;
    private String name;
    private BufferedImage image;
    public Rook(Position position, int player){
        this.value = 5;
        this.player = player;
        this.position = position;
        this.name = "Rook";

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
        return new Rook(this.position.clone(), this.player);
    }
    @Override
    public boolean isNull() {
        return false;
    }
    @Override
    public String getName(){
        return "Rook";
    }
    @Override
    public void updatePos(Position pos) {
        position = pos;
    }
    @Override
    public boolean isBlank(){
        return false;
    }
    @Override
    public ArrayList<Position> getValidMoves(BoardSquare[][] board){
        ArrayList<Position> output = new ArrayList<Position>();
        int x;
        int y;

        //up
        x = this.position.getX() - 1;
        y = this.position.getY();
        while(super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].isBlank()){
            output.add(new Position(x, y));
            x--;
        }
        if (super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].getPlayer() != player) output.add(new Position(x, y));

        //down
        x = this.position.getX() + 1;
        y = this.position.getY();
        while(super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].isBlank()){
            output.add(new Position(x, y));
            x++;
        }
        if (super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].getPlayer() != player) output.add(new Position(x, y));

        //right
        x = this.position.getX();
        y = this.position.getY() + 1;
        while(super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].isBlank()){
            output.add(new Position(x, y));
            y++;
        }
        if (super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].getPlayer() != player) output.add(new Position(x, y));

        //left
        x = this.position.getX();
        y = this.position.getY() - 1;
        while(super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].isBlank()){
            output.add(new Position(x, y));
            y--;
        }
        if (super.inBound(x, y, board) && !board[x][y].isNull() && board[x][y].getPlayer() != player) output.add(new Position(x, y));

        return output;
    }
    @Override
    public void drawMe(Graphics g, int x, int y, boolean black, String select, int width, int height, BoardSquare[][] board) {
        g.drawImage(this.image, x, y, null);
    }
    @Override
    public boolean isValidMove(Position position, BoardSquare[][] board){
        if (this.position.getX() == position.getX() && this.position.getY() == position.getY()) return false;
        if (this.position.getX() == position.getX()){
            for (int i = Math.min(this.position.getY(), position.getY()); i <= Math.max(this.position.getY(), position.getY()); i++){
                if (this.position.getY() == i || (position.getY() == i && board[this.position.getX()][i].getPlayer() != this.getPlayer())) continue;
                if (!board[this.position.getX()][i].isBlank()) return false;
            }
        }
        if (this.position.getY() == position.getY()){
            for (int i = Math.min(this.position.getX(), position.getX()); i <= Math.max(this.position.getX(), position.getX()); i++){
                if (this.position.getX() == i || (position.getX() == i && board[i][this.position.getX()].getPlayer() != this.getPlayer())) continue;
                if(!board[i][this.position.getY()].isBlank()) return false;
            }
        }
        return true;
    }
    
    @Override
    public int getPlayer(){
        return this.player;
    }

    @Override
    public Position getPosition(){
        return position;
    }
    @Override
    public int getValue(){
        return this.value;
    }
}
