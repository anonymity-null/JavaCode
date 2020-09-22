package xyz.chengzi.halma.model;

import xyz.chengzi.halma.listener.GameListener;
import xyz.chengzi.halma.listener.Listenable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChessBoard implements Listenable<GameListener> {
    private List<GameListener> listenerList = new ArrayList<>();
    private Square[][] grid;
    private int dimension;
    private static final Color orange = new Color(0xFFB553);
    private static final Color green = new Color(0x08FF17);

    public static Color getOrange() {
        return orange;
    }

    public static Color getGreen() {
        return green;
    }

    public ChessBoard(int dimension) {
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

    public void placeInitialPieces() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
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

    public void placeInitialPieces2() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
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

        grid[0][dimension - 1].setPiece(new ChessPiece(Color.orange));
        grid[0][dimension - 2].setPiece(new ChessPiece(Color.orange));
        grid[0][dimension - 3].setPiece(new ChessPiece(Color.orange));
        grid[0][dimension - 4].setPiece(new ChessPiece(Color.orange));
        grid[1][dimension - 1].setPiece(new ChessPiece(Color.orange));
        grid[1][dimension - 2].setPiece(new ChessPiece(Color.orange));
        grid[1][dimension - 3].setPiece(new ChessPiece(Color.orange));
        grid[1][dimension - 4].setPiece(new ChessPiece(Color.orange));
        grid[2][dimension - 1].setPiece(new ChessPiece(Color.orange));
        grid[2][dimension - 2].setPiece(new ChessPiece(Color.orange));
        grid[2][dimension - 3].setPiece(new ChessPiece(Color.orange));
        grid[3][dimension - 1].setPiece(new ChessPiece(Color.orange));
        grid[3][dimension - 2].setPiece(new ChessPiece(Color.orange));

        grid[dimension - 1][0].setPiece(new ChessPiece(Color.blue));
        grid[dimension - 1][1].setPiece(new ChessPiece(Color.blue));
        grid[dimension - 1][2].setPiece(new ChessPiece(Color.blue));
        grid[dimension - 1][3].setPiece(new ChessPiece(Color.blue));
        grid[dimension - 2][0].setPiece(new ChessPiece(Color.blue));
        grid[dimension - 2][1].setPiece(new ChessPiece(Color.blue));
        grid[dimension - 2][2].setPiece(new ChessPiece(Color.blue));
        grid[dimension - 2][3].setPiece(new ChessPiece(Color.blue));
        grid[dimension - 3][0].setPiece(new ChessPiece(Color.blue));
        grid[dimension - 3][1].setPiece(new ChessPiece(Color.blue));
        grid[dimension - 3][2].setPiece(new ChessPiece(Color.blue));
        grid[dimension - 4][0].setPiece(new ChessPiece(Color.blue));
        grid[dimension - 4][1].setPiece(new ChessPiece(Color.blue));

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
        getGridAt(location).setPiece(null);
        listenerList.forEach(listener -> listener.onChessPieceRemove(location));
        return piece;
    }

    public void moveChessPiece(ChessBoardLocation src, ChessBoardLocation dest) {
        if (!isValidMove(src, dest)) {
            throw new IllegalArgumentException("Illegal halma move");
        }
        setChessPieceAt(dest, removeChessPieceAt(src));
    }

    public int getDimension() {
        return dimension;
    }

    public boolean isValidMove(ChessBoardLocation src, ChessBoardLocation dest) {
        System.out.printf("[%d,%d] to [%d,%d]\n", src.getRow(), src.getColumn(), dest.getRow(), dest.getColumn());
//八个方位的棋子
        ChessBoardLocation mediumup = new ChessBoardLocation(src.getRow() - 1, src.getRow());
        ChessBoardLocation mediumdown = new ChessBoardLocation(src.getRow() - 1, src.getRow());
        ChessBoardLocation mediumright = new ChessBoardLocation(src.getRow() - 1, src.getRow());
        ChessBoardLocation mediumleft = new ChessBoardLocation(src.getRow() - 1, src.getRow());
        ChessBoardLocation mediumupright = new ChessBoardLocation(src.getRow() - 1, src.getRow());
        ChessBoardLocation mediumupleft = new ChessBoardLocation(src.getRow() - 1, src.getRow());
        ChessBoardLocation mediumdownright = new ChessBoardLocation(src.getRow() - 1, src.getRow());
        ChessBoardLocation mediumdownleft = new ChessBoardLocation(src.getRow() - 1, src.getRow());

        int srcRow = src.getRow(), srcCol = src.getColumn(), destRow = dest.getRow(), destCol = dest.getColumn();
        int rowDistance = destRow - srcRow, colDistance = destCol - srcCol;

        if (getChessPieceAt(src) == null || getChessPieceAt(dest) != null) {
            //不能，初始化地点时空，目标地点不是空
            return false;
        }
        if (rowDistance != 0 && colDistance != 0 && Math.abs((double) rowDistance / colDistance) != 1.0) {
            //只能走八个方向
            return false;
        }
//        return Math.abs(rowDistance) <= 1 && Math.abs(colDistance) <= 1;
        else {
            if ((Math.abs(rowDistance) <= 1 && Math.abs(colDistance) <= 1)) {
                // 一个棋子的一周可以走
                return true;
            } else {
                //不在一周差两行
                if ((Math.abs(colDistance) == 0 && Math.abs(rowDistance) == 2)) {
                    if (mediumup == null) {
                        return false;
                    } else if (mediumup != null) {
                        return true;
                    }
                    if (mediumdown == null) {
                        return false;
                    } else if (mediumdown != null) {
                        return true;
                    }
                }
                if ((Math.abs(colDistance) == 2 && Math.abs(rowDistance) == 0)) {
                    if (mediumright == null) {
                        return false;
                    } else if (mediumright != null) {
                        return true;
                    }
                    if (mediumleft == null) {
                        return false;
                    } else if (mediumleft != null) {
                        return true;
                    }
                }
                if ((Math.abs(colDistance) == 2 && Math.abs(rowDistance) == 2)) {
                    if (mediumupright == null) {
                        return false;
                    } else if (mediumupright != null) {
                        return true;
                    }
                    if (mediumupleft == null) {
                        return false;
                    } else if (mediumupleft != null) {
                        return true;
                    }
                    if (mediumdownright == null) {
                        return false;
                    } else if (mediumdownright != null) {
                        return true;
                    }
                    if (mediumdownleft == null) {
                        return false;
                    } else if (mediumdownleft != null) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
//先写一个返回值是boolean 的方法check
    //位置传参数掉check


    @Override
    public void registerListener(GameListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void unregisterListener(GameListener listener) {
        listenerList.remove(listener);
    }
}
