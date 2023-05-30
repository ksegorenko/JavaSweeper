package org.example.view.gui;

import org.example.view.gui.windows.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiInterface extends JFrame{
    public GuiInterface() {
        JButton newGameButton = createNewGameButton();
        JButton aboutButton = createAboutButton();
        JButton highScoresButton = createHighScoresButton();

        add(newGameButton);
        add(aboutButton);
        add(highScoresButton);

        initPage();
    }

    private JButton createNewGameButton() {
        JButton newGameButton = new JButton("New game");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewGameLaunch launch = new NewGameLaunch();
            }
        });
        return newGameButton;
    }

    private JButton createAboutButton() {
        JButton aboutButton = new JButton("About");
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialog aboutDialog = new AboutDialog(GuiInterface.this);
                aboutDialog.setVisible(true);
            }
        });
        return aboutButton;
    }

    private JButton createHighScoresButton() {
        JButton highScoresButton = new JButton("High scores");
        highScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dialog highScoresDialog = new HighScoresDialog(GuiInterface.this);
                highScoresDialog.setVisible(true);
            }
        });
        return highScoresButton;
    }

    private void initPage() {
        setSize(420, 420);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Sweeper 2023");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
