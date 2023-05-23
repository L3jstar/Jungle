package controller;
import listener.GameListener;
import model.*;
import view.*;
import view.ChessComponent.ChessComponent;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

/**
 * Controller is the connection between model and view,
 * when a Controller receive a request from a view, the Controller
 * analyzes and then hands over to the model for processing
 * [in this demo the request methods are onPlayerClickCell() and onPlayerClickChessPiece()]
 *
 */
public class GameController implements GameListener {
    private Chessboard model;
    private ChessboardComponent view;
    private PlayerColor currentPlayer;

    // Record whether there is a selected piece before
    private ChessboardPoint selectedPoint;
    private ChessboardComponent chessboardComponent;
    public GameController(ChessboardComponent view, Chessboard model) {
        this.view = view;
        this.model = model;
        this.currentPlayer = PlayerColor.BLUE;

        view.registerController(this);
        initialize();
        view.initiateChessComponent(model);
        view.repaint();
    }

    private void initialize() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
            }
        }
    }

    public void clear(){
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if(model.getGrid()[i][j].getPiece()!=null){
                    model.getGrid()[i][j].removePiece();//去掉后端
                }
                if(view.getGridComponents()[i][j]!=null){
                    view.getGridComponents()[i][j].removeAll();//去掉前端
                }
            }
        }
    }

    // after a valid move swap the player
    private void swapColor() {
        currentPlayer = currentPlayer == PlayerColor.BLUE ? PlayerColor.RED : PlayerColor.BLUE;

        if(PlayerColor.BLUE==currentPlayer){
        Constant.turnOfBlue++;
        Constant.color="Blue";
        Constant.statusLabel.setText("Turn " + Constant.turnOfBlue + ": " + Constant.color);
        }else {
        Constant.turnOfRed++;
        Constant.color="Red";
        Constant.statusLabel.setText("Turn " + Constant.turnOfBlue + ": " + Constant.color);
        }
    }

    private boolean win() {
        // TODO: Check the board if there is a winner
        ImageIcon blueIcon = new ImageIcon("src/picture/victory blue.png");
        ImageIcon redIcon = new ImageIcon("src/picture/victory red.png");

//        JPanel panelb = new JPanel();
//        panelb.setBackground(Color.BLUE);
//        panelb.setSize(new Dimension(200, 64));
//        panelb.setLayout(null);
//        JLabel labelb = new JLabel("Winner is Blue !");
//        labelb.setBounds(0, 0, 200, 64);
//        labelb.setFont(new Font("Rockwell", Font.BOLD, 20));
//        labelb.setHorizontalAlignment(SwingConstants.CENTER);
//        panelb.add(labelb);
//        UIManager.put("OptionPane.minimumSize",new Dimension(300, 120));
//        JOptionPane.showMessageDialog(null, panelb, "Customized Message Dialog", JOptionPane.PLAIN_MESSAGE, blueIcon);//blue
//
//        JPanel panelr = new JPanel();
//        panelr.setBackground(Color.RED);
//        panelr.setSize(new Dimension(200, 64));
//        panelr.setLayout(null);
//        JLabel labelr = new JLabel("Winner is Red !");
//        labelr.setBounds(0, 0, 200, 64);
//        labelr.setFont(new Font("Rockwell", Font.BOLD, 20));
//        labelr.setHorizontalAlignment(SwingConstants.CENTER);
//        panelr.add(labelr);
//        UIManager.put("OptionPane.minimumSize",new Dimension(300, 120));
//        JOptionPane.showMessageDialog(null, panelr, "Customized Message Dialog", JOptionPane.PLAIN_MESSAGE, redIcon);//red

        String[] options = {"Restart", "End the Game"};

        if (currentPlayer==PlayerColor.BLUE) {
            int b = JOptionPane.showOptionDialog(null, "Pleas choose",
                    "Winner is Blue !",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, blueIcon, options, options[0]);
            if (b == 0) {
                    chessboardComponent.gameController.restart();
            } else if (b==1) {
                    System.exit(0);
                }
        } else {
            int r = JOptionPane.showOptionDialog(null, "Pleas choose",
                    "Winner is Red !",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, redIcon, options, options[0]);
            if (r == 0) {
                    chessboardComponent.gameController.restart();
            } else if (r==1) {
                    System.exit(0);
                }
        }
        return false;

    }
    public void restart(){
        Constant.turnOfBlue=1;
        Constant.turnOfRed=1;
        Constant.color="Blue";
        Constant.mainFrame.dispose();
//        new StartFrame();
        ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
        GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard());
        mainFrame.setVisible(true);
    }

    public void loadBoard(){//读取的信息转化为棋盘
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();

            try (FileReader fileReader = new FileReader(filePath);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {


                clear();
                model.removeAll();

                String line;//一次一行
                while ((line = bufferedReader.readLine()) != null) {
                    if(line.startsWith("Color")){
                        if ( line.substring(5).equals("BLUE")){
                            currentPlayer = PlayerColor.BLUE;
                        } else if(line.substring(5).equals("RED")){
                            currentPlayer = PlayerColor.RED;
                        }
                    } else {
                        char rank = line.charAt(0);
                        char originRank = line.charAt(1);
                        char row = line.charAt(2);
                        char col = line.charAt(3);

                        int Rank = Character.getNumericValue(rank);
                        int OriginRank = Character.getNumericValue(originRank);
                        int Row = Character.getNumericValue(row);
                        int Col = Character.getNumericValue(col);
                        PlayerColor owner;
                        String name;

                        if(line.charAt(4)=='R'){
                            owner = PlayerColor.RED;
                            name = line.substring(7);
                        } else{
                            owner = PlayerColor.BLUE;
                            name = line.substring(8);
                        }
                        model.getGrid()[Row][Col].setPiece(new ChessPiece(owner, name,Rank,new ChessboardPoint(Row,Col)));
                        model.getGrid()[Row][Col].getPiece().setOriginRank(OriginRank);

                    }

                }
                model.addArray();
                view.initiateChessComponent(model);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void AI(){

    }

    // click an empty cell
    @Override
    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {
        if (selectedPoint != null && model.isValidMove(selectedPoint, point)) {
            if(model.isTrap(point)){
                model.trapped(selectedPoint);
            }
            if(model.isTrap(selectedPoint) && !model.isTrap(point)){
                model.escape(selectedPoint);
            }
            model.moveChessPiece(selectedPoint, point);
            view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
            selectedPoint = null;
            swapColor();
            view.repaint();
            // TODO: if the chess enter Dens or Traps and so on
        }
    }

    // click a cell with a chess
    @Override
    public void onPlayerClickChessPiece(ChessboardPoint point, ChessComponent component) {
        if (selectedPoint == null) {
            if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                selectedPoint = point;
                component.setSelected(true);
                component.repaint();
            }
        } else if (selectedPoint.equals(point)) {
            selectedPoint = null;
            component.setSelected(false);
            component.repaint();
        }
        if(selectedPoint != null && model.isValidCapture(selectedPoint, point)){
            view.removeChessComponentAtGrid(point);
            model.captureChessPiece(selectedPoint,point);
            view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
            selectedPoint = null;
            swapColor();
            view.repaint();
        }
    }

    public void saveBoard(){
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save ChessBoard"); // 设置对话框标题

            // 设置文件过滤器，限制用户只能选择文本文件
            FileNameExtensionFilter filter = new FileNameExtensionFilter("GameBoard", "txt");
            fileChooser.setFileFilter(filter);
            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getPath();
                FileWriter fileWriter = new FileWriter(filePath);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                for (int i = 0; i < model.red.size(); i++) {
                    if(model.red.get(i)!=null){
                        bufferedWriter.write(model.red.get(i).toString());
                        bufferedWriter.newLine(); // 换行
                    }
                }
                for (int i = 0; i < model.blue.size(); i++) {
                    if(model.blue.get(i)!=null){
                        bufferedWriter.write(model.blue.get(i).toString());
                        bufferedWriter.newLine(); // 换行
                    }
                }
                //每个棋子的信息
                bufferedWriter.write("Color" + currentPlayer.toString());
                bufferedWriter.close();
                System.out.println("棋盘保存成功");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
