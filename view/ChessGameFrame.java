package view;
import model.Constant;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    public StartFrame startFrame;
    private final int WIDTH;
    private final int HEIGTH;
    private final int ONE_CHESS_SIZE;
    private ChessboardComponent chessboardComponent;
    public static JLabel statusLabel;
    public static int currentIndex=0;
    public static int musicIndex=0;
    private JLabel label1;
    public static java.util.List<ImageIcon> imageList = new ArrayList<>();
    public static java.util.List<String> musicList = new ArrayList<>();
    public static JLabel jl_bg;
    public ChessGameFrame(int width, int height) {
        setTitle("斗兽王"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.ONE_CHESS_SIZE = (HEIGTH * 4 / 5) / 9;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        imageList.add(new ImageIcon("src/picture/1.jpg"));
        imageList.add(new ImageIcon("src/picture/2.png"));
        imageList.add(new ImageIcon("src/picture/3.jpg"));
        imageList.add(new ImageIcon("src/picture/4.jpg"));
        jl_bg = new JLabel(imageList.get(0));
        jl_bg.setBounds(0, 0, this.getWidth(), this.getHeight());
        setLayout(null);
        this.getLayeredPane().add(jl_bg, Integer.valueOf(Integer.MIN_VALUE));
        ((JPanel)this.getContentPane()).setOpaque(false);

        musicList.add("src/music/Wind Song.wav");
        musicList.add("src/music/Crepusculo.wav");
        musicList.add("src/music/Indigo Love.wav");
        musicList.add("src/music/Waiting Wind.wav");
        musicList.add("src/music/Sunflower.wav");


        addChessboard();
        addLabel();
        addRestartButton();
        addLoadButton();
        addSaveButton();
        addAIMButton();
        addRetractButton();
        addChangeBGM();
        addChangeSeasonButton();
        JPanel panel=(JPanel)getContentPane();
        setVisible(true);
    }


    public ChessboardComponent getChessboardComponent() {
        return chessboardComponent;
    }

    public void setChessboardComponent(ChessboardComponent chessboardComponent) {
        this.chessboardComponent = chessboardComponent;
    }
    private void addChessboard() {
        chessboardComponent = new ChessboardComponent(ONE_CHESS_SIZE);
        chessboardComponent.setLocation(HEIGTH / 5, HEIGTH / 10);
        add(chessboardComponent);
    }
    private void addLabel() {
        Constant.statusLabel.setText("Turn " + Constant.turnOfBlue + ": " + Constant.color);
        Constant.statusLabel.setLocation(HEIGTH+35, HEIGTH / 10);
        Constant.statusLabel.setSize(200, 60);
        Constant.statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(Constant.statusLabel);
    }
    private void addRestartButton(){
        JButton button = new JButton("Restart");
        ImageIcon icon = new ImageIcon("src/picture/restart.png");
        UIManager.put("OptionPane.minimumSize",new Dimension(300, 120));
        button.addActionListener((e) -> {
            System.out.println("Click Restart");
            int n=JOptionPane.showConfirmDialog(null, icon, "Are you sure to restart ?", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                chessboardComponent.gameController.restart();
            }
        });
        button.setLocation(HEIGTH, HEIGTH / 10+180 );
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    private void addChangeSeasonButton(){
        JButton button = new JButton("Change Season");
        button.addActionListener(e -> {
            System.out.println("Click Change Season");
            currentIndex = (currentIndex + 1) % 4;
            jl_bg.setIcon(imageList.get(currentIndex));
        });
        button.setLocation(HEIGTH, HEIGTH / 10 + 420);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    private void addChangeBGM() {
        JButton button = new JButton("Change BGM");
        button.addActionListener(e -> {
            System.out.println("Click Change BGM");
            chessboardComponent.gameController.changeBGM();
        });
        button.setLocation(HEIGTH, HEIGTH / 10 + 480);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.addActionListener(e -> {
            System.out.println("Click Load");
            chessboardComponent.gameController.loadBoard();
            chessboardComponent.repaint();
        });
        button.setLocation(HEIGTH, HEIGTH / 10 + 240);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    private void addSaveButton(){
        JButton button = new JButton("Save");
        button.addActionListener(e -> {
            System.out.println("Click Save");
            chessboardComponent.gameController.saveBoard();
        });
        button.setLocation(HEIGTH, HEIGTH / 10 + 300);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    private void addAIMButton(){
        JButton button = new JButton("Play with AI");
        button.addActionListener(e -> {
            System.out.println("Click AI");
            chessboardComponent.gameController.swapAI();
        });
        button.setLocation(HEIGTH, HEIGTH / 10 + 360);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    private void addRetractButton(){
        JButton button = new JButton("Retract");
        button.addActionListener(e -> {
            System.out.println("Click Retract");
            chessboardComponent.undo();
        });
        button.setLocation(HEIGTH, HEIGTH / 10 + 120);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

}
