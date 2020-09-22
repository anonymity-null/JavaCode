package xyz.chengzi.halma.model;

import xyz.chengzi.halma.MoveStore;
import xyz.chengzi.halma.bean.Move;
import xyz.chengzi.halma.listener.GameListener;
import xyz.chengzi.halma.listener.Listenable;
import xyz.chengzi.halma.view.GameFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChessBoard implements Listenable<GameListener> {
    private List<GameListener> listenerList = new ArrayList<>();

    public Square[][] getGrid() {
        return grid;
    }

    public Square[][] grid;
    private int dimension;
    private GameFrame gameFrame;

    public ChessBoard(int dimension,GameFrame gameFrame) {
        this.gameFrame=gameFrame;
        this.grid = new Square[dimension][dimension];
        this.dimension = dimension;

        initGrid();
    }

    private void initGrid() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                grid[i][j] = new Square(new ChessBoardLocation(i, j));
            }
        }
    }

    public void placeInitialPieces1() {
        for(int i = 0 ; i < dimension ; i++){
            for(int j = 0 ; j < dimension ; j++){
                grid[i][j].setPiece(null);
            }
        }
        // TODO: This is only a demo implementation
        grid[0][0].setPiece(new ChessPiece(Color.RED));
        grid[0][1].setPiece(new ChessPiece(Color.RED));
        grid[0][2].setPiece(new ChessPiece(Color.RED));
        grid[0][3].setPiece(new ChessPiece(Color.RED));
        grid[0][4].setPiece(new ChessPiece(Color.RED));
        grid[1][0].setPiece(new ChessPiece(Color.RED));
        grid[1][1].setPiece(new ChessPiece(Color.RED));
        grid[1][2].setPiece(new ChessPiece(Color.RED));
        grid[1][3].setPiece(new ChessPiece(Color.RED));
        grid[1][4].setPiece(new ChessPiece(Color.RED));
        grid[2][0].setPiece(new ChessPiece(Color.RED));
        grid[2][1].setPiece(new ChessPiece(Color.RED));
        grid[2][2].setPiece(new ChessPiece(Color.RED));
        grid[2][3].setPiece(new ChessPiece(Color.RED));
        grid[3][0].setPiece(new ChessPiece(Color.RED));
        grid[3][1].setPiece(new ChessPiece(Color.RED));
        grid[3][2].setPiece(new ChessPiece(Color.RED));
        grid[4][0].setPiece(new ChessPiece(Color.RED));
        grid[4][1].setPiece(new ChessPiece(Color.RED));

        grid[dimension - 1][dimension - 1].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 1][dimension - 2].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 1][dimension - 3].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 1][dimension - 4].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 1][dimension - 5].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 2][dimension - 1].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 2][dimension - 2].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 2][dimension - 3].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 2][dimension - 4].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 2][dimension - 5].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 3][dimension - 1].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 3][dimension - 2].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 3][dimension - 3].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 3][dimension - 4].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 4][dimension - 1].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 4][dimension - 2].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 4][dimension - 3].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 5][dimension - 1].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 5][dimension - 2].setPiece(new ChessPiece(Color.GREEN));

        listenerList.forEach(listener -> listener.onChessBoardReload(this));
    }
    public void loadGame(int store[][]){
        for(int i = 0 ; i < 16 ; i++){
            for(int j = 0 ; j < 16 ; j++){
                switch (store[i][j]){
                    case 0:
                        grid[i][j].setPiece(null);
                        break;
                    case 1:
                        grid[i][j].setPiece(new ChessPiece(Color.RED));
                        break;
                    case 2:
                        grid[i][j].setPiece(new ChessPiece(Color.GREEN));
                        break;
                    case 3:
                        grid[i][j].setPiece(new ChessPiece(Color.BLUE));
                        break;
                    case 4:
                        grid[i][j].setPiece(new ChessPiece(Color.YELLOW));
                        break;
                }
            }
        }
        listenerList.forEach(listener -> listener.onChessBoardReload(this));
    }
    public void placeInitialPieces2() {
        for(int i = 0 ; i < dimension ; i++){
            for(int j = 0 ; j < dimension ; j++){
                grid[i][j].setPiece(null);
            }
        }
        // TODO: This is only a demo implementation
        grid[0][0].setPiece(new ChessPiece(Color.RED));
        grid[0][1].setPiece(new ChessPiece(Color.RED));
        grid[0][2].setPiece(new ChessPiece(Color.RED));
        grid[0][3].setPiece(new ChessPiece(Color.RED));
        grid[1][0].setPiece(new ChessPiece(Color.RED));
        grid[1][1].setPiece(new ChessPiece(Color.RED));
        grid[1][2].setPiece(new ChessPiece(Color.RED));
        grid[1][3].setPiece(new ChessPiece(Color.RED));
        grid[2][0].setPiece(new ChessPiece(Color.RED));
        grid[2][1].setPiece(new ChessPiece(Color.RED));
        grid[2][2].setPiece(new ChessPiece(Color.RED));
        grid[3][0].setPiece(new ChessPiece(Color.RED));
        grid[3][1].setPiece(new ChessPiece(Color.RED));

        grid[dimension-1][0].setPiece(new ChessPiece(Color.BLUE));
        grid[dimension-1][1].setPiece(new ChessPiece(Color.BLUE));
        grid[dimension-1][2].setPiece(new ChessPiece(Color.BLUE));
        grid[dimension-1][3].setPiece(new ChessPiece(Color.BLUE));
        grid[dimension-2][0].setPiece(new ChessPiece(Color.BLUE));
        grid[dimension-2][1].setPiece(new ChessPiece(Color.BLUE));
        grid[dimension-2][2].setPiece(new ChessPiece(Color.BLUE));
        grid[dimension-2][3].setPiece(new ChessPiece(Color.BLUE));
        grid[dimension-3][0].setPiece(new ChessPiece(Color.BLUE));
        grid[dimension-3][1].setPiece(new ChessPiece(Color.BLUE));
        grid[dimension-3][2].setPiece(new ChessPiece(Color.BLUE));
        grid[dimension-4][0].setPiece(new ChessPiece(Color.BLUE));
        grid[dimension-4][1].setPiece(new ChessPiece(Color.BLUE));

        grid[0][dimension - 1].setPiece(new ChessPiece(Color.YELLOW));
        grid[0][dimension - 2].setPiece(new ChessPiece(Color.YELLOW));
        grid[0][dimension - 3].setPiece(new ChessPiece(Color.YELLOW));
        grid[0][dimension - 4].setPiece(new ChessPiece(Color.YELLOW));
        grid[1][dimension - 1].setPiece(new ChessPiece(Color.YELLOW));
        grid[1][dimension - 2].setPiece(new ChessPiece(Color.YELLOW));
        grid[1][dimension - 3].setPiece(new ChessPiece(Color.YELLOW));
        grid[1][dimension - 4].setPiece(new ChessPiece(Color.YELLOW));
        grid[2][dimension - 1].setPiece(new ChessPiece(Color.YELLOW));
        grid[2][dimension - 2].setPiece(new ChessPiece(Color.YELLOW));
        grid[2][dimension - 3].setPiece(new ChessPiece(Color.YELLOW));
        grid[3][dimension - 1].setPiece(new ChessPiece(Color.YELLOW));
        grid[3][dimension - 2].setPiece(new ChessPiece(Color.YELLOW));

        grid[dimension - 1][dimension - 1].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 1][dimension - 2].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 1][dimension - 3].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 1][dimension - 4].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 2][dimension - 1].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 2][dimension - 2].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 2][dimension - 3].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 2][dimension - 4].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 3][dimension - 1].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 3][dimension - 2].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 3][dimension - 3].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 4][dimension - 1].setPiece(new ChessPiece(Color.GREEN));
        grid[dimension - 4][dimension - 2].setPiece(new ChessPiece(Color.GREEN));


        listenerList.forEach(listener -> listener.onChessBoardReload(this));
    }

    public Square getGridAt(ChessBoardLocation location) {
        System.out.printf("(x=%d,y=%d)",location.getRow(),location.getColumn());
        return grid[location.getRow()][location.getColumn()];
    }

    public ChessPiece getChessPieceAt(ChessBoardLocation location) {
        return getGridAt(location).getPiece();
    }

    public void setChessPieceAt(ChessBoardLocation location, ChessPiece piece) {
        getGridAt(location).setPiece(piece);
        listenerList.forEach(listener -> listener.onChessPiecePlace(location, piece));
    }

    public ChessPiece removeChessPieceAt(ChessBoardLocation location) {
        ChessPiece piece = getGridAt(location).getPiece();
        System.out.println("piece"+piece.getColor());
        getGridAt(location).setPiece(null);
        listenerList.forEach(listener -> listener.onChessPieceRemove(location));
        return piece;
    }

    public void regretChessPiece(ChessBoardLocation src, ChessBoardLocation dest) {

        setChessPieceAt(dest, removeChessPieceAt(src));

        //传值给GameFrame让他可以保存游戏
        gameFrame.getPosition(src,dest);
    }

    public void moveChessPiece(ChessBoardLocation src, ChessBoardLocation dest) {
        if (!isValidMove(src, dest)) {
            throw new IllegalArgumentException("Illegal halma move");
        }
        setChessPieceAt(dest, removeChessPieceAt(src));

        //传值给GameFrame让他可以保存游戏
        gameFrame.getPosition(src,dest);
    }


    public int getDimension() {
        return dimension;
    }

    public boolean isValidMove(ChessBoardLocation src, ChessBoardLocation dest) {
        if (getChessPieceAt(src) == null || getChessPieceAt(dest) != null) {
            return false;
        }
        int srcRow = src.getRow(), srcCol = src.getColumn(), destRow = dest.getRow(), destCol = dest.getColumn();
        int rowDistance = destRow - srcRow, colDistance = destCol - srcCol;

        int[][] pieces = new int[getDimension()][getDimension()];
        for (int i = 0; i < getDimension(); i++) {
            for (int j = 0; j < getDimension(); j++) {
                if (grid[j][i].getPiece() == null) {
                    pieces[i][j] = 0;
                } else {
                    pieces[i][j] = 1;
                }
            }
        }
        for (int i = 0; i <dimension ; i++) {
            for (int j = 0; j <dimension ; j++) {
                System.out.print(pieces[i][j]);
            }
            System.out.println();
        }

        if (Math.abs(rowDistance) <= 1 && Math.abs(colDistance) <= 1) {
            if (rowDistance == 0 && colDistance == 0) {
                return false;
            } else {
                return true;
            }
        }else if (Math.abs(rowDistance)==2&&Math.abs(colDistance)==2){
//            if (pieces[][]==1) {
            if (pieces[(srcCol+destCol)/2][(srcRow+destRow)/2]==1){
                System.out.println(pieces[(srcCol+destCol)/2][(srcRow+destRow)/2]);
                System.out.printf("%d %d",(srcCol+destCol)/2,(srcRow+destRow)/2);
                return true;
            }else{
                System.out.println(pieces[(srcCol+destCol)/2][(srcRow+destRow)/2]);
                System.out.printf("%d %d",(srcCol+destCol)/2,(srcRow+destRow)/2);
                return false;
            }
        }else if(Math.abs(rowDistance)==2&&Math.abs(colDistance)==0){
            if (pieces[srcCol][(srcRow+destRow)/2]==1) {
                System.out.println(pieces[srcCol][(srcRow+destRow)/2]);
                System.out.printf("%d %d",srcCol,(srcRow+destRow)/2);
                return true;
            }else{
                System.out.println(pieces[srcCol][(srcRow+destRow)/2]);
                System.out.printf("%d %d",srcCol,(srcRow+destRow)/2);
                return false;
            }
        }else if (Math.abs(rowDistance)==0&&Math.abs(colDistance)==2){
            if (pieces[(srcCol+destCol)/2][srcRow]==1) {
                System.out.println(pieces[(srcCol+destCol)/2][srcRow]);
                System.out.printf("%d %d",(srcCol+destCol)/2,srcRow);
                return true;
            }else{
                System.out.println(pieces[(srcCol+destCol)/2][srcRow]);
                System.out.printf("%d %d",(srcCol+destCol)/2,srcRow);
                return false;
            }
        }else{
            System.out.println(pieces[(srcCol+destCol)/2][(srcRow+destRow)/2]);
            System.out.printf("%d &d",(srcCol+destCol)/2,(srcRow+destRow)/2);
            return false;
        }
    }


    @Override
    public void registerListener(GameListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void unregisterListener(GameListener listener) {
        listenerList.remove(listener);
    }
}
