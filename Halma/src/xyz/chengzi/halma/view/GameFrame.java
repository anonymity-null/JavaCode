package xyz.chengzi.halma.view;

import xyz.chengzi.halma.controller.GameController;
import xyz.chengzi.halma.listener.MoveCallBack;
import xyz.chengzi.halma.listener.PlayerCallBack;
import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.model.ChessBoardLocation;
import xyz.chengzi.halma.model.Square;
import xyz.chengzi.halma.util.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameFrame extends JFrame implements MoveCallBack, PlayerCallBack {


    private ChessBoardLocation oldLocation;
    private ChessBoardLocation location;
    private final JLabel statusLabel;
    private StringBuilder stringBuilder;

    public GameController getController() {
        return controller;
    }

    private final GameController controller;

    public GameFrame() {
        setTitle("2020 CS102A Project Demo");
        setSize(776, 830);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        stringBuilder = new StringBuilder();

        ChessBoardComponent chessBoardComponent = new ChessBoardComponent(760, 16);
        ChessBoard chessBoard = new ChessBoard(16, this);
        controller = new GameController(chessBoardComponent, chessBoard, this);
        add(chessBoardComponent);

        statusLabel = new JLabel("Sample label");
        statusLabel.setLocation(0, 760);
        statusLabel.setSize(200, 10);
        add(statusLabel);

        JButton save = new JButton("save game");
        save.addActionListener((e) -> {

            Square[][] grid = chessBoard.getGrid();
            int currentPlayer = 1;
            int store[][] = new int[16][16];
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    if (grid[i][j].getPiece() != null) {
                        Color chessColor = grid[i][j].getPiece().getColor();
                        switch (chessColor.toString()) {
                            case "java.awt.Color[r=255,g=0,b=0]":
                                //红方
                                store[j][i] = 1;
                                currentPlayer = 1;
                                break;
                            case "java.awt.Color[r=0,g=255,b=0]":
                                //绿方
                                store[j][i] = 2;
                                currentPlayer = 2;
                                break;
                            case "java.awt.Color[r=0,g=0,b=255]":
                                //蓝方
                                store[j][i] = 3;
                                currentPlayer = 3;
                                break;
                            case "java.awt.Color[r=255,g=255,b=0]":
                                //黄方
                                store[j][i] = 4;
                                currentPlayer = 4;
                                break;
                        }
                    }
                }
            }

            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    System.out.print(store[i][j] + "|");
                    stringBuilder.append(store[i][j]);
                }
                System.out.println("\n");
            }
            System.out.println("currentPlayer+" + currentPlayer);
            int a = controller.getA();
            stringBuilder.append(a);
            stringBuilder.append(currentPlayer);
            System.out.println("a===" + a);
            try {
                FileUtil.saveFile(stringBuilder.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.println("save game");

        });
        save.setLocation(200, 760);
        save.setSize(100, 30);
        add(save);

        JButton reStart1 = new JButton("restart1");
        reStart1.addActionListener((e) -> {
            System.out.println("restart a game with 2 players");
            controller.restartGame1();

        });

        JButton regret = new JButton("regret");
        regret.addActionListener((e) -> {
            controller.regret();
        });
        regret.setLocation(300, 760);
        regret.setSize(100, 20);
        add(regret);

        reStart1.setLocation(400, 760);
        reStart1.setSize(60, 12);
        add(reStart1);



        JButton reStart2 = new JButton("restart2");
        reStart2.addActionListener((e) -> {
            System.out.println("restart a game with 4 players");
            controller.restartGame2();
        });
        reStart2.setLocation(500, 760);
        reStart2.setSize(60, 12);
        add(reStart2);

        JButton button = new JButton("...");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Button clicked!"));
        button.setLocation(740, 760);
        button.setSize(20, 12);
        add(button);
    }

    /**
     * 获取每次棋子移动时的坐标
     *
     * @param src  移动后的位置
     * @param dest 被移动的棋子
     */
    @Override
    public void getPosition(ChessBoardLocation src, ChessBoardLocation dest) {
        oldLocation = dest;
        location = src;

        System.out.println("GameFrameOldPosition" + dest.getRow() + "," + dest.getColumn());
        System.out.println("GameFramePosition" + src.getRow() + "," + src.getColumn());
    }

    @Override
    public void setPlayer(Color player) {
        System.out.println("colorToString" + player.toString());
        switch (player.toString()) {
            case "java.awt.Color[r=255,g=0,b=0]":
                //红方
                statusLabel.setText("红方");

                break;
            case "java.awt.Color[r=0,g=255,b=0]":
                //绿方
                statusLabel.setText("绿方");

                break;
            case "java.awt.Color[r=0,g=0,b=255]":
                //蓝方
                statusLabel.setText("蓝方");

                break;
            case "java.awt.Color[r=255,g=255,b=0]":
                //黄方
                statusLabel.setText("黄方");

                break;
        }

    }
}
