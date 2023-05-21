package model;

import controller.GameController;
import view.AudioPlayer;
import view.ChessGameFrame;
import view.Music;

import javax.swing.*;

public enum Constant {
    CHESSBOARD_ROW_SIZE(9),CHESSBOARD_COL_SIZE(7);

    private final int num;
    public static JLabel statusLabel=new JLabel();
    public static int turnOfBlue=1;
    public static int turnOfRed=1;
    public static String color="Blue";

//    public static String filepath="src/music/Wind Song.wav";//为什么用了这个全局变量之后startFrame就和mainFrame一起出来了？
//    public static Music musicObject = new Music();
//    public static AudioPlayer audioPlayer=new AudioPlayer();
    public static ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
    public static GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard());



    Constant(int num){
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}