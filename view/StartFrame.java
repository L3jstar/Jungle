package view;
import model.Constant;
import model.UserInformation;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

        java.util.List<ImageIcon> imageList = new ArrayList<>();

        imageList.add(new ImageIcon("src/picture/6.gif"));
        imageList.add(new ImageIcon("src/picture/7.gif"));
        imageList.add(new ImageIcon("src/picture/8.gif"));
        imageList.add(new ImageIcon("src/picture/9.gif"));
        imageList.add(new ImageIcon("src/picture/10.gif"));
        imageList.add(new ImageIcon("src/picture/11.gif"));
        imageList.add(new ImageIcon("src/picture/12.gif"));
        imageList.add(new ImageIcon("src/picture/13.gif"));

        JLabel imageLabel = new JLabel(imageList.get(0));
        imageLabel.setBounds(0, 0, 400, 500);
        panel.add(imageLabel);

        setVisible(true);

        JLabel gameName=new JLabel();
        gameName.setText("Jungle");
        gameName.setLocation(110, 100);
        gameName.setSize(200, 60);
        gameName.setFont(new Font("Rockwell", Font.CENTER_BASELINE, 60));
        gameName.setForeground(Color.black);
        add(gameName);
        panel.setComponentZOrder(gameName, 0);

        JLabel Play=new JLabel();
        Play.setText("Play");
        Play.setLocation(160, 240);
        Play.setSize(80, 30);
        Play.setFont(new Font("Rockwell", Font.CENTER_BASELINE, 30));
        Play.setForeground(new Color(200,200,200));
        add(Play);
        panel.setComponentZOrder(Play, 0);
        Play.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                Play.setForeground(new Color(100,100,100));
            }

            public void mouseExited(MouseEvent e) {
                Play.setForeground(new Color(200,200,200));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Constant.mainFrame.setVisible(true);
                setVisible(false);
            }
        });


        JLabel BG=new JLabel();
        BG.setText("Change BG");
        BG.setLocation(330, 15);
        BG.setSize(60, 30);
        BG.setFont(new Font("Rockwell", Font.CENTER_BASELINE, 10));
        BG.setForeground(new Color(200,200,200));
        add(BG);
        panel.setComponentZOrder(BG, 0);

        BG.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                BG.setForeground(new Color(100,100,100));
            }

            public void mouseExited(MouseEvent e) {
                BG.setForeground(new Color(200,200,200));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                currentIndex++;
                if (currentIndex >= imageList.size()) {
                    currentIndex = 0;
                }
                imageLabel.setIcon(imageList.get(currentIndex));
            }
        });

        JLabel Rank=new JLabel();
        Rank.setText("Rank List");
        Rank.setLocation(130, 360);
        Rank.setSize(150, 30);
        Rank.setFont(new Font("Rockwell", Font.CENTER_BASELINE, 30));
        Rank.setForeground(new Color(200,200,200));
        add(Rank);
        panel.setComponentZOrder(Rank, 0);
        Rank.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                Rank.setForeground(new Color(100,100,100));
            }

            public void mouseExited(MouseEvent e) {
                Rank.setForeground(new Color(200,200,200));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                StringBuilder Rank = new StringBuilder();
                ArrayList<UserInformation> User = new ArrayList<>();
                String filePath = "src/model/UsersInfo.txt";
                try (FileReader fileReader = new FileReader(filePath); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                    String line;
                    String pattern = "^(\\d+)\\s+(\\d+)\\s+(\\w+)\\s+(\\w+)$";
                    while ((line = bufferedReader.readLine())!=null){
                        Pattern regex = Pattern.compile(pattern);
                        Matcher matcher = regex.matcher(line);
                        if (matcher.matches()){
                            int winTimes = Integer.parseInt(matcher.group(1));
                            int totalTimes = Integer.parseInt(matcher.group(2));
                            String userName = matcher.group(3);
                            String userPassword = matcher.group(4);
                            UserInformation user = new UserInformation(userName,winTimes,totalTimes,userPassword);
                            User.add(user);
                        }
                    }
                    Collections.sort(User,Comparator.comparingInt(UserInformation::getWinTimes).reversed());
                    for(int i=0;i<User.size();i++){
                        String str = (i+1)+"   winTimes: "+User.get(i).getWinTimes()+"   user: "+User.get(i).getName()+"\n";
                        Rank.append(str);
                    }

                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null, Rank);
                //
            }
        });

        JLabel User = new JLabel();
        User.setText("User");
        User.setLocation(160, 300);
        User.setSize(80, 30);
        User.setFont(new Font("Rockwell", Font.CENTER_BASELINE, 30));
        User.setForeground(new Color(200,200,200));
        add(User);
        panel.setComponentZOrder(User, 0);

        User.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                User.setForeground(new Color(100,100,100));
            }

            public void mouseExited(MouseEvent e) {
                User.setForeground(new Color(200,200,200));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                int choice = JOptionPane.showOptionDialog(null, "Welcome to Jungle!", "Jungle", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Sign up", "Log In"}, "Option 1");

                if (choice == JOptionPane.YES_OPTION) {//注册
                    String name = JOptionPane.showInputDialog(null, "Set your name :");
                    String password = JOptionPane.showInputDialog(null, "Set your password:");

                    String filePath = "src/model/UsersInfo.txt"; // Specify the file path
                    try {
                        String text = "0 0 "+name+" "+password;
                        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
                        writer.write(text);
                        writer.newLine();
                        writer.close();
                        JOptionPane.showMessageDialog(null, "Successfully sign up!");
                    } catch (IOException e1) {
                        System.out.println("An error occurred while writing to the file: " + e1.getMessage());
                    }

                } else if (choice == JOptionPane.NO_OPTION) {//登陆
                    String filePath = "src/model/UsersInfo.txt";
                    String name = JOptionPane.showInputDialog(null, "Enter your name:");
                    String password = JOptionPane.showInputDialog(null, "Enter your password:");
                    try (FileReader fileReader = new FileReader(filePath); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                        String line;
                        String pattern = "^(\\d+)\\s+(\\d+)\\s+(\\w+)\\s+(\\w+)$";

                        while ((line = bufferedReader.readLine())!=null){
                            Pattern regex = Pattern.compile(pattern);
                            Matcher matcher = regex.matcher(line);
                            if (matcher.matches()) {
                                int winTimes = Integer.parseInt(matcher.group(1));
                                int totalTimes = Integer.parseInt(matcher.group(2));
                                String userName = matcher.group(3);
                                String userPassword = matcher.group(4);
                                if(userName.equals(name) && userPassword.equals(password)){

                                    Constant.userInformation = new UserInformation(name,winTimes,totalTimes,password);
                                    //怎么加入到主界面中？

                                    //开始主界面
                                    Constant.mainFrame.setVisible(true);
                                    setVisible(false);

                                    JOptionPane.showMessageDialog(null, "Welcome! You are Blue.");
                                    break;
                                }
                            }
                        }

                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

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