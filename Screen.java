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
import Piece.Piece;

import java.awt.image.BufferedImage; //517,545 //815,622
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
    private boolean inInstructions;
    
    
    
    
    private King[] kings;
    private BufferedImage StartGameButtonImage;
    private int StartGameButtonX;
    private int StartGameButtonY;
    private BufferedImage MenuButtonImage;
    private int MenuButtonX;
    private int MenuButtonY;
    private BufferedImage ChessLabelImage;
    private BufferedImage FastRenderImage;
    private int FastRenderX;
    private int FastRenderY;
    private BufferedImage OnImage;
    private BufferedImage OffImage;
    private BufferedImage NamesImage;
    private BufferedImage InstructionsImage;
    private BufferedImage RedImage;
    private BufferedImage BlueImage;
    private BufferedImage YellowImage;
    private BufferedImage GreenImage;
    private BufferedImage FramesImage;
    private int FramesX;
    private int FramesY;
    private int InstructionsX;
    private int InstructionsY;
    private int turn;
    private int alive;
    private boolean inMenu;
    private AudioPlayer at;
    private boolean inCrosshair;
    private int xCrosshair;
    private int yCrosshair;

    private Piece animatePiece;
    private float animateX;
    private float animageY;

    private int player0Points;
    private int player1Points;
    private int player2Points;
    private int player3Points;
    private boolean startAudio;

    private String winString;

    public Screen() {
        inInstructions = false;
        setLayout(null);
        setFocusable(true);
        addKeyListener(this); 
        addMouseListener(this);
        at = new AudioPlayer();

        winString = "";

        inMenu = true;
        inCrosshair = false;
        xCrosshair = 10;
        yCrosshair = 10;
        StartGameButtonX = 496;
        StartGameButtonY = 425;

        MenuButtonX = 956;
        MenuButtonY = 540;
        FastRenderX = 965;
        FastRenderY = 20;
        InstructionsX = 20;
        InstructionsY =730;
        FramesX = 990;
        FramesY = 185;

        startAudio = false;

        animatePiece = null;

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

        String PathFastRenderImage = "Assets" + "/" + "Images" + "/" + "FastRender" + ".png";
        try {
            FastRenderImage = ImageIO.read(new File(PathFastRenderImage));
        } catch (IOException e) {
            System.out.println("Failed");
        }

        String PathOnImage = "Assets" + "/" + "Images" + "/" + "On" + ".png";
        try {
            OnImage = ImageIO.read(new File(PathOnImage));
        } catch (IOException e) {
            System.out.println("Failed");
        }

        String PathOffImage = "Assets" + "/" + "Images" + "/" + "Off" + ".png";
        try {
            OffImage = ImageIO.read(new File(PathOffImage));
        } catch (IOException e) {
            System.out.println("Failed");
        }
        

        String PathNamesImage = "Assets" + "/" + "Images" + "/" + "Names" + ".png";
        try {
            NamesImage = ImageIO.read(new File(PathNamesImage));
        } catch (IOException e) {
            System.out.println("Failed");
        }
        

        String PathInstructionImage = "Assets" + "/" + "Images" + "/" + "Instructions" + ".png";
        try {
            InstructionsImage = ImageIO.read(new File(PathInstructionImage));
        } catch (IOException e) {
            System.out.println("Failed");
        }

        String PathRedImage = "Assets" + "/" + "Images" + "/" + "Red" + ".png";
        try {
            RedImage = ImageIO.read(new File(PathRedImage));
        } catch (IOException e) {
            System.out.println("Failed");
        }

        String PathGreenImage = "Assets" + "/" + "Images" + "/" + "Green" + ".png";
        try {
            GreenImage = ImageIO.read(new File(PathGreenImage));
        } catch (IOException e) {
            System.out.println("Failed");
        }

        String PathBlueImage = "Assets" + "/" + "Images" + "/" + "Blue" + ".png";
        try {
            BlueImage = ImageIO.read(new File(PathBlueImage));
        } catch (IOException e) {
            System.out.println("Failed");
        }

        String PathYellowImage = "Assets" + "/" + "Images" + "/" + "Yellow" + ".png";
        try {
            YellowImage = ImageIO.read(new File(PathYellowImage));
        } catch (IOException e) {
            System.out.println("Failed");
        }

        String PathFramesImage = "Assets" + "/" + "Images" + "/" + "Frames" + ".png";
        try {
            FramesImage = ImageIO.read(new File(PathFramesImage));
        } catch (IOException e) {
            System.out.println("Failed");
        }

        startGame();
    }

    public void startGame() {
        winString = "";
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
        player0Score.setBounds(tempX + -20, tempY + 556, 200, 30);
        player0Score.setText("Blue: " + points[0]);
        this.add(player0Score);
        player0Score.setVisible(false);

        player1Score = new JLabel();
        player1Score.setFont(new Font("Arial", Font.BOLD, 35));
        player1Score.setHorizontalAlignment(SwingConstants.CENTER);
        player1Score.setBounds(tempX - 27, tempY + 115, 200, 30);
        player1Score.setText("Green: " + points[1]);
        this.add(player1Score);
        player1Score.setVisible(false);

        player2Score = new JLabel();
        player2Score.setFont(new Font("Arial", Font.BOLD, 35));
        player2Score.setHorizontalAlignment(SwingConstants.CENTER);
        player2Score.setBounds(tempX + 535, tempY + 115, 200, 30);
        player2Score.setText("Red: " + points[2]);
        this.add(player2Score);
        player2Score.setVisible(false);

        player3Score = new JLabel();
        player3Score.setFont(new Font("Arial", Font.BOLD, 35));
        player3Score.setHorizontalAlignment(SwingConstants.CENTER);
        player3Score.setBounds(tempX + 536, tempY + 556, 200, 30);
        player3Score.setText("Yellow: " + points[3]);
        this.add(player3Score);
        player3Score.setVisible(false);
    }

    public Dimension getPreferredSize() {
        return new Dimension(1355, 790);
    }

    public void oneButton(){
        Config.waitMS = 1;
    }
    public void fiveButton(){
        Config.waitMS = 5;
    }
    public void tenButton(){
        Config.waitMS = 10;
    }
    public void cycleButton(){
        if (Config.waitMS == 1) Config.waitMS = 5;
        else if (Config.waitMS == 5) Config.waitMS = 10;
        else if (Config.waitMS == 10) Config.waitMS = 1;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(205, 170, 125));
        g.fillRect(0, 0, 1355, 790);
        if (inInstructions){
            player0Score.setVisible(false);
            player1Score.setVisible(false);
            player2Score.setVisible(false);
            player3Score.setVisible(false);
        }
        else if (inMenu == false) { //in the game
            player0Score.setVisible(true);
            player1Score.setVisible(true);
            player2Score.setVisible(true);
            player3Score.setVisible(true);
            drawBoard(g);
            //System.out.println("MenuButtonY" + MenuButtonY);
            g.drawImage(MenuButtonImage, MenuButtonX, MenuButtonY, null);
            g.drawImage(FastRenderImage, FastRenderX, FastRenderY, null);
            g.drawImage(InstructionsImage, InstructionsX, InstructionsY, null);
            g.drawImage(FramesImage, FramesX, FramesY, null);
            if (turn == 0) {
                g.drawImage(BlueImage, 20,20, null);
            } else if (turn == 1) {
                g.drawImage(GreenImage,20,20,null);
            } else if(turn == 2) {
                g.drawImage(RedImage,20,20,null);
            } else if (turn == 3) {
                g.drawImage(YellowImage,20,20,null);
            }
            if (Config.fastRender == true) {
                g.drawImage(OnImage, 1080,105, null);
            } else {
                g.drawImage(OffImage, 1060, 105, null);
            }


            if (animatePiece != null){
                int x = (int) animateX;
                int y = (int) animageY;
                g.drawImage(animatePiece.getImage(), y - 20, x - 20, null);
            }
            

        } else { //in the menu
            player0Score.setVisible(false);
            player1Score.setVisible(false);
            player2Score.setVisible(false);
            player3Score.setVisible(false);
            g.drawImage(StartGameButtonImage, StartGameButtonX, StartGameButtonY, null);
            g.drawImage(ChessLabelImage, 285,120,null);
            g.drawImage(NamesImage, 10, 720, null);
        }


        g.setColor(new Color(0, 0, 0));
        if (inCrosshair == true) {
            g.fillRect(0,yCrosshair,1920,1); //y
            g.fillRect(xCrosshair,0,1,1080); //x
        }
        g.setColor(new Color(54,35,16));
        g.fillRect(0,0,1355,5);
        g.fillRect(0,785,1355,5);
        g.fillRect(0,0,5,790);
        g.fillRect(1350,0,5,790);

        if (inInstructions){
            Font font = new Font("Arial", Font.PLAIN, 25);
            g.setFont(font);
            g.drawString(Config.rules[0], 150, 150);
            g.drawString(Config.rules[1], 200, 225);
            g.drawString(Config.rules[2], 200, 250);
            g.drawString(Config.rules[3], 200, 275);
            g.drawString(Config.rules[4], 200, 300);
            g.drawString(Config.rules[5], 200, 325);
            g.drawString(Config.rules[6], 200, 350);
            g.drawImage(InstructionsImage, InstructionsX, InstructionsY, null);
        }

        Font font = new Font("Arial", Font.PLAIN, 100);
        g.setFont(font);
        g.drawString(winString, 400, 400);
        
        
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
            winString = "Gamer Over!";

        }
    }

    public int checkMateDetection(King king) {
        if (king.inCheck(board)) {
            System.out.println("In Check");
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
            System.out.println("Checkmate");
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

    public void update(float xIncrement, float yIncrement){
        animateX += xIncrement;
        animageY += yIncrement;
    }

    public void finishMove(){
        System.out.println("Finish Move");
        board[nextSelect.getX()][nextSelect.getY()].setPiece(animatePiece);

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

        animatePiece = null;
        repaint();

        return;
    }

    public void move() { // successful moving
        if (nextSelect != null) {
            ArrayList<Position> moves = board[currentSelect.getX()][currentSelect.getY()]
                    .returnValidMoveSet(currentSelect, board);// getValidMoves(board);
            for (Position move : moves) {
                if (move.equals(nextSelect) && isValidMove(currentSelect, nextSelect)) {

                    player0Points = points[0];
                    player1Points = points[1];
                    player2Points = points[2];
                    player3Points = points[3];

                    points[board[currentSelect.getX()][currentSelect.getY()]
                            .getPlayer()] += board[nextSelect.getX()][nextSelect.getY()].getValue();


                    animatePiece = board[currentSelect.getX()][currentSelect.getY()].getPiece();
                    board[currentSelect.getX()][currentSelect.getY()].setPiece(new BlankSquare());

                    animateX = animatePiece.getPosition().getCoordX();
                    animageY = animatePiece.getPosition().getCoordY();

                    Thread r = new Thread(new Animate(this, currentSelect, nextSelect));
                    r.start();
            
                    // try{
                    //     r.join(); //wait until t1 is done
                    // } catch(Exception ex) {
                    //     System.out.println(ex);
                    // }

                    return;

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

    public boolean checkMouseStartButton(int mX, int mY) {
        if (inMenu == true) {
            if ((mX >= StartGameButtonX && mX <= StartGameButtonX + 375) && (mY >= StartGameButtonY && mY <= StartGameButtonY + 255)) {
                
                //backToMenu.setVisible(true);
                inMenu = false;
                startGame();
                at.playStartBoard();
                repaint();
                return true;
                
            }
        }
        return false;
    }
    public boolean checkMouseFramesButton(int mX, int mY) {
        if (inMenu == false) {
            if ((mX >= FramesX && mX <= FramesX + 298) && (mY >= FramesY && mY <= FramesY + 77)) {
                cycleButton();
                at.playButtonClick();
                repaint();
            }
        }
        return false;
    }
    public void checkMouseMenuButton(int mX, int mY) {
        if (inMenu == false) {
            
            if ((mX >= MenuButtonX && mX <= MenuButtonX + 355) && (mY >= MenuButtonY && mY <= MenuButtonY + 135)) {
                //backToMenu.setVisible(false);
                
                player0Score.setVisible(false);
                player1Score.setVisible(false);
                player2Score.setVisible(false);
                player3Score.setVisible(false);

                startGame();
                at.playButtonClick();

                inMenu = true;
                repaint();
            }
        }
    }

    public void checkMouseFastRenderButton(int mX, int mY) {
        if (inMenu == false) {
            
            if ((mX >= FastRenderX && mX <= FastRenderX + 350) && (mY >= FastRenderY && mY <= FastRenderY + 80)) {
                Config.fastRender = !Config.fastRender;
                at.playButtonClick();
                repaint();
                
            }
        }
    }


    public void checkMouseInstructionsButton(int mX, int mY) {
        if (inMenu == false) {
            if ((mX >= InstructionsX && mX <= InstructionsX + 319) && (mY >= InstructionsY && mY <= InstructionsY + 45)) {
                inInstructions = !inInstructions;


                at.playButtonClick();
                repaint();
                
            }
        }
    }




    public void mousePressed(MouseEvent e) {
        if (animatePiece != null) return;

        int mX = e.getX();
        int mY = e.getY();
        System.out.println(mX + " " + mY);
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
        if (checkMouseStartButton(mX, mY) == true) {
            startAudio = true;
        }
        checkMouseStartButton(mX, mY);
        checkMouseMenuButton(mX,mY);
        checkMouseFastRenderButton(mX,mY);
        checkMouseInstructionsButton(mX,mY);
        checkMouseFramesButton(mX,mY);
        startAudio = false;
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
        if (animatePiece != null) return;
        
    
        
    }
    public void keyPressed(KeyEvent e) { 
        if (animatePiece != null) return;
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
}