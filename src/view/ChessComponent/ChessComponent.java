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
            g2.setColor(Color.PINK);
            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(1, 1, this.getWidth() - 1, this.getHeight() - 1, size / 4, size / 4);
            g2.fill(roundedRectangle);
        }
    }
}
