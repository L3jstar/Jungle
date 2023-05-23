import view.Music;
import view.StartFrame;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new StartFrame();
//            ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
//            GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard());
//            mainFrame.setVisible(true);
            

            String filepath = "src/music/Wind Song.wav";
            Music musicObject = new Music();
            musicObject.playMusic(filepath);//这个可以用，放在main的中间（Music)
        });

    }
}
