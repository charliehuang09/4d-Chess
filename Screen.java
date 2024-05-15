import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import Piece.BlankSquare;
import Piece.BoardSquare;
import Piece.Position;
import Piece.King;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Screen extends JPanel implements MouseListener, ActionListener{
    private Board boardClass;
    private BoardSquare[][] board;
    private int x;
    private int y;
    private int tempX;
    private int tempY;
    private Position currentSelect;
    private Position nextSelect;
    private int[] points;
    private JLabel player0Score;
    private JLabel player3Score;
    private JLabel player2Score;
    private JLabel player1Score;
    private JButton startGameButton;
    private JButton backToMenu;
    private JLabel ChessLabel;
    private King[] kings;
    private BufferedImage StartGameButtonImage;
    private int turn; 
    private int alive;
    private boolean inMenu;
    private AudioPlayer at;

    public Screen() {
        setLayout(null);
        setFocusable(true);
        at = new AudioPlayer();

        inMenu = true;

        startGameButton = new JButton();
        startGameButton.setFont(new Font("Arial", Font.BOLD, 75));
        startGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        startGameButton.setBounds(539, 469, 350, 70);
        startGameButton.setText("Start");
        this.add(startGameButton);
        startGameButton.addActionListener(this);
        startGameButton.setVisible(true);

        String pathStartGameButtonImage = "Assets" + "/" + "Images" + "/" + "StartGame" + ".png";
        try {
            StartGameButtonImage = ImageIO.read(new File(pathStartGameButtonImage));
        } catch (IOException e) {
            System.out.println("Failed");
        }

        backToMenu = new JButton();
        backToMenu.setFont(new Font("Arial", Font.BOLD, 75));
        backToMenu.setHorizontalAlignment(SwingConstants.CENTER);
        backToMenu.setBounds(790, 644, 700, 70);
        backToMenu.setText("Back To Menu");
        this.add(backToMenu);
        backToMenu.addActionListener(this);
        backToMenu.setVisible(false);
        

        ChessLabel = new JLabel();
        ChessLabel.setFont(new Font("Arial", Font.BOLD, 250));
        ChessLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ChessLabel.setBounds(192, 39, 1000, 300);
        ChessLabel.setText("Chess");
        this.add(ChessLabel);
        
        
        startGame();
        
        addMouseListener(this);
    
    }
    public void startGame() {
        alive = 4;
        turn = 3; //RED ALWAYS GOES FIRST

        
        points = new int[] {0, 0, 0, 0};
        boardClass = new Board();
        board = boardClass.getBoard();
        // board[6][6].setPiece(new King(new Position(6, 6), 0));
        // board[6][7].setPiece(new Queen(new Position(6, 7), 0));
        // board[8][8].setPiece(new Rook(new Position(8, 8), 0));
        // board[9][9].setPiece(new Bishop(new Position(9, 9), 2));
        
        kings = new King[] {(King) board[6][0].getPiece(), (King) board[0][6].getPiece(), (King) board[7][13].getPiece(), (King) board[13][7].getPiece()};
        

        x = 200; //HERES THE COORDINATES FOR WHERE THE GRID STARTS
        y = 10;
        tempX = x;
        tempY = y;
        currentSelect = null;
        nextSelect = null;
        
        
        player0Score = new JLabel();
        player0Score.setFont(new Font("Arial", Font.BOLD, 35));
        player0Score.setHorizontalAlignment(SwingConstants.CENTER);
        player0Score.setBounds(tempX+-20, tempY+650, 200, 30);
        player0Score.setText("Blue: " + points[0]);
        this.add(player0Score);
        player0Score.setVisible(false);

        player1Score = new JLabel();
        player1Score.setFont(new Font("Arial", Font.BOLD, 35));
        player1Score.setHorizontalAlignment(SwingConstants.CENTER);
        player1Score.setBounds(tempX-20, tempY+40, 200, 30);
        player1Score.setText("Green: " + points[1]);
        this.add(player1Score);
        player1Score.setVisible(false);

        player2Score = new JLabel();
        player2Score.setFont(new Font("Arial", Font.BOLD, 35));
        player2Score.setHorizontalAlignment(SwingConstants.CENTER);
        player2Score.setBounds(tempX + 575, tempY + 70, 200, 30);
        player2Score.setText("Red: " + points[2]);
        this.add(player2Score);
        player2Score.setVisible(false);

        player3Score = new JLabel();
        player3Score.setFont(new Font("Arial", Font.BOLD, 35));
        player3Score.setHorizontalAlignment(SwingConstants.CENTER);
        player3Score.setBounds(tempX + 575, tempY + 575, 200, 30);
        player3Score.setText("Yellow: " + points[3]);
        this.add(player3Score);
        player3Score.setVisible(false);
    }
    public Dimension getPreferredSize(){
        return new Dimension(1920,1080);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(205,170,125));
        g.fillRect(0,0,1920,1080);
        if (inMenu == false) {
            drawBoard(g);
        } else {
            player0Score.setVisible(false);
            player1Score.setVisible(false);
            player2Score.setVisible(false);
            player3Score.setVisible(false);
            g.drawImage(StartGameButtonImage, 525, 450, null);
        }
    }

    public void drawBoard(Graphics g) {
        player0Score.setVisible(true);
        player1Score.setVisible(true);
        player2Score.setVisible(true);
        player3Score.setVisible(true);
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j].drawBoard(g, i, j);
            }
        }

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c].getNull() == false) { //this draws everything that is not a null square
                    board[r][c].drawMe(g, x, y, board);
                }
                x = x + board[0][0].getWidth();
            }
            x = tempX;
            y = y + board[0][0].getHeight();
        }
        x = tempX;
        y = tempY;


        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j].drawMoves(g, board, kings);
            }
        }
    }

    public Position returnLocation(int mX, int mY) { //goes through every single index and if the mouse coordinates are within the rnages, it returns the position
        x = tempX;
        y = tempY;
        Position location = null;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if ((x <= mX && mX <= (x + board[0][0].getWidth())) && (y <= mY && mY <= (y + board[0][0].getHeight()))) {
                    location = new Position(r,c);
                }
                x = x + board[0][0].getWidth();
            }
            x = tempX;
            y = y + board[0][0].getHeight();
        }
        y = tempY;
        x = tempX;
        return location;
    }
    public void kill(int player){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j].getPlayer() == player) board[i][j].kill();
            }
        }
        alive--;
        if (alive == 1){
            backToMenu.setVisible(true);
        }
    }
    public int checkMateDetection(King king){
        if (king.inCheck(board)){
            for (int i = 0; i < this.board.length; i++){
                for (int j = 0; j < this.board[i].length; j++){
                    if (this.board[i][j].getPlayer() == king.getPlayer()){
                        ArrayList<Position> moves = board[i][j].getValidMoves(board);
                        for (Position move : moves){
                            if (isValidMove(board[i][j].getPosition(), move)){
                                System.out.println(this.board[i][j].getName());
                                System.out.println(move);
                                return 0;
                            }
                        }
                    }
                }
            }
            kill(king.getPlayer());
            return 20;
        }
        return 0;
    }
    public boolean isValidMove(Position currentSelect, Position nextSelect){
        BoardSquare[][] board = new BoardSquare[this.board.length][this.board[0].length];
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j] = this.board[i][j].clone();
            }
        }
        board[currentSelect.getX()][currentSelect.getY()].move();
        board[nextSelect.getX()][nextSelect.getY()].setPiece(board[currentSelect.getX()][currentSelect.getY()].getPiece());
        board[currentSelect.getX()][currentSelect.getY()].setPiece(new BlankSquare());
        
        board[currentSelect.getX()][currentSelect.getY()].updatePosition(currentSelect);
        board[nextSelect.getX()][nextSelect.getY()].updatePosition(nextSelect);
        board[currentSelect.getX()][currentSelect.getY()].changeSelect("clear");
        board[nextSelect.getX()][nextSelect.getY()].changeSelect("clear");
        
        if (board[nextSelect.getX()][nextSelect.getY()].getName().equals("King")) return !this.kings[board[nextSelect.getX()][nextSelect.getY()].getPlayer()].inCheck(board, nextSelect);
        return !this.kings[board[nextSelect.getX()][nextSelect.getY()].getPlayer()].inCheck(board);
    }
    public void move() { //successful moving
        if (nextSelect != null) {
            ArrayList<Position> moves = board[currentSelect.getX()][currentSelect.getY()].returnValidMoveSet(currentSelect,board);//getValidMoves(board);
            for (Position move : moves){
                if (move.equals(nextSelect) && isValidMove(currentSelect, nextSelect)) {
                    if (board[nextSelect.getX()][nextSelect.getY()].isBlank() == false) {
                        at.playCapture();
                    } else {
                        at.playMove();
                    }
                    points[board[currentSelect.getX()][currentSelect.getY()].getPlayer()] += board[nextSelect.getX()][nextSelect.getY()].getValue();
                    board[currentSelect.getX()][currentSelect.getY()].move();
                    
                    
                    
                    board[nextSelect.getX()][nextSelect.getY()].setPiece(board[currentSelect.getX()][currentSelect.getY()].getPiece());
                    board[currentSelect.getX()][currentSelect.getY()].setPiece(new BlankSquare()); //this must be blank for the valid move system to work (i think)


                    board[currentSelect.getX()][currentSelect.getY()].updatePosition(currentSelect);
                    board[nextSelect.getX()][nextSelect.getY()].updatePosition(nextSelect);
                    board[currentSelect.getX()][currentSelect.getY()].changeSelect("clear");
                    board[nextSelect.getX()][nextSelect.getY()].changeSelect("clear");
                    currentSelect = null;
                    nextSelect = null;

                    int tmp = turn;
                    changeTurn();
                    for (King king : kings){
                        if (king.getPlayer() != tmp && king.getValue() != 0) points[tmp] += checkMateDetection(king);
                    }
                    updateTurn();

                    for (int i = 0; i < points.length; i++) {
                        System.out.println(i + "has:" + points[i]);
                    }
                    player0Score.setText("Blue: " + points[0]);
                    player1Score.setText("Green: " + points[1]);
                    player2Score.setText("Red: " + points[2]);
                    player3Score.setText("Yellow: " + points[3]);

                }
            }
        }
    }

    public void changeTurn() {
        turn++;
        turn %= 4;
    }
    public void updateTurn(){
        while(kings[turn].getValue() == 0){
            turn++;
            turn %= 4;
        }
    }
    public void mousePressed(MouseEvent e) {

        int mX = e.getX();
        int mY = e.getY();
        //Print location of x and y
        System.out.println("Clicked X: " + mX + ", Y: " + mY);
        Position pos = returnLocation(mX, mY);
        if (pos != null) {
            if (currentSelect == null) { 
                if (board[pos.getX()][pos.getY()].getPlayer() == turn) { 
                    if (board[pos.getX()][pos.getY()].isBlank() == false) {
                        // System.out.println("set current");
                        board[pos.getX()][pos.getY()].changeSelect("current");
                        currentSelect = pos;
                    }
                }
            } else if (nextSelect == null) { //piece to an empty square
                System.out.println("set next");
                board[pos.getX()][pos.getY()].changeSelect("next");
                nextSelect = pos;
                
            } else if (currentSelect != null){ //if the current selection is already made and want to be canceled
                // System.out.println("reset current and next");
                board[currentSelect.getX()][currentSelect.getY()].changeSelect("clear");
                board[nextSelect.getX()][nextSelect.getY()].changeSelect("clear");
                
                currentSelect = null;
                nextSelect = null;
            }
        } else {
            System.out.println("Position is null");
        }
        move(); //checks if a move has been made and calculates the resulting change
        repaint();
    }


    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}


    public void mouseClicked(MouseEvent e) {}

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startGameButton) {
            startGameButton.setVisible(false);
            ChessLabel.setVisible(false);
            backToMenu.setVisible(true);
            inMenu = false;
            startGame();
            repaint();
        }
        if (e.getSource() == backToMenu){
            backToMenu.setVisible(false);
            startGameButton.setVisible(true);
            ChessLabel.setVisible(true);
            player0Score.setVisible(false);
            player1Score.setVisible(false);
            player2Score.setVisible(false);
            player3Score.setVisible(false);
            
            startGame();
            

            
            inMenu = true;
            repaint();
        }
    }
}