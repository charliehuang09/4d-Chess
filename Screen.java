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
import java.awt.event.KeyListener; 
import java.awt.event.KeyEvent; 

import javax.imageio.ImageIO;
import javax.swing.JButton;
import Piece.BlankSquare;
import Piece.BoardSquare;
import Piece.Config;
import Piece.Position;
import Piece.King;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Screen extends JPanel implements MouseListener, ActionListener, KeyListener{
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
    
    
    private JButton fastRender;
    
    private King[] kings;
    private BufferedImage StartGameButtonImage;
    private int StartGameButtonX;
    private int StartGameButtonY;
    private BufferedImage MenuButtonImage;
    private int MenuButtonX;
    private int MenuButtonY;
    private BufferedImage ChessLabelImage;
    private int turn;
    private int alive;
    private boolean inMenu;
    private AudioPlayer at;
    private boolean inCrosshair;
    private int xCrosshair;
    private int yCrosshair;

    public Screen() {
        setLayout(null);
        setFocusable(true);
        addKeyListener(this); 
        addMouseListener(this);
        at = new AudioPlayer();

        inMenu = true;
        inCrosshair = false;
        xCrosshair = 10;
        yCrosshair = 10;
        StartGameButtonX = 565;
        StartGameButtonY = 425;

        MenuButtonX = 1011;
        MenuButtonY = 604;

        

        String pathStartGameButtonImage = "Assets" + "/" + "Images" + "/" + "StartGame" + ".png";
        try {
            StartGameButtonImage = ImageIO.read(new File(pathStartGameButtonImage));
        } catch (IOException e) {
            System.out.println("Failed");
        }

        String pathMenuButtonImage = "Assets" + "/" + "Images" + "/" + "Menu" + ".png";
        try {
            MenuButtonImage = ImageIO.read(new File(pathMenuButtonImage));
        } catch (IOException e) {
            System.out.println("Failed");
        }

        String PathChessLabelImage = "Assets" + "/" + "Images" + "/" + "ChessLabel" + ".png";
        try {
            ChessLabelImage = ImageIO.read(new File(PathChessLabelImage));
        } catch (IOException e) {
            System.out.println("Failed");
        }

        

        fastRender = new JButton();
        fastRender.setFont(new Font("Arial", Font.PLAIN, 10));
        fastRender.setBounds(1150, 25, 150, 50);
        fastRender.setText("Turn On Fastrender");
        this.add(fastRender);
        fastRender.addActionListener(this);

        
        

        startGame();

        

    }

    public void startGame() {
        alive = 4;
        turn = 3; // RED ALWAYS GOES FIRST

        points = new int[] { 0, 0, 0, 0 };
        boardClass = new Board();
        board = boardClass.getBoard();

        kings = new King[] { (King) board[6][0].getPiece(), (King) board[0][6].getPiece(),
                (King) board[7][13].getPiece(), (King) board[13][7].getPiece() };

        x = 200; // HERES THE COORDINATES FOR WHERE THE GRID STARTS
        y = 10;
        tempX = x;
        tempY = y;
        currentSelect = null;
        nextSelect = null;

        player0Score = new JLabel();
        player0Score.setFont(new Font("Arial", Font.BOLD, 35));
        player0Score.setHorizontalAlignment(SwingConstants.CENTER);
        player0Score.setBounds(tempX + -20, tempY + 650, 200, 30);
        player0Score.setText("Blue: " + points[0]);
        this.add(player0Score);
        player0Score.setVisible(false);

        player1Score = new JLabel();
        player1Score.setFont(new Font("Arial", Font.BOLD, 35));
        player1Score.setHorizontalAlignment(SwingConstants.CENTER);
        player1Score.setBounds(tempX - 20, tempY + 40, 200, 30);
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

    public Dimension getPreferredSize() {
        return new Dimension(1920, 1080);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(205, 170, 125));
        g.fillRect(0, 0, 1920, 1080);
        if (inMenu == false) {
            drawBoard(g);
            g.drawImage(MenuButtonImage, 1000, 500, null);
        } else {
            player0Score.setVisible(false);
            player1Score.setVisible(false);
            player2Score.setVisible(false);
            player3Score.setVisible(false);
            g.drawImage(StartGameButtonImage, StartGameButtonX, StartGameButtonY, null);
            g.drawImage(ChessLabelImage, 370,120,null);
        }


        g.setColor(new Color(0, 0, 0));
        if (inCrosshair == true) {
            g.fillRect(0,yCrosshair,1920,1); //y
            g.fillRect(xCrosshair,0,1,1080); //x
        }
        g.setColor(new Color(54,35,16));
        g.fillRect(0,0,1920,5);
        g.fillRect(0,787,1920,8);
        g.fillRect(0,0,5,1080);
        g.fillRect(1530,0,10,1080);
        
        
    }

    public void drawBoard(Graphics g) {
        player0Score.setVisible(true);
        player1Score.setVisible(true);
        player2Score.setVisible(true);
        player3Score.setVisible(true);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].drawBoard(g, i, j);
            }
        }

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c].getNull() == false) { // this draws everything that is not a null square
                    board[r][c].drawMe(g, x, y, board);
                }
                x = x + board[0][0].getWidth();
            }
            x = tempX;
            y = y + board[0][0].getHeight();
        }
        x = tempX;
        y = tempY;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].drawMoves(g, board, kings);
            }
        }
    }

    public Position returnLocation(int mX, int mY) { // goes through every single index and if the mouse coordinates are
                                                     // within the rnages, it returns the position
        x = tempX;
        y = tempY;
        Position location = null;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if ((x <= mX && mX <= (x + board[0][0].getWidth()))
                        && (y <= mY && mY <= (y + board[0][0].getHeight()))) {
                    location = new Position(r, c);
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

    public void kill(int player) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getPlayer() == player)
                    board[i][j].kill();
            }
        }
        alive--;
        if (alive == 1) {
            //

        }
    }

    public int checkMateDetection(King king) {
        if (king.inCheck(board)) {
            for (int i = 0; i < this.board.length; i++) {
                for (int j = 0; j < this.board[i].length; j++) {
                    if (this.board[i][j].getPlayer() == king.getPlayer()) {
                        ArrayList<Position> moves = board[i][j].getValidMoves(board);
                        for (Position move : moves) {
                            if (isValidMove(board[i][j].getPosition(), move)) {
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

    public boolean isValidMove(Position currentSelect, Position nextSelect) {
        BoardSquare[][] board = new BoardSquare[this.board.length][this.board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = this.board[i][j].clone();
            }
        }
        board[nextSelect.getX()][nextSelect.getY()]
                .setPiece(board[currentSelect.getX()][currentSelect.getY()].getPiece());
        board[currentSelect.getX()][currentSelect.getY()].setPiece(new BlankSquare());

        board[currentSelect.getX()][currentSelect.getY()].updatePosition(currentSelect);
        board[nextSelect.getX()][nextSelect.getY()].updatePosition(nextSelect);
        board[currentSelect.getX()][currentSelect.getY()].changeSelect("clear");
        board[nextSelect.getX()][nextSelect.getY()].changeSelect("clear");
        board[nextSelect.getX()][nextSelect.getY()].move(board);

        if (board[nextSelect.getX()][nextSelect.getY()].getName().equals("King"))
            return !this.kings[board[nextSelect.getX()][nextSelect.getY()].getPlayer()].inCheck(board, nextSelect);
        return !this.kings[board[nextSelect.getX()][nextSelect.getY()].getPlayer()].inCheck(board);
    }

    public void move() { // successful moving
        if (nextSelect != null) {
            ArrayList<Position> moves = board[currentSelect.getX()][currentSelect.getY()]
                    .returnValidMoveSet(currentSelect, board);// getValidMoves(board);
            for (Position move : moves) {
                if (move.equals(nextSelect) && isValidMove(currentSelect, nextSelect)) {

                    int player0Points = points[0];
                    int player1Points = points[1];
                    int player2Points = points[2];
                    int player3Points = points[3];

                    points[board[currentSelect.getX()][currentSelect.getY()]
                            .getPlayer()] += board[nextSelect.getX()][nextSelect.getY()].getValue();

                    board[nextSelect.getX()][nextSelect.getY()]
                            .setPiece(board[currentSelect.getX()][currentSelect.getY()].getPiece());
                    board[currentSelect.getX()][currentSelect.getY()].setPiece(new BlankSquare());
                    // this must be blank for the valid move system

                    board[currentSelect.getX()][currentSelect.getY()].updatePosition(currentSelect);
                    board[nextSelect.getX()][nextSelect.getY()].updatePosition(nextSelect);
                    board[currentSelect.getX()][currentSelect.getY()].changeSelect("clear");
                    board[nextSelect.getX()][nextSelect.getY()].changeSelect("clear");
                    board[nextSelect.getX()][nextSelect.getY()].move(board);

                    boolean playCheckSoundKing = false;
                    ArrayList<King> kingsInCheck = new ArrayList<King>();
                    for (King king : kings) {
                        if (king.inCheck(board)) {
                            playCheckSoundKing = true;
                            System.out.println("playChecKsoundking set to true");
                            kingsInCheck.add(king);
                        }

                    }

                    boolean playCheckSoundValidPiece = false;
                    ArrayList<Position> AttackingMoves = board[nextSelect.getX()][nextSelect.getY()]
                            .getAttackingMoves(board);
                    if (kingsInCheck.size() != 0) {
                        for (int i = 0; i < AttackingMoves.size(); i++) {
                            for (int k = 0; k < kingsInCheck.size(); k++) {
                                if (kingsInCheck.get(k).getPosition().toString()
                                        .equals(AttackingMoves.get(i).toString())) {
                                    System.out.println("playCheckSoundValidPiece set to true");
                                    playCheckSoundValidPiece = true;
                                }
                            }
                        }
                    }

                    boolean playCaptureBool = false;
                    if (player0Points < points[0] || player1Points < points[1] || player2Points < points[2]
                            || player3Points < points[3]) {
                        playCaptureBool = true;
                    }

                    if (playCheckSoundKing == true && playCheckSoundValidPiece == true) {
                        at.playCheck();
                    } else if (playCaptureBool == true) {
                        at.playCapture();
                        System.out.println("Play Capture");
                    } else {
                        at.playMove();
                        System.out.println("Play Move");
                    }

                    currentSelect = null;
                    nextSelect = null;

                    int tmp = turn;
                    changeTurn();
                    for (King king : kings) {
                        if (king.getPlayer() != tmp && king.getValue() != 0)
                            points[tmp] += checkMateDetection(king);
                    }
                    updateTurn();

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

    public void updateTurn() {
        while (kings[turn].getValue() == 0) {
            turn++;
            turn %= 4;
        }
    }

    public void checkMouseStartButton(int mX, int mY) {
        if (inMenu == true) {
            if ((mX >= StartGameButtonX && mX <= StartGameButtonX + 375) && (mY >= StartGameButtonY && mY <= StartGameButtonY + 255)) {
                
                //backToMenu.setVisible(true);
                inMenu = false;
                startGame();
                repaint();
            }
        }
    }
    public void checkMouseMenuButton(int mX, int mY) {
        if (inMenu == false) {
            if ((mX >= MenuButtonX && mX <= MenuButtonX + 352) && (mY >= MenuButtonY && mY <= MenuButtonY + 154)) {
                //backToMenu.setVisible(false);
                
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

    public void mousePressed(MouseEvent e) {

        int mX = e.getX();
        int mY = e.getY();
        Position pos = returnLocation(mX, mY);
        if (pos != null) {
            if (currentSelect == null) {
                if (board[pos.getX()][pos.getY()].getPlayer() == turn) {
                    if (board[pos.getX()][pos.getY()].isBlank() == false) {
                        board[pos.getX()][pos.getY()].changeSelect("current");
                        currentSelect = pos;
                    }
                }
            } else if (nextSelect == null) { // piece to an empty square
                board[pos.getX()][pos.getY()].changeSelect("next");
                nextSelect = pos;

            } else if (currentSelect != null) { // if the current selection is already made and want to be canceled
                board[currentSelect.getX()][currentSelect.getY()].changeSelect("clear");
                board[nextSelect.getX()][nextSelect.getY()].changeSelect("clear");

                currentSelect = null;
                nextSelect = null;
            }
        } else {
            System.out.println("Position is null");
        }
        checkMouseStartButton(mX, mY);
        checkMouseMenuButton(mX,mY);
        move(); // checks if a move has been made and calculates the resulting change
        repaint();
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fastRender) {
            if (Config.fastRender == true) {
                fastRender.setText("Turn On Fastrender");
            } else {
                fastRender.setText("Turn Off Fastrender");
            }
            Config.fastRender = !Config.fastRender;
            repaint();
        }
        
        
    }
    public void keyPressed(KeyEvent e) { 
        //use to detect what key is 
        if (e.getKeyCode() == 67) {
            inCrosshair = !(inCrosshair);
        }
        if (inCrosshair == true) {
            if (e.getKeyCode()  == 38) { //up
                yCrosshair -= 5;
            } else if (e.getKeyCode() == 40) { //down
                yCrosshair += 5;
            } else if (e.getKeyCode() == 37) { //left
                
                xCrosshair -= 5;
            } else if (e.getKeyCode() == 39) { //right
                
                xCrosshair += 5;
            } else if (e.getKeyCode()  == 87) { //up
                yCrosshair -= 1;
            } else if (e.getKeyCode() == 83) { //down
                yCrosshair += 1;
            } else if (e.getKeyCode() == 65) { //left
                xCrosshair -= 1;
            } else if (e.getKeyCode() == 68) { //right
                xCrosshair += 1;
            }
            System.out.println("X: " + xCrosshair + " Y: " + yCrosshair);
        }
        repaint();
        

     }
     public void keyReleased(KeyEvent e) {} 
     public void keyTyped(KeyEvent e) {} 

     public void animate() {
        while (true) {


            try {
                Thread.sleep(10);
              } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
              }
              repaint();
            repaint();
        }
    
     }
}