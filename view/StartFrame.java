package view;
import model.Constant;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame{
    ChessGameFrame chessGameFrame;
    private JButton j1=new JButton("Start");

    public StartFrame(){
        setTitle("斗兽王");
        setSize(400,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel panel=(JPanel) getContentPane();

        Image picture = new ImageIcon("src/picture/startFrame.jpg").getImage();
        picture = picture.getScaledInstance(400, 500,Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(picture);
        JLabel start = new JLabel(icon);
        start.setSize(400, 500);
        start.setLocation(0, 0);
        add(start);


        j1.setLocation(110,280);
        j1.setSize(180,50);
        j1.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(j1);
        setVisible(true);
        j1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Constant.mainFrame.setVisible(true);
                setVisible(false);
            }
        });
    }

//    public StartFrame(){
//        setTitle("斗兽王");
//        this.WIDTH=400;
//        this.HEIGHT=500;
//        setSize(WIDTH,HEIGHT);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setLayout(null);
//
//        ChessGameFrame chessGameFrame=new ChessGameFrame(1100,810);
//        GameController controller = new GameController(chessGameFrame.getChessboardComponent(), new Chessboard());
//        this.chessGameFrame=chessGameFrame;
//        chessGameFrame.startFrame=this;
//
//        addStartButton();
//
//        Image picture = new ImageIcon("src/view/startFrame.jpg").getImage();
//        picture = picture.getScaledInstance(400, 500,Image.SCALE_DEFAULT);
//        ImageIcon icon = new ImageIcon(picture);
//        JLabel start = new JLabel(icon);
//        start.setSize(400, 500);
//        start.setLocation(0, 0);
//        add(start);
//        setVisible(true);
//    }
//    private void addStartButton(){
//        JButton button=new JButton("Start");
//
//        button.addActionListener((e) -> {
////            this.setVisible(false);
////            Timer.time = 45;
////            if (Controller.timer == null){
////                Controller.timer = new Timer(gameFrame.getBoardView().controller);
////                Controller.timer.start();
////            }
//            this.setVisible(true);
//
//            chessGameFrame.statusLabel.setLocation(770, 81);
//            chessGameFrame.repaint();
//            chessGameFrame.getChessboardComponent().GameController.restart();
//            chessGameFrame.setVisible(true);
//        });
//
//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new ChessGameFrame(getWidth(),getHeight());
//                setVisible(false);
//            }
//        });
//        button.setLocation(100,100);
//        button.setSize(180,50);
//        button.setFont(new Font("Rockwell", Font.BOLD, 20));
//        add(button);
//    }
//
//
    static class ImageComponent extends JComponent {
        Image paintImage;
        public ImageComponent(Image picture) {
            this.setLayout(null);
            this.setFocusable(true);
            this.paintImage = picture;
            repaint();
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(paintImage, 0, 0, paintImage.getWidth(this), paintImage.getHeight(this), this);
        }
    }
}
