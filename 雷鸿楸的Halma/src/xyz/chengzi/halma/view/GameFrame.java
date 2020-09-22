package xyz.chengzi.halma.view;

import xyz.chengzi.halma.controller.GameController;
import xyz.chengzi.halma.model.ChessBoard;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("2020 CS102A Project Demo");
        setSize(776, 810);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        ChessBoardComponent chessBoardComponent = new ChessBoardComponent(760, 16);
        ChessBoard chessBoard = new ChessBoard(16);
        GameController controller = new GameController(chessBoardComponent, chessBoard);
        add(chessBoardComponent);

        JLabel statusLabel = new JLabel("小可爱准备好了吗");
        statusLabel.setLocation(0, 760);
        statusLabel.setSize(200, 15);
        add(statusLabel);
        controller.setjLabel(statusLabel);

        JButton btnRestart = new JButton("restart");
        btnRestart.addActionListener(e ->{
            controller.restart();
            statusLabel.setText("new game for 2 players");
        });
        btnRestart.setLocation(540, 760);
        btnRestart.setSize(60, 12);
        add(btnRestart);

        JButton btnRestart2 = new JButton("restart2");
        btnRestart2.addActionListener(e -> {
            controller.restart2();
            statusLabel.setText("new game for 4 players");
        });
        btnRestart2.setLocation(440, 760);
        btnRestart2.setSize(60, 12);
        add(btnRestart2);

        JButton button = new JButton("...");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Button clicked!"));
        button.setLocation(740, 760);
        button.setSize(20, 12);
        add(button);
    }

}
