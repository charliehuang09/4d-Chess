
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class AudioPlayer {
    public void playCapture() {
        String path = "Assets/Sounds/capture.wav";
        System.out.println("Path");
        try {
            URL url = this.getClass().getClassLoader().getResource(path);
            Clip stand = AudioSystem.getClip();
            stand.open(AudioSystem.getAudioInputStream(url));
            stand.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
    public void playMove() {
        String path = "Assets/Sounds/move-self.wav";
        System.out.println("Path");
        try {
            URL url = this.getClass().getClassLoader().getResource(path);
            Clip stand = AudioSystem.getClip();
            stand.open(AudioSystem.getAudioInputStream(url));
            stand.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }public void playCheck() {
        String path = "Asset/Sounds/move-self.wav";
        System.out.println("Path");
        try {
            URL url = this.getClass().getClassLoader().getResource(path);
            Clip stand = AudioSystem.getClip();
            stand.open(AudioSystem.getAudioInputStream(url));
            stand.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }    
}
