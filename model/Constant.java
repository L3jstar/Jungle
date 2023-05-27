package model;
import controller.GameController;
import view.ChessGameFrame;
import view.Music;

import javax.swing.*;
import java.util.ArrayList;

public enum Constant {
    CHESSBOARD_ROW_SIZE(9),CHESSBOARD_COL_SIZE(7);

    private final int num;

    public static JLabel statusLabel=new JLabel();
    public static int turnOfBlue=1;
    public static int turnOfRed=0;
    public static String color="Blue";

    public static ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
    public static GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard());
    public static Music musicObject = new Music();
    Constant(int num){
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}