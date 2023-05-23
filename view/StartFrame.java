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

        JPanel panel = new JPanel(null);
        setContentPane(panel);

        Image picture = new ImageIcon("src/picture/startFrame.gif").getImage();
        picture = picture.getScaledInstance(400, 500, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(picture);
        JLabel start = new JLabel(icon);
        start.setBounds(0, 0, 400, 500);
        panel.add(start);

        JButton j1 = new JButton("Play");
        j1.setFont(new Font("Rockwell", Font.CENTER_BASELINE, 20));
        j1.setBounds(130, 300, 140, 40);
        panel.add(j1);

        // 设置按钮的层级顺序为最高，使其显示在顶层
        panel.setComponentZOrder(j1, 0);

        j1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Constant.mainFrame.setVisible(true);
                setVisible(false);
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

        Timer timer = new Timer(10, new ActionListener() {
            private Color color = new Color(0,0,0);
            int r=0;
            int g=0;
            int b=0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if(r<200){
                    r=(r+1)%255;
                    g=(r+1)%255;
                    b=(r+1)%255;
                    color = new Color(r,g,b);
                }
                gameName.setForeground(color);
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
