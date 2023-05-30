package org.example.scores;

import java.io.Serializable;

public class Score implements Comparable<Score>, Serializable {
    private final String playerName;
    private final int hours;
    private final int minutes;
    private final int seconds;

    public Score(String player, int hours, int minutes, int seconds) {
        this.playerName = player;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    @Override
    public String toString() {
        return String.format("%s %02d:%02d:%02d\", name, hours, minutes, seconds");
    }

    @Override
    public int compareTo(Score o) {
        if (this.hours < o.hours)
            return -1;
        if (this.hours == o.hours){
            if(this.minutes < o.minutes)
                return -1;
            if(this.minutes == o.minutes) {
                if (this.seconds == o.seconds)
                    return 0;
                if (this.seconds < o.seconds)
                    return -1;
            }
        }
        return 1;
    }
}
