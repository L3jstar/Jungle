package view;
import model.Constant;
import javax.swing.*;
import java.awt.*;
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
    int turn=1;
    public ChessGameFrame(int width, int height) {
        setTitle("斗兽王"); //设置标题
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
        addAIMButton();
        JPanel panel=(JPanel)getContentPane();
        setVisible(true);
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

    /**
     * 在游戏面板中添加标签
     */
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

    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.addActionListener(e -> {
            System.out.println("Click Load");
            chessboardComponent.gameController.loadBoard();
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
            chessboardComponent.gameController.AI();
        });
        button.setLocation(HEIGTH, HEIGTH / 10 + 360);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

}
