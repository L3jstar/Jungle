package view;
import model.Constant;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StartFrame extends JFrame{
    ChessGameFrame chessGameFrame;
    private JButton j1=new JButton("Start");
    private int currentIndex=0;

    public StartFrame(){
        setTitle("Jungle");
        setSize(400,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel panel = new JPanel(null);
        setContentPane(panel);
//
        java.util.List<ImageIcon> imageList = new ArrayList<>();

        imageList.add(new ImageIcon("src/picture/10.gif"));
        imageList.add(new ImageIcon("src/picture/11.gif"));
        imageList.add(new ImageIcon("src/picture/12.gif"));
        imageList.add(new ImageIcon("src/picture/13.gif"));
        imageList.add(new ImageIcon("src/picture/6.gif"));
        imageList.add(new ImageIcon("src/picture/7.gif"));
        imageList.add(new ImageIcon("src/picture/8.gif"));
        imageList.add(new ImageIcon("src/picture/9.gif"));


        JLabel imageLabel = new JLabel(imageList.get(0));
        imageLabel.setBounds(0, 0, 400, 500);
        panel.add(imageLabel);
//
        JButton j1 = new JButton("Play");
        j1.setFont(new Font("Rockwell", Font.CENTER_BASELINE, 20));
        j1.setBounds(150, 300, 100, 40);
        panel.add(j1);

        JButton j2 = new JButton();
        j2.setFont(new Font("Rockwell", Font.CENTER_BASELINE, 20));
        j2.setBounds(330, 25, 60, 15);
        panel.add(j2);

        // 设置按钮的层级顺序为最高，使其显示在顶层
        panel.setComponentZOrder(j1, 1);
        panel.setComponentZOrder(j2, 1);

        j1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Constant.mainFrame.setVisible(true);
                setVisible(false);
            }
        });
        setVisible(true);

        j2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex++;
                if (currentIndex >= imageList.size()) {
                    currentIndex = 0;
                }

                // 更新图片标签的图像
                imageLabel.setIcon(imageList.get(currentIndex));
            }
        });
        setVisible(true);

        JLabel gameName=new JLabel();
        gameName.setText("Jungle");
        gameName.setLocation(110, 100);
        gameName.setSize(200, 60);
        gameName.setFont(new Font("Rockwell", Font.CENTER_BASELINE, 60));
        gameName.setForeground(Color.white);
        add(gameName);
        panel.setComponentZOrder(gameName, 0);

        JLabel Play=new JLabel();
        Play.setText("Play");
        Play.setLocation(170, 290);
        Play.setSize(200, 60);
        Play.setFont(new Font("Rockwell", Font.CENTER_BASELINE, 30));
        Play.setForeground(Color.white);
        add(Play);
        panel.setComponentZOrder(Play, 0);

        JLabel BG=new JLabel();
        BG.setText("Change BG");
        BG.setLocation(330, 0);
        BG.setSize(100, 60);
        BG.setFont(new Font("Rockwell", Font.CENTER_BASELINE, 10));
        BG.setForeground(new Color(200,200,200));
        add(BG);
        panel.setComponentZOrder(BG, 0);

        Timer timer = new Timer(10, new ActionListener() {
            private Color color1 = new Color(0,0,0);

            int r1=0;
            int g1=0;
            int b1=0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if(r1<200){
                    r1=r1+1;
                    g1=r1+1;
                    b1=r1+1;
                    color1 = new Color(r1,g1,b1);
                }
                Play.setForeground(color1);
                gameName.setForeground(color1);
            }
        });
        timer.start();
    }


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