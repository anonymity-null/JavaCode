package xyz.chengzi.halma;

import xyz.chengzi.halma.controller.GameController;
import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.view.ChessBoardComponent;
import xyz.chengzi.halma.view.GameFrame;
import xyz.chengzi.halma.view.StartMenu;

import javax.swing.*;

public class Halma {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StartMenu startMenu=new StartMenu();
            startMenu.setVisible(true);
            //GameFrame mainFrame = new GameFrame();
           // mainFrame.setVisible(true);
        });
    }
}
