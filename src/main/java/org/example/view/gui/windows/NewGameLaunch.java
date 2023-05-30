package org.example.view.gui.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameLaunch extends JFrame{
    private JTextField nameText;
    private JTextField widthText;
    private JTextField heightText;
    private JTextField bombsText;

    public NewGameLaunch() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel namePanel = createNamePanel();
        JPanel sizePanel = createSizePanel();
        JPanel bombsPanel = createBombsPanel();

        JButton playButton = createPlayButton();
        initFrame();
    }

    private JPanel createNamePanel() {
        JPanel namePanel = new JPanel();
        nameText = new JTextField("player", 15);
        namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(new JLabel("Name"));
        namePanel.add(nameText);
        namePanel.revalidate();
        add(namePanel);
        return namePanel;
    }

    private JPanel createSizePanel() {
        JPanel sizePanel = new JPanel();
        widthText = new JTextField("9", 2);
        heightText = new JTextField("9", 2);
        sizePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        sizePanel.add(new JLabel("Size"));
        sizePanel.add(widthText);
        sizePanel.add(new JLabel("x"));
        sizePanel.add(heightText);
        sizePanel.revalidate();
        add(sizePanel);
        return sizePanel;
    }

    private JPanel createBombsPanel() {
        JPanel bombsPanel = new JPanel();
        bombsText = new JTextField("10");
        bombsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bombsPanel.add(new JLabel("Bombs"));
        bombsPanel.add(bombsText);
        bombsPanel.revalidate();
        add(bombsPanel);
        return bombsPanel;
    }

    private JButton createPlayButton() {
        JButton playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int width = Integer.parseInt(widthText.getText());
                    int height = Integer.parseInt(heightText.getText());
                    int totalBombs = Integer.parseInt(bombsText.getText());
                    String playerName = nameText.getText();
                    GameWindow gameWindow = new GameWindow(width, height,totalBombs, playerName);
                }
                catch (NumberFormatException exception){
                    JOptionPane.showMessageDialog(null, "Usage: please, enter correct integer format", "Usage", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        add(playButton);
        return playButton;
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("New game");
        setSize(250, 150);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
