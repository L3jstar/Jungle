package controller;
import listener.GameListener;
import model.*;
import view.*;
import view.ChessComponent.ChessComponent;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.Constant;
import static model.Constant.gameController;
import static view.ChessGameFrame.musicIndex;
import static view.ChessGameFrame.musicList;

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
    public static boolean win=false;


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

    public void changeBGM() {
        Constant.musicObject.stopMusic();
        musicIndex = (musicIndex + 1) % 5;
        Constant.musicObject.initClip(musicList.get(musicIndex));
        Constant.musicObject.playMusic();
    }
    public void swapColor() {
        currentPlayer = currentPlayer == PlayerColor.BLUE ? PlayerColor.RED : PlayerColor.BLUE;
        if(PlayerColor.BLUE==currentPlayer){
        Constant.turnOfBlue++;
        Constant.color="Blue";
        Constant.statusLabel.setText("Turn " + Constant.turnOfBlue + ": " + Constant.color);
        }else {
        Constant.turnOfRed++;
        Constant.color="Red";
        Constant.statusLabel.setText("Turn " + Constant.turnOfRed + ": " + Constant.color);
        }

        Constant.time=30;
        String text="No moves";
        if(model.getSteps().size()!=0){
            int[] previewsStep = model.getSteps().get(model.steps.size()-1).clone();
            String name=null;
            switch (previewsStep[2]){
                case 8: name="Elephant";break;
                case 7: name="Lion"    ;break;
                case 6: name="Tiger"   ;break;
                case 5: name="Leopard" ;break;
                case 4: name="Wolf"    ;break;
                case 3: name="Dog"     ;break;
                case 2: name="Cat"     ;break;
                case 1: name="Rat"     ;break;
            }
            String player;
            if (previewsStep[1]==0){
                player="Blue";
            } else {
                player="Red";
            }
            text = "<html>"+player+" "+name+" moves<br>from ["+previewsStep[3]+","+previewsStep[4]+"] to ["+previewsStep[5]+","+previewsStep[6]+"]";
        }
        Constant.PreviewsMove.setText(text);

    }

    public void retractSwapColor(){
        if(PlayerColor.BLUE==currentPlayer){
            Constant.turnOfBlue--;
            Constant.color="Red";
            Constant.statusLabel.setText("Turn " + Constant.turnOfRed + ": " + Constant.color);
        }else {
            Constant.turnOfRed--;
            Constant.color="Blue";
            Constant.statusLabel.setText("Turn " + Constant.turnOfBlue + ": " + Constant.color);
        }
        currentPlayer = currentPlayer == PlayerColor.BLUE ? PlayerColor.RED : PlayerColor.BLUE;

        Constant.time=30;
        String text="No moves";
        if(model.getSteps().size()!=0){
            int[] previewsStep = model.getSteps().get(model.steps.size()-1).clone();
            String name=null;
            switch (previewsStep[2]){
                case 8: name="Elephant";break;
                case 7: name="Lion"    ;break;
                case 6: name="Tiger"   ;break;
                case 5: name="Leopard" ;break;
                case 4: name="Wolf"    ;break;
                case 3: name="Dog"     ;break;
                case 2: name="Cat"     ;break;
                case 1: name="Rat"     ;break;
            }
            String player;
            if (previewsStep[1]==0){
                player="Blue";
            } else {
                player="Red";
            }
            text = "<html>"+player+" "+name+" moves<br>from ["+previewsStep[3]+","+previewsStep[4]+"] to ["+previewsStep[5]+","+previewsStep[6]+"]";
        }
        Constant.PreviewsMove.setText(text);
    }
    public void Win() {
        String[] options = {"Restart", "End the Game"};
        ImageIcon blueIcon = new ImageIcon("src/picture/victory blue.png");
        ImageIcon redIcon = new ImageIcon("src/picture/victory red.png");

        if(model.checkWin().equals(PlayerColor.BLUE)){
            int b = JOptionPane.showOptionDialog(null, "Pleas choose",
                    "Winner is Blue !",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, blueIcon, options, options[0]);
            if (b == 0) {
                gameController.restart();
            } else if (b==1) {
                System.exit(0);
            }
        }

        if(model.checkWin().equals(PlayerColor.RED)){
            int r = JOptionPane.showOptionDialog(null, "Pleas choose",
                    "Winner is Red !",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, redIcon, options, options[0]);
            if (r == 0) {
                gameController.restart();
            } else if (r==1) {
                System.exit(0);
            }
        }
    }

    public void restart(){
        Constant.turnOfBlue=1;
        Constant.turnOfRed=1;
        Constant.color="Blue";
        Constant.mainFrame.getTimer().stop();
        Constant.mainFrame.dispose();
        Constant.time=30;
        ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
        Constant.mainFrame=mainFrame;
        GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard());
        mainFrame.setVisible(true);
    }

    private void setContentPane(JPanel panel1) {
    }

    public void loadBoard(){//读取的信息转化为棋盘
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();

            try (FileReader fileReader = new FileReader(filePath);
                 BufferedReader bufferedReader = new BufferedReader(fileReader)) {

                if (!filePath.toLowerCase().endsWith(".txt")) {
                    throw new IllegalArgumentException();
                }
                clear();
                model.removeAll();
                view.repaint();
                String line;//一次一行
                currentPlayer=null;
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
                view.initiateChessComponent(model);
                model.addArray();
                Constant.time=30;
                if(currentPlayer==null){
                    JOptionPane.showMessageDialog(null, "No current player!");
                    restart();
                }
                if(!model.checkPiece()){
                    JOptionPane.showMessageDialog(null, "Wrong piece!");
                    restart();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e){
                String warning = "NOT a txt file!";
                JOptionPane.showMessageDialog(null, warning);
            }
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
    public boolean isAI(){
        return isAI;
    }
    public boolean isAI=false;

    public void setAI(boolean AI) {
        isAI = AI;
    }

    public void swapAI() {
        isAI = isAI == true ? false : true;
    }
    public PlayerColor getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerColor currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Chessboard getModel() {
        return model;
    }

    public void setModel(Chessboard model) {
        this.model = model;
    }

    @Override
    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {//点击棋盘

        if (selectedPoint != null && model.isValidMove(selectedPoint, point)) {
            hideValidMoves();//add
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

            String filepath = "src/music/click.wav";
            ClickMusic musicObject = new ClickMusic();
            musicObject.playMusic(filepath);

            if(model.checkWin()!=null){
                Win();
            }
        }
    }

    // click a cell with a chess
    public void onPlayerClickChessPiece(ChessboardPoint point, ChessComponent component) {//点击棋子

        if (selectedPoint == null) {
            if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                selectedPoint = point;
                component.setSelected(true);
                component.repaint();
                showValidMoves(point);//add
            }
        } else if (selectedPoint.equals(point)) {
            hideValidMoves();//add
            selectedPoint = null;
            component.setSelected(false);
            component.repaint();
        } else if(selectedPoint != null) {//改
            if(model.isTrap(point)){//入
                model.trapped(selectedPoint);
                if(!model.isValidCapture(selectedPoint, point)){
                    model.escape(selectedPoint);
                }
            }
            if(model.isTrap(selectedPoint) && !model.isTrap(point)){//出
                model.escape(selectedPoint);
                if(!model.isValidCapture(selectedPoint, point)){
                    model.trapped(selectedPoint);
                }
            }
            if(model.isValidCapture(selectedPoint, point)){
                hideValidMoves();//add
                view.removeChessComponentAtGrid(point);
                model.captureChessPiece(selectedPoint,point);
                view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
                selectedPoint = null;
                swapColor();
                model.setTurn(model.getTurn()+1);
                view.repaint();

                String filepath = "src/music/click.wav";
                ClickMusic musicObject = new ClickMusic();
                musicObject.playMusic(filepath);

                if(model.checkWin()!=null){
                    Win();
                }
            }
        }
    }

    private List<ChessboardPoint> validMoves;
    public void showValidMoves(ChessboardPoint point) {
        validMoves = new ArrayList<>(model.possibleMove(point));
        view.showValidMoves(validMoves);
    }
    public void hideValidMoves() {
        view.hideValidMoves(validMoves);
    }

}
