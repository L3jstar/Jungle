package view;
import javax.swing.*;
import java.awt.*;

/**
 * This is the equivalent of the Cell class,
 * but this class only cares how to draw Cells on ChessboardComponent
 */

public class CellComponent extends JPanel {
    private Color background;
    int size;
    public CellType type;
    private boolean validMove;
    public void setValidMove(boolean validMove) {
        this.validMove = validMove;
    }
    public CellComponent(Color background, Point location, int size, CellType type) {
        setLayout(new GridLayout(1, 1));
        setLocation(location);
        setSize(size, size);
        this.background = background;
        this.type = type;

        if (type.equals(CellType.RIVER)) {
            background = Color.BLUE;
        } else if (type.equals(CellType.LAND)) {
            background = Color.decode("#40E0D0");//绿宝石
        } else if (type.equals(CellType.TRAP)) {
            background = Color.decode("#556B2F");//土褐色
        } else if (type.equals(CellType.DEN)) {
            background = Color.decode("#FFD700");//金色
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(background);
        g.fillRect(1, 1, this.getWidth() - 1, this.getHeight() - 1);
        if (validMove) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2d.setColor(Color.YELLOW);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.dispose();
        }
    }

    public CellComponent() {
        setPreferredSize(new Dimension(50, 50));
        validMove = false;
    }

}
