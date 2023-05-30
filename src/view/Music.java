package view;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
public class Music {
    private Clip clip;
    public void initClip(String filePath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public void playMusic() {
        if (clip != null) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
    public void stopMusic() {
        if (clip != null) {
            clip.stop();
        }
    }

}

