package xyz.chengzi.halma.view;

import xyz.chengzi.halma.util.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class StartMenu extends JFrame {
    private JLabel welcome;
    private JButton singlePlayer, twoPlayers, fourPlayers, loadGame, ranking;

    public StartMenu() {
        setTitle("Halma");
        setSize(300, 550);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        welcome = new JLabel("welcome to Halma");
        welcome.setSize(200, 60);
        singlePlayer = new JButton("Single Player");
        singlePlayer.setSize(160,80);
        twoPlayers = new JButton("Two Players");
        twoPlayers.setSize(160,80);
        fourPlayers = new JButton("Four Players");
        fourPlayers.setSize(160,80);

        loadGame = new JButton("Load Game");
        loadGame.setSize(160,80);

        ranking = new JButton("Ranking");
        ranking.setSize(160,80);


        JPanel panel = new JPanel(new FlowLayout());

        panel.add(welcome);
        panel.add(singlePlayer);
        panel.add(twoPlayers);
        panel.add(fourPlayers);
        panel.add(loadGame);
        panel.add(ranking);
        add(panel);

        setBtListener();
    }
    private void setBtListener(){
        singlePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        twoPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame mainFrame = new GameFrame();
                mainFrame.setVisible(true);
            }
        });
        fourPlayers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame mainFrame = new GameFrame();
                mainFrame.setVisible(true);
                mainFrame.getController().restartGame2();
            }
        });
        loadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String file = FileUtil.getFile();
                    System.out.println(file);

                    int [][] store=new int[16][16];

                    int index=0;
                    for (int i = 0; i < 16; i++) {
                        for (int j = 0; j < 16; j++) {
                            store[j][i]=Character.getNumericValue(file.charAt(index));
                            index++;
                        }
                    }
                    int a=Character.getNumericValue(file.charAt(256));
                    int currentPlayer=file.charAt(257);
                    GameFrame mainFrame = new GameFrame();
                    mainFrame.setVisible(true);
                    mainFrame.getController().loadGame(store,a,currentPlayer);
                    for (int i = 0; i < 16; i++) {
                        for (int j = 0; j < 16; j++) {
                            System.out.print(store[i][j]+"|");
                        }
                        System.out.println("\n");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        ranking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame mainFrame = new GameFrame();
                mainFrame.setVisible(true);
                Random random=new Random();
                int i = random.nextInt(3);
                switch (i){
                    case 0:
                        System.out.println("Ranking="+0+"SinglePlayer");
                        break;
                    case 1:
                        mainFrame.getController().restartGame1();
                        System.out.println("Ranking="+1+"TwoPlayer");
                        break;
                    case 2:
                        mainFrame.getController().restartGame2();
                        System.out.println("Ranking="+2+"FourPlayer");
                        break;
                }
                //mainFrame.getController().restartGame2();
            }
        });
    }


}
