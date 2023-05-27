package view;
import controller.GameController;
import model.ChessboardPoint;
import model.Constant;
import model.PlayerColor;
import view.ChessComponent.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static model.Constant.PreviewsMove;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    public StartFrame startFrame;
    private final int WIDTH;
    private final int HEIGTH;
    private final int ONE_CHESS_SIZE;
    private ChessboardComponent chessboardComponent;
    public static JLabel statusLabel;
    private int currentIndex=0;
    private java.util.List<ImageIcon> imageList;
    private JLabel imageLabel;
    public static int musicIndex=0;
    int turn=1;
    public static java.util.List<String> musicList = new ArrayList<>();

    public Timer getTimer() {
        return timer;
    }

    javax.swing.Timer timer;
    public ChessGameFrame(int width, int height) {
        musicList.add("src/music/Crepusculo.wav");
        musicList.add("src/music/Indigo Love.wav");
        musicList.add("src/music/Sunflower.wav");
        musicList.add("src/music/Waiting Wind.wav");
        musicList.add("src/music/Wind Song.wav");
        setTitle("Jungle"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.ONE_CHESS_SIZE = (HEIGTH * 4 / 5) / 9;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        addChessboard();
        addLabel();
        addRestartButton();
        addLoadButton();
        addSaveButton();
        addRetractButton();
        addChangeBGButton();
        addAIMButton();
        addTime();
        addPreviewsMove();
        addBGM();
        JPanel panel=(JPanel)getContentPane();
        setVisible(true);
        imageList = new ArrayList<>();
        imageList.add(new ImageIcon("src/picture/mainFrame1.jpg"));
        imageList.add(new ImageIcon("src/picture/mainFrame2.jpg"));
        imageList.add(new ImageIcon("src/picture/mainFrame3.jpg"));
        imageList.add(new ImageIcon("src/picture/mainFrame4.jpg"));
        imageLabel = new JLabel(imageList.get(0));
        imageLabel.setBounds(0, 0, 1100, 810);
        panel.add(imageLabel);

    }

    public ChessboardComponent getChessboardComponent() {
        return chessboardComponent;
    }

    public void setChessboardComponent(ChessboardComponent chessboardComponent) {
        this.chessboardComponent = chessboardComponent;
    }

    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {
        chessboardComponent = new ChessboardComponent(ONE_CHESS_SIZE);
        chessboardComponent.setLocation(HEIGTH / 5, HEIGTH / 10);
        add(chessboardComponent);
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(JLabel statusLabel) {
        ChessGameFrame.statusLabel = statusLabel;
    }

    /**
     * 在游戏面板中添加标签
     */
    private void addLabel() {
        Constant.statusLabel.setText("Turn " + Constant.turnOfBlue + ": " + Constant.color);
        Constant.statusLabel.setLocation(HEIGTH+30, HEIGTH / 10);
        Constant.statusLabel.setSize(200, 60);
        Constant.statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        Constant.statusLabel.setForeground(Color.white);
        add(Constant.statusLabel);
    }
    public void addTime() {
        JLabel Time=new JLabel();
        Time.setText("Time: "+Constant.time+"s");
        Time.setLocation(HEIGTH+30, HEIGTH / 10+40 );
        Time.setSize(180, 30);
        Time.setFont(new Font("Rockwell", Font.CENTER_BASELINE, 20));
        Time.setForeground(Color.white);
        add(Time);

        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(Constant.time>0){
                    Constant.time--;
                    Time.setForeground(Color.white);
                    if(Constant.time<=10){
                        Time.setForeground(Color.RED);
                    }
                    Time.setText("Time: "+Constant.time+"s");
                } else if (Constant.time==0) {
                    JOptionPane.showMessageDialog(null, "TIME IS UP!");
                    Time.setForeground(Color.white);
                    Constant.time--;
                }
            }
        });
        timer.start();
    }
    private void addPreviewsMove(){
        PreviewsMove.setText("No moves");
        PreviewsMove.setLocation(HEIGTH+30, HEIGTH / 10+80 );
        PreviewsMove.setSize(220, 60);
        PreviewsMove.setFont(new Font("Rockwell", Font.CENTER_BASELINE, 20));
        PreviewsMove.setForeground(Color.white);
        add(PreviewsMove);
    }

    private void addRestartButton(){
        ImageIcon icon = new ImageIcon("src/picture/restart.png");
        JLabel Restart=new JLabel();
        Restart.setText("Restart");
        Restart.setLocation(HEIGTH+30, HEIGTH / 10+200 );
        Restart.setSize(120, 30);
        Restart.setFont(new Font("Rockwell", Font.CENTER_BASELINE, 30));
        Restart.setForeground(Color.white);
        add(Restart);
        Restart.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                Restart.setForeground(new Color(100,100,100));
            }

            public void mouseExited(MouseEvent e) {
                Restart.setForeground(Color.white);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Click Restart");
                int n=JOptionPane.showConfirmDialog(null,icon, "Are you sure to restart ?", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    chessboardComponent.gameController.restart();
                }
            }
        });
    }

    private void addLoadButton() {
        JLabel Load=new JLabel();
        Load.setText("Load");
        Load.setLocation(HEIGTH+30, HEIGTH / 10+260 );
        Load.setSize(100, 30);
        Load.setFont(new Font("Rockwell", Font.CENTER_BASELINE, 30));
        Load.setForeground(Color.white);
        add(Load);
        Load.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                Load.setForeground(new Color(100,100,100));
            }

            public void mouseExited(MouseEvent e) {
                Load.setForeground(Color.white);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Click Load");
                chessboardComponent.gameController.loadBoard();
            }
        });
    }

    private void addSaveButton(){
        JLabel save=new JLabel();
        save.setText("Save");
        save.setLocation(HEIGTH+30, HEIGTH / 10+320 );
        save.setSize(100, 30);
        save.setFont(new Font("Rockwell", Font.CENTER_BASELINE, 30));
        save.setForeground(Color.white);
        add(save);
        save.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                save.setForeground(new Color(100,100,100));
            }

            public void mouseExited(MouseEvent e) {
                save.setForeground(Color.white);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Click Save");
                chessboardComponent.gameController.saveBoard();
            }
        });
    }

    private void addRetractButton(){
        JLabel Retract=new JLabel();
        Retract.setText("Retract");
        Retract.setLocation(HEIGTH+30, HEIGTH / 10+380 );
        Retract.setSize(120, 30);
        Retract.setFont(new Font("Rockwell", Font.CENTER_BASELINE, 30));
        Retract.setForeground(Color.white);
        add(Retract);
        Retract.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                Retract.setForeground(new Color(100,100,100));
            }

            public void mouseExited(MouseEvent e) {
                Retract.setForeground(Color.white);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Click Retract");
                chessboardComponent.undo();
            }
        });
    }

    private void addChangeBGButton(){
        JLabel ChaneBG=new JLabel();
        ChaneBG.setText("Change BG");
        ChaneBG.setLocation(HEIGTH+30, HEIGTH / 10+500 );
        ChaneBG.setSize(180, 30);
        ChaneBG.setFont(new Font("Rockwell", Font.CENTER_BASELINE, 30));
        ChaneBG.setForeground(Color.white);
        add(ChaneBG);
        ChaneBG.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                ChaneBG.setForeground(new Color(100,100,100));
            }

            public void mouseExited(MouseEvent e) {
                ChaneBG.setForeground(Color.white);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Click Change BG Picture");
                currentIndex++;
                if (currentIndex >= imageList.size()) {
                    currentIndex = 0;
                }
                imageLabel.setIcon(imageList.get(currentIndex));
            }
        });
    }
    private void addAIMButton(){
        JLabel AI=new JLabel();
        AI.setText("AI mode");
        AI.setLocation(HEIGTH+30, HEIGTH / 10+440 );
        AI.setSize(150, 30);
        AI.setFont(new Font("Rockwell", Font.CENTER_BASELINE, 30));
        AI.setForeground(Color.white);
        add(AI);
        AI.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                AI.setForeground(new Color(100,100,100));
            }

            public void mouseExited(MouseEvent e) {
                AI.setForeground(Color.white);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Click AI");
                chessboardComponent.gameController.swapAI();
            }
        });
    }
    private void addBGM(){
        JLabel BGM=new JLabel();
        BGM.setText("Change BGM");
        BGM.setLocation(HEIGTH+30, HEIGTH / 10+560 );
        BGM.setSize(200, 30);
        BGM.setFont(new Font("Rockwell", Font.CENTER_BASELINE, 30));
        BGM.setForeground(Color.white);
        add(BGM);
        BGM.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                BGM.setForeground(new Color(100,100,100));
            }

            public void mouseExited(MouseEvent e) {
                BGM.setForeground(Color.white);
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Click Change BGM");
                chessboardComponent.gameController.changeBGM();
            }
        });
    }

}
