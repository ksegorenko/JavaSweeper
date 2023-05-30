package org.example.scores;

import java.util.ArrayList;
import java.util.Collections;

public class HighScores {
    private final int AmountOfScores = 5;
    private final ArrayList<Score> scoreTable;

    public HighScores() {
        scoreTable = new ArrayList<Score>();
    }

    public void add(Score score){
        scoreTable.add(score);
        Collections.sort(scoreTable);
        if(scoreTable.size() > AmountOfScores) {
            scoreTable.remove(AmountOfScores - 1);
        }
    }

    public ArrayList<Score> getScoreTable() {
        return scoreTable;
    }
}

