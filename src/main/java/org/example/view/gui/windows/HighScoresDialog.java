package org.example.view.gui.windows;

import org.example.scores.*;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class HighScoresDialog extends JDialog{
    private final String PATH_TO_HIGHSCORES = "C:\\Users\\79628\\IdeaProjects\\JavaSwepeer2000\\src\\main\\resources\\highscores";
    private HighScores highScores;

    public HighScoresDialog(JFrame frame) {
        super(frame, "High scores", true);
        loadHighScores();
        Box box = Box.createVerticalBox();
        add(box);

        for (Score score : highScores.getScoreTable()) {
            JLabel label = new JLabel(score.toString());
            label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            box.add(label);
        }
        pack();
        setLocationRelativeTo(null);
    }

    private void loadHighScores() {
        try {
            FileInputStream fileInputStream = new FileInputStream(PATH_TO_HIGHSCORES);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            this.highScores = (HighScores) objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (IOException | ClassNotFoundException exception) {}
    }
}
