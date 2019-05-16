package edu.mco364;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TicTacToeFrame extends JFrame {
    private TicTacToeModel gameModel;
    private JLabel statusBar;

    TicTacToeFrame() {
        gameModel = new TicTacToeModel();
        statusBar = new JLabel(" ");

        setSize(500, 520);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(new TicTacToePanel(), BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
        setVisible(true);
    }

    class TicTacToePanel extends JPanel {
        private JButton[][] gridButtons = new JButton[3][3];

        TicTacToePanel() {
            setLayout(new GridLayout(3, 3));
            ButtonEventHandler buttonEventHandler = new ButtonEventHandler();

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    gridButtons[row][col] = new JButton();
                    gridButtons[row][col].setFont(new Font("Serif", Font.BOLD, 100));
                    gridButtons[row][col].addActionListener(buttonEventHandler);
                    add(gridButtons[row][col]);
                }
            }
        }

        private class ButtonEventHandler implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                int row, col = 0;
                Cell currentPlayer = gameModel.currentPlayer();

                gridLocationFinder:
                for (row = 0; row < 3; row++) {
                    for (col = 0; col < 3; col++) {

                        if (e.getSource() == gridButtons[row][col]) {
                            gridButtons[row][col].setText(currentPlayer.toString());

                            statusBar.setText(gameModel.currentPlayer() + " turn: ");
                            gridButtons[row][col].setEnabled(false);
                            gameModel.currentPlayer();
                            break gridLocationFinder;
                        }
                    }
                }

                gameModel.setCell(row, col, currentPlayer);

                if (gameModel.isGameOver()) {
                    statusBar.setText(gameModel.getWinner(currentPlayer));
                    for (row = 0; row < 3; row++) {
                        for (col = 0; col < 3; col++) {
                            gridButtons[row][col].setEnabled(false);
                        }
                    }
                }
            }
        }
    }
}