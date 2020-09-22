package xyz.chengzi.halma.view;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import xyz.chengzi.halma.controller.GameController;

import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.model.ChessBoardLocation;
import xyz.chengzi.halma.model.Square;
import xyz.chengzi.halma.util.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameFrame extends JFrame  {


    private ChessBoardLocation oldLocation;
    private ChessBoardLocation location;
    private  JLabel statusLabel;
    private StringBuilder stringBuilder;

    public GameController getController() {
        return controller;
    }

    private final GameController controller;

    public GameFrame() {
        setTitle("2020 CS102A Project Demo");
        setSize(1000, 900);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);


        ChessBoardComponent chessBoardComponent = new ChessBoardComponent(800, 16);
        ChessBoard chessBoard = new ChessBoard(16, this);
        controller = new GameController(chessBoardComponent, chessBoard, this);
        add(chessBoardComponent);

        final JFXPanel fxPanel = new JFXPanel(); // to initialize JavaFx
        File file = new File("huhu/Halam/Christa Wells - You Are My Defense.mp3");
        final Media media = new Media(file.toURI().toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaPlayer.play();
        JButton playMusic=new JButton("Play Music");
        playMusic.addActionListener((e) -> {
            mediaPlayer.play();
        });
        JButton stopMusic=new JButton("Stop Music");
        stopMusic.addActionListener((e) -> {
            mediaPlayer.stop();
        });

        playMusic.setLocation(830,75);
        playMusic.setSize(120,40);
        playMusic.setBackground(new Color(246,203,200));
        add(playMusic);

        stopMusic.setLocation(830,135);
        stopMusic.setSize(120,40);
        stopMusic.setBackground(new Color(246,203,200));
        add(stopMusic);


        statusLabel = new JLabel("开始游戏");
        statusLabel.setLocation(830, 750);
        statusLabel.setSize(200, 40);
        statusLabel.setBackground(new Color(246,203,200));
        add(statusLabel);
        controller.setPlayer(statusLabel);

        JButton save = new JButton("Save");
        save.setBackground(new Color(246,203,200));
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
        save.setLocation(830, 195);
        save.setSize(120, 40);
        add(save);

        JButton reStart1 = new JButton("restart1");
        reStart1.setBackground(new Color(246,203,200));
        reStart1.addActionListener((e) -> {
            System.out.println("restart a game with 2 players");
            controller.restartGame1();

        });

        JButton regret = new JButton("Regret");
        regret.setBackground(new Color(246,203,200));
        regret.addActionListener((e) -> {
            controller.regret();
        });
        regret.setLocation(830, 255);
        regret.setSize(120, 40);
        add(regret);

        JButton nextPlayer = new JButton("nextPlayer");
        nextPlayer.setBackground(new Color(246,203,200));
        nextPlayer.addActionListener((e) -> {
            controller.nextPlay();
        });
        nextPlayer.setLocation(830,315);
        nextPlayer.setSize(120,40);
        add(nextPlayer);
//        System.out.println("a的值等于"+controller.a);



        reStart1.setLocation(830, 435);
        reStart1.setSize(120, 40);
        add(reStart1);



        JButton reStart2 = new JButton("restart2");
        reStart2.setBackground(new Color(246,203,200));
        reStart2.addActionListener((e) -> {
            System.out.println("restart a game with 4 players");
            controller.restartGame2();
        });
        reStart2.setLocation(830, 375);
        reStart2.setSize(120, 40);
        add(reStart2);

        JButton button = new JButton("...");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Button clicked!"));
        button.setLocation(830, 495);
        button.setSize(20, 20);
        add(button);
    }

    /**
     * 获取每次棋子移动时的坐标
     *
     * @param src  移动后的位置
     * @param dest 被移动的棋子
     */
    public void getPosition(ChessBoardLocation src, ChessBoardLocation dest) {
        oldLocation = dest;
        location = src;

        System.out.println("GameFrameOldPosition" + dest.getRow() + "," + dest.getColumn());
        System.out.println("GameFramePosition" + src.getRow() + "," + src.getColumn());
    }




}
