package view.ChessComponent;

import model.PlayerColor;

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
public class LeopardChessComponent extends ChessComponent {
    private PlayerColor owner;
    private boolean selected;
    int size;

    public LeopardChessComponent(PlayerColor owner, int size) {
        this.owner = owner;
        this.selected = false;
        this.size=size;
        setSize(size/2, size/2);
        setLocation(0,0);
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
            File file=new File("C:\\Users\\罗鑫\\Desktop\\Java\\homework\\src\\project\\src\\picture\\bluLeopard.jpg");
            if(owner==PlayerColor.RED){
                file=new File("C:\\Users\\罗鑫\\Desktop\\Java\\homework\\src\\project\\src\\picture\\redLeopard.jpg");
            }
            BufferedImage image= ImageIO.read(file);
            g.drawImage(image,0,0,getWidth(),getHeight(),null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.PINK);//本来是RAD
            g.drawOval(0, 0, getWidth() , getHeight());//有可能要写东西
        }
    }
}