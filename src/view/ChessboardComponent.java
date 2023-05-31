package view;
import controller.GameController;
import model.*;
import model.Cell;
import model.ChessPiece;
import model.Chessboard;
import model.ChessboardPoint;
import view.ChessComponent.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;
import static model.Constant.CHESSBOARD_COL_SIZE;
import static model.Constant.CHESSBOARD_ROW_SIZE;

/**
 * This class represents the checkerboard component object on the panel
 */
public class ChessboardComponent extends JComponent {
    private final CellComponent[][] gridComponents = new CellComponent[CHESSBOARD_ROW_SIZE.getNum()][CHESSBOARD_COL_SIZE.getNum()];
    private final int CHESS_SIZE;

    private final Set<ChessboardPoint> riverCell = new HashSet<>();
    public controller.GameController GameController;
    private Set<ChessboardPoint> trapCell = new HashSet<>();
    private Set<ChessboardPoint> denCell = new HashSet<>();
    private final Set<ChessboardPoint> landCell = new HashSet<>();
    private final Set<ChessboardPoint> elephantCell = new HashSet<>();
    private final Set<ChessboardPoint> lionCell = new HashSet<>();
    private final Set<ChessboardPoint> tigerCell = new HashSet<>();
    private final Set<ChessboardPoint> leopardCell = new HashSet<>();
    private final Set<ChessboardPoint> wolfCell = new HashSet<>();
    private final Set<ChessboardPoint> dogCell = new HashSet<>();
    private final Set<ChessboardPoint> catCell = new HashSet<>();
    private final Set<ChessboardPoint> ratCell = new HashSet<>();

    public GameController gameController;

    public ChessboardComponent(int chessSize) {
        CHESS_SIZE = chessSize;
        int width = CHESS_SIZE * 7;
        int height = CHESS_SIZE * 9;
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);// Allow mouse events to occur
        setLayout(null);
        setSize(width, height);
        System.out.printf("chessboard width, height = [%d : %d], chess size = %d\n", width, height, CHESS_SIZE);
        initiateGridComponents();
    }

    /**
     * This method represents how to initiate ChessComponent
     * according to Chessboard information
     */
    public void initiateChessComponent(Chessboard chessboard) {
        Cell[][] grid = chessboard.getGrid();
        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                // TODO: Implement the initialization checkerboard
                if (grid[i][j].getPiece() != null) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    if (chessPiece.getName().equals("Elephant")) {
                        gridComponents[i][j].add(new ElephantChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                    }
                    if (chessPiece.getName().equals("Lion")) {
                        gridComponents[i][j].add(new LionChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                    }
                    if (chessPiece.getName().equals("Tiger")) {
                        gridComponents[i][j].add(new TigerChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                    }
                    if (chessPiece.getName().equals("Leopard")) {
                        gridComponents[i][j].add(new LeopardChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                    }
                    if (chessPiece.getName().equals("Wolf")) {
                        gridComponents[i][j].add(new WolfChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                    }
                    if (chessPiece.getName().equals("Dog")) {
                        gridComponents[i][j].add(new DogChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                    }
                    if (chessPiece.getName().equals("Cat")) {
                        gridComponents[i][j].add(new CatChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                    }
                    if (chessPiece.getName().equals("Rat")) {
                        gridComponents[i][j].add(new RatChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                    }
                }
            }
        }

    }

    public void initiateGridComponents() {

        riverCell.add(new ChessboardPoint(3, 1));
        riverCell.add(new ChessboardPoint(3, 2));
        riverCell.add(new ChessboardPoint(4, 1));
        riverCell.add(new ChessboardPoint(4, 2));
        riverCell.add(new ChessboardPoint(5, 1));
        riverCell.add(new ChessboardPoint(5, 2));
        riverCell.add(new ChessboardPoint(3, 4));
        riverCell.add(new ChessboardPoint(3, 5));
        riverCell.add(new ChessboardPoint(4, 4));
        riverCell.add(new ChessboardPoint(4, 5));
        riverCell.add(new ChessboardPoint(5, 4));
        riverCell.add(new ChessboardPoint(5, 5));

        trapCell.add(new ChessboardPoint(8, 2));
        trapCell.add(new ChessboardPoint(8, 4));
        trapCell.add(new ChessboardPoint(7, 3));
        trapCell.add(new ChessboardPoint(0, 2));
        trapCell.add(new ChessboardPoint(0, 4));
        trapCell.add(new ChessboardPoint(1, 3));

        denCell.add(new ChessboardPoint(8, 3));
        denCell.add(new ChessboardPoint(0, 3));


        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                ChessboardPoint temp = new ChessboardPoint(i, j);
                CellComponent cell=null;
                if (riverCell.contains(temp)) {
                    cell = new CellComponent(Color.CYAN, calculatePoint(i, j), CHESS_SIZE, CellType.RIVER);
                    this.add(cell);//青色
                } else if (trapCell.contains(temp)) {
                    cell = new CellComponent(Color.decode("#6B8E23"), calculatePoint(i, j), CHESS_SIZE, CellType.TRAP);
                    this.add(cell);//米色
                } else if (denCell.contains(temp)) {
                    cell = new CellComponent(Color.decode("#FFF8DC"), calculatePoint(i, j), CHESS_SIZE, CellType.DEN);
                    this.add(cell);//玉米色
                } else {
                    cell = new CellComponent(Color.decode("#F5FFFA"), calculatePoint(i, j), CHESS_SIZE, CellType.LAND);
                    this.add(cell);//春天的绿色
                }

                gridComponents[i][j] = cell;
            }
        }
    }

    public void registerController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setChessComponentAtGrid(ChessboardPoint point, ChessComponent chess) {
        getGridComponentAt(point).add(chess);
    }

    public CellComponent[][] getGridComponents() {
        return gridComponents;
    }

    public ChessComponent removeChessComponentAtGrid(ChessboardPoint point) {
        // Note re-validation is required after remove / removeAll.
        ChessComponent chess = (ChessComponent) getGridComponentAt(point).getComponents()[0];
        getGridComponentAt(point).removeAll();
        getGridComponentAt(point).revalidate();
        chess.setSelected(false);
        return chess;
    }


    private CellComponent getGridComponentAt(ChessboardPoint point) {
        return gridComponents[point.getRow()][point.getCol()];
    }

    private ChessboardPoint getChessboardPoint(Point point) {
        System.out.println("[" + point.y / CHESS_SIZE + ", " + point.x / CHESS_SIZE + "] Clicked");
        return new ChessboardPoint(point.y / CHESS_SIZE, point.x / CHESS_SIZE);
    }

    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            JComponent clickedComponent = (JComponent) getComponentAt(e.getX(), e.getY());
            if (clickedComponent.getComponentCount() == 0) {
                System.out.print("None chess here and ");
                gameController.onPlayerClickCell(getChessboardPoint(e.getPoint()), (CellComponent) clickedComponent);
            } else {
                System.out.print("One chess here and ");
                gameController.onPlayerClickChessPiece(getChessboardPoint(e.getPoint()), (ChessComponent) clickedComponent.getComponents()[0]);
            }
            if(gameController.isAI() && gameController.getCurrentPlayer()== PlayerColor.RED){

                List<ChessboardPoint> AIPoint = new ArrayList<>(gameController.getModel().AI());

                ChessboardPoint src = AIPoint.get(0);
                ChessboardPoint dest = AIPoint.get(1);

                JComponent AIClicked = getGridComponentAt(src);
                JComponent AIMove = getGridComponentAt(dest);

                gameController.onPlayerClickChessPiece(src,(ChessComponent) AIClicked.getComponents()[0]);

                if (AIMove.getComponentCount() == 0) {
                    gameController.onPlayerClickCell(dest, (CellComponent) AIMove);
                } else {
                    gameController.onPlayerClickChessPiece(dest, (ChessComponent) AIMove.getComponents()[0]);
                }
            }

        }
    }

    public void undo(){
        ArrayList<int[]> step  = new ArrayList<>(gameController.getModel().getSteps());
        ChessboardPoint point = new ChessboardPoint(step.get(step.size()-1)[3],step.get(step.size()-1)[4]);
        ChessboardPoint selectedPoint = new ChessboardPoint(step.get(step.size()-1)[5],step.get(step.size()-1)[6]);
        setChessComponentAtGrid(point, removeChessComponentAtGrid(selectedPoint));
        if(step.get(step.size()-1)[0]==1){
            PlayerColor color;
            if(step.get(step.size()-1)[7]==0){
                color=PlayerColor.BLUE;
            }else {
                color=PlayerColor.RED;
            }
            switch (step.get(step.size()-1)[8]){
                case  1 :gridComponents[step.get(step.size()-1)[5]][step.get(step.size()-1)[6]].add(new RatChessComponent(color, getCHESS_SIZE()));     break;
                case  2 :gridComponents[step.get(step.size()-1)[5]][step.get(step.size()-1)[6]].add(new CatChessComponent(color, getCHESS_SIZE()));     break;
                case  3 :gridComponents[step.get(step.size()-1)[5]][step.get(step.size()-1)[6]].add(new DogChessComponent(color, getCHESS_SIZE()));     break;
                case  4 :gridComponents[step.get(step.size()-1)[5]][step.get(step.size()-1)[6]].add(new WolfChessComponent(color, getCHESS_SIZE()));    break;
                case  5 :gridComponents[step.get(step.size()-1)[5]][step.get(step.size()-1)[6]].add(new LeopardChessComponent(color, getCHESS_SIZE())); break;
                case  6 :gridComponents[step.get(step.size()-1)[5]][step.get(step.size()-1)[6]].add(new TigerChessComponent(color, getCHESS_SIZE()));   break;
                case  7 :gridComponents[step.get(step.size()-1)[5]][step.get(step.size()-1)[6]].add(new LionChessComponent(color, getCHESS_SIZE()));    break;
                case  8 :gridComponents[step.get(step.size()-1)[5]][step.get(step.size()-1)[6]].add(new ElephantChessComponent(color, getCHESS_SIZE()));break;
            }//这个是今天早上提到的那个多图片BUG
        }
        gameController.getModel().Retract();
        gameController.retractSwapColor();
        repaint();
    }

    public int getCHESS_SIZE() {
        return CHESS_SIZE;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void showValidMoves(List<ChessboardPoint> validMoves) {
        for (ChessboardPoint validMove : validMoves) {
            CellComponent cellComponent = getGridComponentAt(validMove);
            cellComponent.setValidMove(true);
        }
        repaint();
    }
    public void hideValidMoves(List<ChessboardPoint> validMoves) {
        for (ChessboardPoint validMove : validMoves) {
            CellComponent cellComponent = getGridComponentAt(validMove);
            cellComponent.setValidMove(false);
        }
        repaint();
    }

}
