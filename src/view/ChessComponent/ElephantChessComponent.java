package view.ChessComponent;
import model.Chessboard;
import model.PlayerColor;
import view.ChessGameFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This is the equivalent of the ChessPiece class,
 * but this class only cares how to draw Chess on ChessboardComponent
 */
public class ElephantChessComponent extends ChessComponent {
    private PlayerColor owner;
    private boolean selected;
    int size;

    public ElephantChessComponent(PlayerColor owner, int size) {
        this.owner = owner;
        this.selected = false;
        this.size=size;
        this.setSize(size/2, size/2);
        this.setLocation(0,0);
        setVisible(true);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿

        try {
            File file=new File("picture/blueElephant.jpg");
            if(owner==PlayerColor.RED){
                 file=new File("picture/redElephant.jpg");
            }
            BufferedImage image= ImageIO.read(file);
            g.drawImage(image,5,5,getWidth()-10,getHeight()-10,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Font font = new Font("楷体", Font.PLAIN, getWidth() / 2);
//        g2.setFont(font);
//        g2.setColor(owner.getColor());
//        g2.drawString("象", getWidth() / 4, getHeight() * 5 / 8); // FIXME: Use library to find the correct offset.


        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.PINK);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }



}
