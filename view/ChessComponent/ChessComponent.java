package view.ChessComponent;
import model.PlayerColor;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
public class ChessComponent extends JComponent {
    public boolean selected;
    int size;
    public PlayerColor owner;

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public boolean isSelected() {
        return selected;
    }
    public ChessComponent() {}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isSelected()) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.PINK);// g2.setColor(new Color(255, 255, 255, 150));
            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(1, 1, this.getWidth() - 1, this.getHeight() - 1, size / 4, size / 4);
            g2.fill(roundedRectangle);
        }
    }
//    static class ImageComponent extends JComponent {
//        Image paintImage;
//
//        public ImageComponent(Image image) {
//            this.setLayout(null);
//            this.setFocusable(true);//Sets the focusable state of this Component to the specified value. This value overrides the Component's default focusability.
//            this.paintImage = image;
//            repaint();//execute the paintComponent method
//        }
//
//        // the method describes how to paint, and being invoked by repaint() method.
//        @Override
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            g.drawImage(paintImage, 0, 0, paintImage.getWidth(this), paintImage.getHeight(this), this);
//        }
//    }
}
