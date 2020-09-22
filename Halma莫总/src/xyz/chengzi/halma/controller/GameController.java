package xyz.chengzi.halma.controller;

import java.awt.Color;
import java.time.LocalDate;

import xyz.chengzi.halma.MoveStore;
import xyz.chengzi.halma.bean.Move;
import xyz.chengzi.halma.listener.InputListener;
import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.model.ChessBoardLocation;
import xyz.chengzi.halma.model.ChessPiece;
import xyz.chengzi.halma.view.ChessBoardComponent;
import xyz.chengzi.halma.view.ChessComponent;
import xyz.chengzi.halma.view.GameFrame;
import xyz.chengzi.halma.view.SquareComponent;

import javax.swing.*;

public class GameController extends JFrame implements InputListener {
    private ChessBoardComponent view;
    private ChessBoard model;
    private ChessBoardLocation selectedLocation;
    private Color currentPlayer;
    private JLabel player;

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public String strCurrentPlayer(Color currentPlayer){
        if(currentPlayer == Color.RED){
            return "red's turn";
        }
        else if(currentPlayer == Color.BLUE){
            return "blues's turn";
        }
        else if(currentPlayer == Color.GREEN){
            return "green's turn";
        }
        else if(currentPlayer == Color.YELLOW){
            return "yellow's turn";
        }
        else{
            return "null";
        }
    }

    public void setPlayer(JLabel player) {
        this.player = player;
    }

    public int getA() {
        return a;
    }

    public int a = -1;

    public Color getColor() {
        return color;
    }

    private Color color;
    private GameFrame gameFrame;

    public GameController(ChessBoardComponent chessBoardComponent, ChessBoard chessBoard,GameFrame gameFrame) {
        this.view = chessBoardComponent;
        this.model = chessBoard;
        this.gameFrame=gameFrame;
        this.currentPlayer = Color.RED;
        this.view.registerListener(this);
        this.model.registerListener(this.view);
        this.model.placeInitialPieces1();
    }

    public void restartGame1() {
        this.model.placeInitialPieces1();
        this.a = -1;
        this.currentPlayer = Color.RED;
    }

    public void loadGame(int[][] store,int a,int currentPlayer){
        this.model.loadGame(store);
        this.a=a;
        System.out.println("a到底是多少"+this.a);
        switch (currentPlayer){
            case 1:
                this.currentPlayer=Color.RED;
                break;
            case 2:
                this.currentPlayer=Color.GREEN;
                break;
            case 3:
                this.currentPlayer=Color.BLUE;
                break;
            case 4:
                this.currentPlayer=Color.YELLOW;
                break;
        }

    }

    public void restartGame2() {
        this.model.placeInitialPieces2();
        this.a = 0;
        this.currentPlayer = Color.RED;
    }

    public ChessBoardLocation getSelectedLocation() {
        return this.selectedLocation;
    }

    public void setSelectedLocation(ChessBoardLocation location) {
        this.selectedLocation = location;
    }

    public void resetSelectedLocation() {
        this.setSelectedLocation((ChessBoardLocation)null);
    }

    public boolean hasSelectedLocation() {
        return this.selectedLocation != null;
    }

    public Color nextPlayer1() {
        return this.currentPlayer = this.currentPlayer == Color.RED ? Color.GREEN : Color.RED;
    }

    public Color nextPlayers1() {
//        ++this.a;
        return this.currentPlayer = this.currentPlayer == Color.RED ? Color.BLUE : Color.RED;
    }

    public Color nextPlayers2() {
//        ++this.a;
        return this.currentPlayer = this.currentPlayer == Color.BLUE ? Color.GREEN : Color.BLUE;
    }

    public Color nextPlayers3() {
//        ++this.a;
        return this.currentPlayer = this.currentPlayer == Color.GREEN ? Color.YELLOW : Color.GREEN;
    }

    public Color nextPlayers4() {
//        ++this.a;
        return this.currentPlayer = this.currentPlayer == Color.YELLOW ? Color.RED : Color.YELLOW;
    }

    public void onPlayerClickSquare(ChessBoardLocation location, SquareComponent component) {
        System.out.println("a的值等于"+a);
        JButton nextPlayer = new JButton("nextPlayer");
        nextPlayer.addActionListener((e) -> {
            nextPlay();
        });
        nextPlayer.setLocation(100,600);
        nextPlayer.setSize(100,12);
        add(nextPlayer);
        if (this.hasSelectedLocation() && this.model.isValidMove(this.getSelectedLocation(), location)) {
            this.model.moveChessPiece(this.selectedLocation, location);
            MoveStore.moveList.add(new Move(color,this.selectedLocation,location));
            this.resetSelectedLocation();
//            if (this.a == -1) {
//                color = this.nextPlayer1();
//            } else if (this.a % 4 == 0) {
//                color=this.nextPlayers1();
//            } else if (this.a % 4 == 1) {
//                color=this.nextPlayers2();
//            } else if (this.a % 4 == 2) {
//                color=this.nextPlayers3();
//            } else {
//                color=this.nextPlayers4();
//            }
        }
    }
    public void regret(){
        if (MoveStore.moveList.size()>0){
            System.out.println("初始的list"+MoveStore.moveList.size());
            Move move = MoveStore.moveList.get(MoveStore.moveList.size() - 1);
            System.out.println(move.getLocation().getColumn());
            System.out.println(move.getOldLocation().getColumn());
            this.model.regretChessPiece(move.getLocation(), move.getOldLocation());
            MoveStore.moveList.remove(MoveStore.moveList.size()-1);
            System.out.println("移除后的list"+MoveStore.moveList.size());
        }
    }

    public void nextPlay(){
        if (this.a == -1) {
            color = this.nextPlayer1();
        } else if (this.a % 4 == 0) {
            a++;
            color=this.nextPlayers1();
        } else if (this.a % 4 == 1) {
            a++;
            color=this.nextPlayers2();
        } else if (this.a % 4 == 2) {
            a++;
            color=this.nextPlayers3();
        } else {
            a++;
            color=this.nextPlayers4();
        }
        player.setText(strCurrentPlayer(getCurrentPlayer()));
    }

    public void onPlayerClickChessPiece(ChessBoardLocation location, ChessComponent component) {
        ChessPiece piece = this.model.getChessPieceAt(location);
        if (piece.getColor() == this.currentPlayer && (!this.hasSelectedLocation() || location.equals(this.getSelectedLocation()))) {
            if (!this.hasSelectedLocation()) {
                this.setSelectedLocation(location);
            } else {
                this.resetSelectedLocation();
            }

            component.setSelected(!component.isSelected());
            component.repaint();
        }

    }

}
