package model;


public class ChessPiece implements Comparable<ChessPiece>{
    // the owner of the chess
    private PlayerColor owner;
    private String name;
    private int rank;
    private  int originRank;
    private ChessboardPoint currentPoint;

    public void setOriginRank(int originRank) {
        this.originRank = originRank;
    }

    public ChessboardPoint getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(ChessboardPoint currentPoint) {
        this.currentPoint = currentPoint;
    }



    public int compareTo(ChessPiece piece) {
        if(this.originRank>piece.originRank){
            return 1;
        } else if(this.originRank<piece.originRank){
            return -1;
        }
        return 0;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }



    public int getOriginRank() {
        return originRank;
    }



    public ChessPiece(PlayerColor owner, String name, int rank, ChessboardPoint currentPoint) {
        this.currentPoint = currentPoint;
        this.owner = owner;
        this.name = name;
        this.rank = rank;
        this.originRank = rank;
    }

    public boolean canCapture(ChessPiece target) {
        if(target.getOwner().equals(owner)){
            return false;
        }
        if(rank==1){
            if(target.rank==1||target.rank==8){
                return true;
            }
        } else if (rank!=1 && rank!=8) {
            if(target.rank<=this.rank){
                return true;
            }
        } else if(rank==8) {
            if(target.rank<=this.rank&&target.rank!=1){
                return true;
            }
        }
        return false;
    }//可以捕获方法，不考虑河道影响，只考虑等级关系及不能吃自己的棋（包括特殊等级捕食关系）

    public String getName() {
        return name;
    }

    public PlayerColor getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return rank +String.valueOf(originRank)+ currentPoint.getRow() + currentPoint.getCol() +owner.toString()+name;
    }

}