package xyz.chengzi.halma.controller;

import xyz.chengzi.halma.listener.InputListener;
import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.model.ChessBoardLocation;
import xyz.chengzi.halma.model.ChessPiece;
import xyz.chengzi.halma.model.Square;
import xyz.chengzi.halma.view.ChessBoardComponent;
import xyz.chengzi.halma.view.ChessComponent;
import xyz.chengzi.halma.view.SquareComponent;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GameController implements InputListener {
    private ChessBoardComponent view;
    private ChessBoard model;

    private ChessBoardLocation selectedLocation;
    private Color currentPlayer;

    private JLabel jLabel;
    int a = -1;
    public GameController(ChessBoardComponent chessBoardComponent, ChessBoard chessBoard) {
        this.view = chessBoardComponent;
        this.model = chessBoard;

        this.currentPlayer = Color.RED;
        view.registerListener(this);
        model.registerListener(view);
        model.placeInitialPieces();
    }

    public void restart(){
        model.placeInitialPieces();
        this.a = -1;
        this.currentPlayer = Color.RED;

    }
    public void restart2() {
        model.placeInitialPieces2();
        this.a = 0;
        this.currentPlayer = Color.RED;
    }
//    public Color nextPlayer() {
//        player.setText(Color2String(currentPlayer));
//        return currentPlayer = currentPlayer == Color.RED ? Color.GREEN : Color.RED;
//    }

    public ChessBoardLocation getSelectedLocation() {
        return selectedLocation;
    }

    public void setSelectedLocation(ChessBoardLocation location) {
        this.selectedLocation = location;
    }

    public void resetSelectedLocation() {
        setSelectedLocation(null);
    }

    public boolean hasSelectedLocation() {
        return selectedLocation != null;
    }

    public Color nextPlayer() {
        return currentPlayer = currentPlayer == Color.RED ? Color.GREEN : Color.RED;
    }

    public void setjLabel(JLabel jLabel){
        this.jLabel =jLabel ;
    }

    public Color nextPlayers1() {
        ++this.a;
        return this.currentPlayer = this.currentPlayer == Color.RED ? Color.BLUE : Color.RED;
    }

    public Color nextPlayers2() {
        ++this.a;
        return this.currentPlayer = this.currentPlayer == Color.BLUE ? Color.GREEN : Color.BLUE;
    }

    public Color nextPlayers3() {
        ++this.a;
        return this.currentPlayer = this.currentPlayer == Color.GREEN ? Color.orange : Color.GREEN;
    }

    public Color nextPlayers4() {
        ++this.a;
        return this.currentPlayer = this.currentPlayer == Color.orange ? Color.RED : Color.orange;
    }

    public Color getCurrentPlayer(){
        return currentPlayer;
    }

    public String strCurrentPlayer(Color currentPlayer){
        if(currentPlayer == Color.RED){
            return "red's turn";
        }
        else if(currentPlayer == Color.orange){
            return "orange's turn";
        }
        else if(currentPlayer == Color.blue){
            return "blue's turn";
        }
        else if(currentPlayer == Color.GREEN){
            return "green's turn";
        }
        else{
            return "null";
        }
    }


    public void saveGameToFile(String path) throws IOException {
        //how to check the Square?
        Square[][] gamePieces = new Square [16][16];
        //[表情]ange to getSize
        for (int i = 0; i <16 ; i++) {
            for (int j = 0; j < 16; j++) {
                //gamePieces[i][j] =
            }

        }
        int [][] saveGame = new int[16][16];
        BufferedWriter bf = new BufferedWriter(new FileWriter(path));
        for (int i = 0; i < 16; i++) {
            //[表情]ange to getSize
            for (int j = 0; j <16 ; j++) {
                if(gamePieces[i][j].getPiece().getColor().equals(Color.RED)){
                    saveGame[i][j] = 1;
                }else if(gamePieces[i][j].getPiece().getColor().equals(Color.GREEN)){
                    saveGame[i][j] = 2;
                }else if(gamePieces[i][j].getPiece().getColor().equals(Color.orange)){
                    saveGame[i][j] = 3;
                }else if(gamePieces[i][j].getPiece().getColor().equals(Color.blue)){
                    saveGame[i][j] = 4;
                }else{
                    saveGame[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                bf.write(saveGame[i][j]+48);
            }
            System.out.println();
        }
        bf.write(0);
    }
    @Override
    public void onPlayerClickSquare(ChessBoardLocation location, SquareComponent component) {
        if (hasSelectedLocation() && model.isValidMove(getSelectedLocation(), location)) {
            model.moveChessPiece(selectedLocation, location);
            this.resetSelectedLocation();
            if (this.a ==-1) {
                this.nextPlayer();
            } else if (this.a % 4 == 0) {
                this.nextPlayers1();
            } else if (this.a % 4 == 1) {
                this.nextPlayers2();
            } else if (this.a % 4 == 2) {
                this.nextPlayers3();
            } else {
                this.nextPlayers4();
            }
        }
        jLabel.setText(strCurrentPlayer(currentPlayer));
    }


    @Override
    public void onPlayerClickChessPiece(ChessBoardLocation location, ChessComponent component) {
        ChessPiece piece = model.getChessPieceAt(location);
        if (piece.getColor() == currentPlayer && (!hasSelectedLocation() || location.equals(getSelectedLocation()))) {
            if (!hasSelectedLocation()) {
                setSelectedLocation(location);
            } else {
                resetSelectedLocation();
            }
            component.setSelected(!component.isSelected());
            component.repaint();
        }
    }
}
