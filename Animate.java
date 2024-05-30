import Piece.BoardSquare;
import Piece.Config;
import Piece.Position;

public class Animate implements Runnable {
    private Screen screen;
    private float xIncrement;
    private float yIncrement;
    public Animate(Screen screen, Position currPosition, Position nextPosition) {
        this.screen = screen;

        xIncrement = (nextPosition.getCoordX() - currPosition.getCoordX()) / Config.animateFrames;
        yIncrement = (nextPosition.getCoordY() - currPosition.getCoordY()) / Config.animateFrames;
        
    }
    
    public void run() {
        for (int i = 0; i < Config.animateFrames; i++){
            try {
                Thread.sleep(Config.waitMS);
            } catch (InterruptedException e){}
            
            screen.update(xIncrement, yIncrement);
            screen.repaint();
        }
        screen.finishMove();


    }
 }