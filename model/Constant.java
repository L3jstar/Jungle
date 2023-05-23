package model;
import controller.GameController;
import view.ChessGameFrame;
import javax.swing.*;

public enum Constant {
    CHESSBOARD_ROW_SIZE(9),CHESSBOARD_COL_SIZE(7);

    private final int num;

    public static JLabel statusLabel=new JLabel();
    public static int turnOfBlue=1;
    public static int turnOfRed=1;
    public static String color="Blue";

    public static ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
    public static GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard());

    Constant(int num){
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
