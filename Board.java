import Piece.Bishop;
import Piece.BlankSquare;
import Piece.BoardSquare;
import Piece.King;
import Piece.Knight;
import Piece.Pawn;
import Piece.Position;
import Piece.Queen;
import Piece.Rook;

public class Board {
    private BoardSquare[][] board;
    public Board() {
        board = new BoardSquare[14][14];
        fillBlank(); //ALWAYS MAKE SURE YOU CALL THIS FIRST BEFORE ALL THE OTHER PIECES
        pawns(); //adds the pawns onto the board
        rook();
        knight();
        bishop();
        king();
        queen();
        //call nullify after setting up the pieces to clean up

        nullify();
        /* 
        board[5][5].setPiece(new Queen(new Position(5, 5), 3));
        board[6][6].setPiece(new King(new Position(6, 6), 2));
        board[7][7].setPiece(new Bishop(new Position(7, 7), 2));
        board[8][8].setPiece(new Pawn(new Position(8, 8), 2));
        board[9][9].setPiece(new Knight(new Position(9, 9), 0));
        board[10][10].setPiece(new Rook(new Position(10, 10), 1));
        */


    }
    public void setSelect(Position pass) {
        //board[pass.getX()][pass.getY()].changeSelect();
    }
    public void fillBlank() { //fills a grid of blank classes
        boolean alternate = false;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c ++) {
                if (alternate == false) {
                    if ((c+1) % 2 == 0) {
                        board[r][c] = new BoardSquare(new BlankSquare(), false, null, true);
                    } else if((c+1) % 2 == 1){
                        board[r][c] = new BoardSquare(new BlankSquare(), false, null, false);
                    }
                } else {
                    if ((c+1) % 2 == 0) {
                        board[r][c] = new BoardSquare(new BlankSquare(), false, null,  false);
                    } else if ((c+1) % 2 == 1){
                        board[r][c] = new BoardSquare(new BlankSquare(), false, null, true);
                    }
                }
            }
            alternate = !alternate;
        }
    }
    public BoardSquare[][] getBoard() { //returns the board so the screen class can render it
        return board;
    }
    public void nullify() { //males all the needed elements need
        //EXPLANATION
        //a method that goes from the board from length to right and makes the need elements in columns = null
        for (int r = 0; r < board.length; r++) { 
            if (r < 3 || r >= 11) {
                for (int c = 0; c < 3; c++) {
                    board[r][c].startUpdate(new NullSquare(), true); //replace null with proposed nothing class if needd
                }
                for (int c = board[0].length-3; c < board[0].length; c++) {
                    board[r][c].startUpdate(new NullSquare(), true);
                }
            }
        }   
    }
    public void pawns() { //adds the pawns, supposedly 
        //makes the pawns on the team 1 and team 3 (yellow and red) 
        for (int c = 3; c < board[0].length-3; c++) {

            board[1][c].startUpdate(new Pawn(new Position(1,c), 1),false);
            board[12][c].startUpdate(new Pawn(new Position(12,c), 3),false);
        }
        //makes the pawns on teh team 0 and team 2 (blue and green)
        for (int r = 3; r < board[0].length-3; r++) {

            board[r][1].startUpdate(new Pawn(new Position(r,1), 0),false);
            board[r][12].startUpdate(new Pawn(new Position(r,12), 2),false);
        }
    }
     
    public void knight() { //adds the knights
        
        
        //makes the pawns on teh team 1 and team 3 (blue and green)
        for (int c = 0; c < board[0].length; c++) {
            if (c == 4 || c == 9) {
                board[0][c].startUpdate(new Knight(new Position(0,c), 1),false);
                board[13][c].startUpdate(new Knight(new Position(13,c), 3),false);
            }
        }
        //team 0, team 2
        for (int r = 0; r < board.length; r++) {
            if (r == 4 || r == 9) {
                board[r][0].startUpdate(new Knight(new Position(r,0), 0),false);
                board[r][13].startUpdate(new Knight(new Position(r,13), 2),false);
            }
        }
    }
    public void bishop() { //adds the bishop
        
        
        //makes the pawns on teh team 1 and team 3 (blue and green)
        for (int c = 0; c < board[0].length; c++) {
            if (c == 5 || c == 8) {
                board[0][c].startUpdate(new Bishop(new Position(0,c), 1),false);
                board[13][c].startUpdate(new Bishop(new Position(13,c), 3),false);
            }
        }
        //team 0, team 2
        for (int r = 0; r < board.length; r++) {
            if (r == 5 || r == 8) {
                board[r][0].startUpdate(new Bishop(new Position(r,0), 0),false);
                board[r][13].startUpdate(new Bishop(new Position(r,13), 2),false);
            }
        }
    }

    public void king() { //adds the kings
        
        
        board[6][0].startUpdate(new King(new Position(6,0), 0),false); 
        board[0][6].startUpdate(new King(new Position(0,6), 1),false); 
        board[7][13].startUpdate(new King(new Position(7,13), 2),false);
        board[13][7].startUpdate(new King(new Position(13,7), 3),false);
    }
    
    public void queen() { //adds the queens
        
        board[7][0].startUpdate(new Queen(new Position(7,0), 0),false); //fine
        board[0][7].startUpdate(new Queen(new Position(0,7), 1),false); //fine
        board[6][13].startUpdate(new Queen(new Position(6,13), 2),false);
        board[13][6].startUpdate(new Queen(new Position(13,6), 3),false);

        
    }

    //ROOK CLASS
    public void rook() { //adds the rooks
        
        
        //makes the pawns on teh team 1 and team 3 (blue and green)
        for (int c = 0; c < board[0].length; c++) {
            if (c == 3 || c == 10) {
                board[0][c].startUpdate(new Rook(new Position(0,c), 1),false);
                board[13][c].startUpdate(new Rook(new Position(13,c), 3),false);
            }
        }
        //team 0, team 2
        for (int r = 0; r < board.length; r++) {
            if (r == 3 || r == 10) {
                board[r][0].startUpdate(new Rook(new Position(r,0), 0),false);
                board[r][13].startUpdate(new Rook(new Position(r,13), 2),false);
            }
        }
    }
    



}
