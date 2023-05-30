package org.example.view.gui.windows;

import org.example.controller.GUIController;
import org.example.model.*;
import org.example.model.Box;
import org.example.scores.Score;
import org.example.scoretime.GUITime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWindow extends JFrame{
    private Model model;
    private GUIController controller;

    private final String PLAYER_NAME;
    private final int ROWS;
    private final int COLS;
    private final int TOTAL_BOMBS_COUNT;

    private JLabel countOfBombsLabel;
    private JPanel gameFieldPanel;
    private GUITime timer;

    private JButton[][] buttons;

    public GameWindow(int cols, int rows, int totalBombs, String playerName) {
        COLS = cols;
        ROWS = rows;
        TOTAL_BOMBS_COUNT = totalBombs;
        PLAYER_NAME = playerName;

        model = new Model(COLS, ROWS, TOTAL_BOMBS_COUNT);
        model.start();
        controller = new GUIController(model);

        initNumOfBombsLabel();
        initGameField();
        initTimer();
        initFrame();
    }

    public int getCols() {
        return COLS;
    }

    public int getRows() {
        return ROWS;
    }

    public int getTotalBombsCount() {
        return TOTAL_BOMBS_COUNT;
    }

    public String getPlayerName() {
        return PLAYER_NAME;
    }

    public Score getScore() {
        return new Score(PLAYER_NAME, timer.getHours(), timer.getMinutes(), timer.getSeconds());
    }

    private void initNumOfBombsLabel() {
        countOfBombsLabel = new JLabel();
        countOfBombsLabel.setText("Bombs: 10");
        add(countOfBombsLabel, BorderLayout.NORTH);
    }

    private void initGameField() {
        buttons = new JButton[COLS][ROWS];
        gameFieldPanel = new JPanel(new GridLayout(COLS, ROWS)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords()) {
                    buttons[coord.x][coord.y].setIcon(getIcon(model.getBox(coord)));
                }
            }
        };
        for (Coord coord : Ranges.getAllCoords()) {
            JButton button = new JButton(getIcon(model.getBox(coord)));
            button.setPreferredSize(new Dimension(40, 40));
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        controller.pressLeftButton(coord);
                    }
                    else if (e.getButton() == MouseEvent.BUTTON3) {
                        controller.pressRightButton(coord);
                    }
                    gameFieldPanel.repaint();
                    if(model.gameOver()) {
                        timer.stopTimer();
                        Dialog dialog = model.getState() == GameState.WINNER
                                ? new WinnerDialog(GameWindow.this)
                                : new LoserDialog(GameWindow.this);
                        dialog.setVisible(true);
                    }
                }
            });
            buttons[coord.x] [coord.y] = button;
            gameFieldPanel.add(button);
        }
        if(model.gameOver()) {
            dispose();
        }
        add(gameFieldPanel, BorderLayout.CENTER);
    }

    private void initTimer() {
        timer = new GUITime();
        timer.setFont(new Font("Montserrat", Font.BOLD, 16));
        add(timer, BorderLayout.SOUTH);
        timer.startTimer();
    }

    private void initFrame() {
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Sweeper 2023");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private ImageIcon getIcon(Box box) {
        String fileName = "img/" + box.name().toLowerCase() + ".png";
        return new ImageIcon(getClass().getResource("C:\\Users\\79628\\IdeaProjects\\JavaSwepeer2000\\src\\main\\resources\\" + fileName));
    }
}
