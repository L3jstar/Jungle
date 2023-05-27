package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * This class store the real chess information.
 * The Chessboard has 9*7 cells, and each cell has a position for chess
 */
public class Chessboard {
    private Cell[][] grid;
    public ArrayList<ChessPiece> red ;
    public ArrayList<ChessPiece> blue;

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    int turn;

    public ArrayList<int[]> getSteps() {
        return steps;
    }

    public ArrayList<int[]> steps = new ArrayList<>();

    public Chessboard() {
        this.grid = new Cell[Constant.CHESSBOARD_ROW_SIZE.getNum()][Constant.CHESSBOARD_COL_SIZE.getNum()];//19X19
        this.red = new ArrayList<>();
        this.blue = new ArrayList<>();
        initGrid();
        initPieces();
        turn = 1;
        addArray();
    }
    public void addArray(){//bug
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if (grid[i][j].getPiece() != null && grid[i][j].getPiece().getOwner() == PlayerColor.RED) {
                    this.red.add(grid[i][j].getPiece());
                } else if(grid[i][j].getPiece() != null && grid[i][j].getPiece().getOwner() == PlayerColor.BLUE){
                    this.blue.add(grid[i][j].getPiece());
                }
            }
        }//red 和 blue 的数组
    }

    private void initGrid() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                grid[i][j] = new Cell();
            }
        }
    }
    public PlayerColor checkWin(){//可能加一个判断条件？一方所有棋子不能动了则输
        boolean redEmpty = false;
        if(red.size()==0){
            redEmpty = true;
        }
        boolean blueEmpty = false;
        if(blue.size()==0){
            blueEmpty = true;
        }
        if(grid[8][3].getPiece() != null || blueEmpty){
            return PlayerColor.RED;
        } else if(grid[0][3].getPiece() != null || redEmpty){
            return PlayerColor.BLUE;
        }
        return null;
    }//若有赢家，返回赢家，若无，返回null

    private void initPieces() {
        //红方
        grid[2][6].setPiece(new ChessPiece(PlayerColor.RED, "Elephant",8,new ChessboardPoint(2,6)));
        grid[0][0].setPiece(new ChessPiece(PlayerColor.RED, "Lion",    7,new ChessboardPoint(0,0)));
        grid[0][6].setPiece(new ChessPiece(PlayerColor.RED, "Tiger",   6,new ChessboardPoint(0,6)));
        grid[2][2].setPiece(new ChessPiece(PlayerColor.RED, "Leopard", 5,new ChessboardPoint(2,2)));
        grid[2][4].setPiece(new ChessPiece(PlayerColor.RED, "Wolf",    4,new ChessboardPoint(2,4)));
        grid[1][1].setPiece(new ChessPiece(PlayerColor.RED, "Dog",     3,new ChessboardPoint(1,1)));
        grid[1][5].setPiece(new ChessPiece(PlayerColor.RED, "Cat",     2,new ChessboardPoint(1,5)));
        grid[2][0].setPiece(new ChessPiece(PlayerColor.RED, "Rat",     1,new ChessboardPoint(2,0)));
        //蓝方
        grid[6][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Elephant",8,new ChessboardPoint(6,0)));
        grid[8][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Lion",    7,new ChessboardPoint(8,6)));
        grid[8][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Tiger",   6,new ChessboardPoint(8,0)));
        grid[6][4].setPiece(new ChessPiece(PlayerColor.BLUE, "Leopard", 5,new ChessboardPoint(6,4)));
        grid[6][2].setPiece(new ChessPiece(PlayerColor.BLUE, "Wolf",    4,new ChessboardPoint(6,2)));
        grid[7][5].setPiece(new ChessPiece(PlayerColor.BLUE, "Dog",     3,new ChessboardPoint(7,5)));
        grid[7][1].setPiece(new ChessPiece(PlayerColor.BLUE, "Cat",     2,new ChessboardPoint(7,1)));
        grid[6][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Rat",     1,new ChessboardPoint(6,6)));



    }
    public void removeAll(){
        for(int i=0;i < red.size(); i++){
            getGridAt(red.get(i).getCurrentPoint()).removePiece();
        }
        for(int i=0;i < blue.size(); i++){
            getGridAt(blue.get(i).getCurrentPoint()).removePiece();
        }
        red.clear();
        blue.clear();
        steps.clear();
    }

    public boolean isRiver(ChessboardPoint point) {
        if(point.getRow()>=3 && point.getRow()<=5) {
            return point.getCol() >= 1 && point.getCol() <= 5 && point.getCol() != 3;
        }
        return false;
    }//添加了河的坐标信息(判断某个点是否为河)
    public boolean isTrap(ChessboardPoint point) {
        if(point.getCol()==2||point.getCol()==4) {
            return point.getRow() == 0 || point.getRow() == 8;
        } else if (point.getCol()==3){
            return point.getRow() == 1 || point.getRow() == 7;
        }
        return false;
    }//判断某个点是否为陷阱
    public boolean isDens(ChessboardPoint point) {
        if(point.getCol()==3){
            return point.getRow() == 0 || point.getRow() == 8;
        }
        return false;
    }//判断某个点是否为兽穴

    public ChessPiece getChessPieceAt(ChessboardPoint point) {
        return getGridAt(point).getPiece();
    }

    private Cell getGridAt(ChessboardPoint point) {
        return grid[point.getRow()][point.getCol()];
    }

    private int calculateDistance(ChessboardPoint src, ChessboardPoint dest) {
        return Math.abs(src.getRow() - dest.getRow()) + Math.abs(src.getCol() - dest.getCol());
    }

    private ChessPiece removeChessPiece(ChessboardPoint point) {
        ChessPiece chessPiece = getChessPieceAt(point);
        getGridAt(point).removePiece();
        return chessPiece;
    }

    private void setChessPiece(ChessboardPoint point, ChessPiece chessPiece) {
        getGridAt(point).setPiece(chessPiece);
    }

    public void moveChessPiece(ChessboardPoint src, ChessboardPoint dest) {//src:源点,dest:目标点
        if (!isValidMove(src, dest)) {
            throw new IllegalArgumentException("Illegal chess move!");
        }
        setChessPiece(dest, removeChessPiece(src));
        getChessPieceAt(dest).setCurrentPoint(dest);
        int[] move =new int[9];
        move[0]=0;
        if(getChessPieceAt(dest).getOwner().equals(PlayerColor.BLUE)){
            move[1]=0;
        }else {
            move[1]=1;
        }
        move[2]=getChessPieceAt(dest).getOriginRank();
        move[3]=src.getRow();
        move[4]=src.getCol();
        move[5]=dest.getRow();
        move[6]=dest.getCol();
        steps.add(move);
    }

    public void captureChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        if (!isValidCapture(src, dest)) {
            throw new IllegalArgumentException("Illegal chess capture!");
        }

        int[] capture =new int[9];
        capture[0]=1;
        if(getChessPieceAt(src).getOwner().equals(PlayerColor.BLUE)){
            capture[1]=0;
        }else {
            capture[1]=1;
        }
        capture[2]=getChessPieceAt(src).getOriginRank();
        capture[3]=src.getRow();
        capture[4]=src.getCol();
        capture[5]=dest.getRow();
        capture[6]=dest.getCol();
        if(getChessPieceAt(dest).getOwner().equals(PlayerColor.BLUE)){
            capture[7]=0;
        } else {
            capture[7]=1;
        }
        capture[8]=getChessPieceAt(dest).getOriginRank();
        steps.add(capture);



        if(getChessPieceAt(dest).getOwner().equals(PlayerColor.RED)){
            for(int i=0;i<red.size();i++){
                if(red.get(i).getOriginRank()==getChessPieceAt(dest).getOriginRank()){
                    red.remove(i);
                    break;
                }
            }
        } else if (getChessPieceAt(dest).getOwner().equals(PlayerColor.BLUE)){
            for(int i=0;i<blue.size();i++){
                if(blue.get(i).getOriginRank()==getChessPieceAt(dest).getOriginRank()){
                    blue.remove(i);
                    break;
                }
            }
        }
        getGridAt(dest).removePiece();
        setChessPiece(dest,removeChessPiece(src));
        getChessPieceAt(dest).setCurrentPoint(dest);
    }
    public void trapped(ChessboardPoint point) {
        if(getChessPieceAt(point).getOwner().equals(PlayerColor.RED)){
            if(point.getRow()>4){
                getChessPieceAt(point).setRank(0);
            }
        } else {
            if(point.getRow()<4){
                getChessPieceAt(point).setRank(0);
            }
        }
    }
    public void escape(ChessboardPoint point) {
        switch (getChessPieceAt(point).getName()){
            case "Elephant":     getChessPieceAt(point).setRank(8); break;
            case "Lion"    :     getChessPieceAt(point).setRank(7); break;
            case "Tiger"   :     getChessPieceAt(point).setRank(6); break;
            case "Leopard" :     getChessPieceAt(point).setRank(5); break;
            case "Wolf"    :     getChessPieceAt(point).setRank(4); break;
            case "Dog"     :     getChessPieceAt(point).setRank(3); break;
            case "Cat"     :     getChessPieceAt(point).setRank(2); break;
            case "Rat"     :     getChessPieceAt(point).setRank(1); break;

        }
    }

    public Cell[][] getGrid() {
        return grid;
    }
    public PlayerColor getChessPieceOwner(ChessboardPoint point) {
        return getGridAt(point).getPiece().getOwner();
    }

    public boolean isValidMove(ChessboardPoint src, ChessboardPoint dest) {
        if (getChessPieceAt(src) == null || getChessPieceAt(dest) != null) {
            return false;
        }//确保src有棋子，dest无棋子
        return specialMove(src,dest) && densMove(src,dest);
    }
    public boolean densMove(ChessboardPoint src, ChessboardPoint dest){
        if(!isDens(dest)){
            return true;
        } else return (getChessPieceAt(src).getOwner().equals(PlayerColor.RED) && dest.getCol()==3 && dest.getRow()==8)||
                (getChessPieceAt(src).getOwner().equals(PlayerColor.BLUE) && dest.getCol()==3 && dest.getRow()==0);
    }
    public boolean specialMove(ChessboardPoint src, ChessboardPoint dest){//行棋方法，不考虑是否capture
        if(getChessPieceAt(src).getName().equals("Rat")){
            return calculateDistance(src, dest) == 1;//控制棋子只能上下左右移动
        }//老鼠的移动方式（可以下河）

        if(getChessPieceAt(src).getName().equals("Lion")||getChessPieceAt(src).getName().equals("Tiger")){
            if(src.getRow()==dest.getRow()||src.getCol()==dest.getCol()){//jump条件:行或列相等,中间全是河且没有动物
                if(src.getRow()==dest.getRow()){
                    int temp = 0;
                    for (int i = 1 ;i < calculateDistance(src,dest);i++) {
                        ChessboardPoint point = new ChessboardPoint(src.getRow(),Math.min(src.getCol(),dest.getCol())+i);
                        if(isRiver(point) && getChessPieceAt(point)==null){
                            temp+=0;
                        } else {
                            temp+=1;
                        }
                    }
                    if(temp==0 && !isRiver(dest)){return true;}
                }
                if(src.getCol()==dest.getCol()){
                    int temp = 0;
                    for (int i = 1 ;i < calculateDistance(src,dest);i++) {
                        ChessboardPoint point = new ChessboardPoint(Math.min(src.getRow(),dest.getRow())+i,src.getCol());
                        if(isRiver(point) && getChessPieceAt(point)==null){
                            temp+=0;
                        } else {
                            temp+=1;
                        }
                    }
                    if(temp==0 && !isRiver(dest)){return true;}
                }
            }

            //不满足jump条件
            return calculateDistance(src, dest) == 1 && !isRiver(dest);//控制棋子只能上下左右移动,不能下河
        }//老虎狮子可以跳过河流

        else {
            return calculateDistance(src, dest) == 1 && !isRiver(dest);
        }
    }

    public boolean isValidCapture(ChessboardPoint src, ChessboardPoint dest) {

        if (getChessPieceAt(src) == null || getChessPieceAt(dest) == null) {
            return false;
        }//确保两个点都有棋子

        if(!specialMove(src,dest)){
            return false;
        }//行棋方式无效，则返回false

        if (getChessPieceAt(src).getRank()==1 && getChessPieceAt(dest).getRank()==8 && isRiver(src)){
            return false;
        }//河里的rat不能吃大象

        if(getChessPieceAt(dest).getRank()==1 && isRiver(dest) && !isRiver(src)){
            return false;
        }//河里的rat不能被岸上动物吃

        return getChessPieceAt(src).canCapture(getChessPieceAt(dest));
    }
    public  static List<ChessboardPoint> possiblePoints = new ArrayList<>();
    public List<ChessboardPoint> possibleMove(ChessboardPoint point){//可能的移动point

        int row = point.getRow();
        int col = point.getCol();
        ChessboardPoint point1 = new ChessboardPoint(row+1,col);//下
        ChessboardPoint point2 = new ChessboardPoint(row,col+1);//右
        ChessboardPoint point3 = new ChessboardPoint(row,col-1);//左
        ChessboardPoint point4 = new ChessboardPoint(row-1,col);//上
        if(getChessPieceAt(point).getName().equals("Lion" )|| getChessPieceAt(point).getName().equals("Tiger")){
            //是虎和狮
            if(isRiver(point1)){
                point1.setRow(row+4);
            }
            if(isRiver(point2)){
                point2.setCol(col+3);
            }
            if(isRiver(point3)){
                point3.setCol(col-3);
            }
            if(isRiver(point4)){
                point4.setRow(row-4);
            }
        }
        possiblePoints.add(point1);
        possiblePoints.add(point2);
        possiblePoints.add(point3);
        possiblePoints.add(point4);
        for(int i=0;i<possiblePoints.size();i++){
            ChessboardPoint p = possiblePoints.get(i);
            if(p.getRow()<0 || p.getRow()>8 || p.getCol()<0 ||p.getCol()>6){
                possiblePoints.remove(p);
                i--;
                continue;
            }
            if(getChessPieceAt(p) == null && !specialMove(point,p)){
                possiblePoints.remove(p);
                i--;
                continue;
            }
            if(getChessPieceAt(p) != null && !isValidCapture(point,p)){
                possiblePoints.remove(p);
                i--;
            }
        }
        return possiblePoints;
    }
    public List<ChessboardPoint> AI(){
        List<ChessboardPoint> move = new ArrayList<>();//用于储存移动的起点与终点
        ChessboardPoint src;
        ChessboardPoint dest;

        for(int i=0;i<blue.size();i++){
            if(blue.get(i) != null){
                List<ChessboardPoint> PossibleMove = new ArrayList<>(possibleMove(blue.get(i).getCurrentPoint()));
                for(ChessboardPoint p : PossibleMove){
                    if(isValidCapture(blue.get(i).getCurrentPoint(),p) && possibleMove(p).size()!=0){
                        src=p;
                        List<ChessboardPoint> chessMove = new ArrayList<>(possibleMove(src));
                        Random random = new Random();
                        int dir = random.nextInt(chessMove.size());
                        dest=chessMove.get(dir);
                        move.add(src);
                        move.add(dest);
                        return move;
                    }
                }
            }
        }//是否有棋要逃

        for(int i=0;i<red.size();i++){
            if(red.get(i) != null){
                List<ChessboardPoint> PossibleMove = new ArrayList<>(possibleMove(red.get(i).getCurrentPoint()));
                for(ChessboardPoint p : PossibleMove){
                    if(isValidCapture(red.get(i).getCurrentPoint(),p)){
                        src=red.get(i).getCurrentPoint();
                        dest=p;
                        move.add(src);
                        move.add(dest);
                        return move;
                    }
                }
            }
        }//是否有棋可吃别子

        ArrayList<ChessPiece> Red = new ArrayList<>(red);
        boolean havePiece;
        boolean canMove=false;

        do{
            Random random = new Random();
            int index = random.nextInt(Red.size());
            if(Red.get(index)==null){
                havePiece = false;
                Red.remove(index);
            } else {
                havePiece = true;
                List<ChessboardPoint> chessMove = new ArrayList<>(possibleMove(Red.get(index).getCurrentPoint()));

                if(chessMove.size()==0){
                    canMove=false;
                    Red.remove(index);
                } else {
                    canMove=true;
                    for(ChessboardPoint movePoint:chessMove){
                        if(calculateDistance(Red.get(index).getCurrentPoint(),new ChessboardPoint(8,3))>calculateDistance(movePoint,new ChessboardPoint(8,3))){
                            src=Red.get(index).getCurrentPoint();
                            dest=movePoint;
                            move.add(src);
                            move.add(dest);
                            return move;
                        }
                    }
                    src=Red.get(index).getCurrentPoint();
                    dest=chessMove.get(0);
                    move.add(src);
                    move.add(dest);
                    return move;
                }
            }
        } while ((!havePiece || !canMove) && Red.size()!=0);//随机选一个可以动的棋
        return null;
    }
    public void Retract(){//悔棋


        ChessboardPoint src = new ChessboardPoint(steps.get(steps.size()-1)[3],steps.get(steps.size()-1)[4]);
        ChessboardPoint dest = new ChessboardPoint(steps.get(steps.size()-1)[5],steps.get(steps.size()-1)[6]);
        setChessPiece(src, removeChessPiece(dest));
        getChessPieceAt(src).setCurrentPoint(src);
        if(isTrap(src)){
            trapped(src);
        }

        if(steps.get(steps.size()-1)[0]==1){//吃子
            String name=null;
            PlayerColor color;
            switch (steps.get(steps.size()-1)[8]){
                case 1: name = "Elephant"; break;
                case 2: name = "Lion"    ; break;
                case 3: name = "Tiger"   ; break;
                case 4: name = "Leopard" ; break;
                case 5: name = "Wolf"    ; break;
                case 6: name = "Dog"     ; break;
                case 7: name = "Cat"     ; break;
                case 8: name = "Rat"     ; break;
            }
            if (steps.get(steps.size()-1)[7]==0){
                color = PlayerColor.BLUE;
            }else {
                color = PlayerColor.RED;
            }
            grid[dest.getRow()][dest.getCol()].setPiece(new ChessPiece(color, name,steps.get(steps.size()-1)[8],new ChessboardPoint(dest.getRow(),dest.getCol())));
            if(isTrap(dest)){
                trapped(dest);
            }
        }
        steps.remove(steps.size()-1);
    }

    public boolean checkPiece(){
        for(ChessPiece piece: blue){
            switch (piece.getName()){
                case  "Elephant": if(piece.getOriginRank()!=8){return false;}break;
                case  "Lion"    : if(piece.getOriginRank()!=7){return false;}break;
                case  "Tiger"   : if(piece.getOriginRank()!=6){return false;}break;
                case  "Leopard" : if(piece.getOriginRank()!=5){return false;}break;
                case  "Wolf"    : if(piece.getOriginRank()!=4){return false;}break;
                case  "Dog"     : if(piece.getOriginRank()!=3){return false;}break;
                case  "Cat"     : if(piece.getOriginRank()!=2){return false;}break;
                case  "Rat"     : if(piece.getOriginRank()!=1){return false;}break;
                default: return false;
            }
            if(piece.getOriginRank()>8 ||piece.getOriginRank()<1){
                return false;
            }
            if(piece.getRank()!=0 && piece.getRank() != piece.getOriginRank()){
                return false;
            }
        }
        for(ChessPiece piece: red){
            switch (piece.getName()){
                case  "Elephant": if(piece.getOriginRank()!=8){return false;}break;
                case  "Lion"    : if(piece.getOriginRank()!=7){return false;}break;
                case  "Tiger"   : if(piece.getOriginRank()!=6){return false;}break;
                case  "Leopard" : if(piece.getOriginRank()!=5){return false;}break;
                case  "Wolf"    : if(piece.getOriginRank()!=4){return false;}break;
                case  "Dog"     : if(piece.getOriginRank()!=3){return false;}break;
                case  "Cat"     : if(piece.getOriginRank()!=2){return false;}break;
                case  "Rat"     : if(piece.getOriginRank()!=1){return false;}break;
                default: return false;
            }
            if(piece.getOriginRank()>8 ||piece.getOriginRank()<1){
                return false;
            }
            if(piece.getRank()!=0 && piece.getRank() != piece.getOriginRank()){
                return false;
            }
        }
        return true;
    }


}
