import java.awt.Graphics;
public class Board {
    private Piece[][] board;
    public Board() {
        board = new Piece[14][14];
        pawns(); //adds the pawns onto the board
        //call nullify after setting up the pieces to clean up
        
        nullify();

    }
    public void nullify() {
        //EXPLANATION
        //a method that goes from the board from length to right and makes the need elements in columns = null
        for (int r = 0; r < board.length; r++) { 
            if (r < 3 || r >= 13) {
                for (int c = 0; c < 3; c++) {
                    board[r][c] = null; //replace null with proposed nothing class if needd
                }
                for (int c = board[0].length-3; c < board[0].length; c++) {
                    board[r][c] = null; 
                }
            }
        }   
    }
    public void pawns() {
        //makes the pawns on the team 1 and team 3 (yellow and red) 
        for (int c = 3; c < board[0].length-3; c++) {

            board[1][c] = new Pawn(new Position(1,c), 1);
            board[12][c] = new Pawn(new Position(12,c), 3);
        }
        //makes the pawns on teh team 0 and team 2 (blue and green)
        for (int r = 3; r < board[0].length-3; r++) {

            board[r][1] = new Pawn(new Position(r,1), 0);
            board[r][12] = new Pawn(new Position(r,12), 2);
        }
    }
    public void rook() {
        
        
        //makes the pawns on teh team 1 and team 3 (blue and green)
        for (int c = 0; c < board[0].length; c++) {
            if (c == 4 || c == 10) {
                board[0][c] = new Rook(new Position(0,c), 1);
                board[13][c] = new Rook(new Position(13,c), 13;
            }
        }
        //team 0, team 2
        for (int r = 0; r < board.length; r++) {
            if (r == 4 || r == 10) {
                board[r][0] = new Rook(new Position(r,0), 0);
                board[r][13] new Rook(new Position(r,13), 2);
            }
        }
    }


}
