import view.Music;
import view.StartFrame;
import javax.swing.*;
public class Main {
    public static Music musicObject = new Music();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StartFrame();
            musicObject.playMusic();
        });
    }
}