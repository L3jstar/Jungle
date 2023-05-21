package controller;
import listener.GameListener;
import model.*;
import view.*;
import view.ChessComponent.ChessComponent;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.Random;


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
        if (currentPlayer==PlayerColor.BLUE) {
            JOptionPane.showMessageDialog(null, "蓝方 胜利！");

        } else {
            JOptionPane.showMessageDialog(null, "红方 胜利！");

        }
        return false;
    }
    public void restart(){
        Constant.turnOfBlue=1;
        Constant.turnOfRed=1;
        Constant.color="Blue";
        Constant.mainFrame.dispose();
        new StartFrame();

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

//    public void changeBGM(){
//        Random random = new Random();
//        int v = random.nextInt(4);
//
////    if(v==0){
////        Constant.filepath="src/music/Crepusculo.wav";
////    } else if (v==1) {
////        Constant.filepath="src/music/Indigo Love.wav";
////    } else if (v==2) {
////        Constant.filepath="src/music/Sunflower.wav";
////    }else if (v==3){
////        Constant.filepath="src/music/Waiting Wind.wav";
////    }else {
////        Constant.filepath = "src/music/Wind Song.wav";
////    }
//
////        Music musicObject = new Music();
////        musicObject.playMusic(Constant.filepath);
//
////        if(v==0){
////            Constant.audioPlayer.stop();
////            Constant.audioPlayer.play("src/music/Crepusculo.wav");
////        } else if (v==1) {
////            Constant.audioPlayer.play("src/music/Indigo Love.wav");
////        } else if (v==2) {
////            Constant.audioPlayer.play("src/music/Sunflower.wav");
////        }else if (v==3){
////            Constant.audioPlayer.play("src/music/Waiting Wind.wav");
////        }else {
////            Constant.audioPlayer.play("src/music/Wind Song.wav");
////        }
//        Constant.filepath="src/music/Crepusculo.wav";
//        Constant.audioPlayer.stop();
//
//    }

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
