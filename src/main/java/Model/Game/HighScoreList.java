package Model.Game;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HighScoreList {

    @Getter private List<Score> highScores;
    @Getter private static final int MAX_SCORES = 10;


    public HighScoreList(List<Score> highScores) {
        this.highScores = highScores;
    }
    public HighScoreList() {
        this.highScores = new ArrayList<>();
    }

    public HighScoreList add(Score newHighScore) {
        // Method takes a new high score as a paramenter. It checks if passed score is a new high score.
        // If so, HighScoreList adds it to the proper place in the high score list. This function always returns
        // an updated high score list. THIS METHOD DOES NOT DO A DEEP COPY !!

        // We always want to add new high scores if we have less than 10 of them in total
        // Otherwise, we want to look at compareScore() return value
        final boolean isNewHighScore = compareScore(newHighScore) || highScores.size() <= MAX_SCORES;

        if(isNewHighScore) {
            put(newHighScore);
        }

        trim();

        return new HighScoreList(highScores);
    }

    public int size() { return highScores.size(); }

    public void put(Score newScore) {
        // Add new score to high score list, then sort the list
        highScores.add(newScore);
        Collections.sort(highScores);
    }

    /**
     * If there are more then ten scores in the highscore list it removes the lowest score.
     */
    public void trim() {
        while(highScores.size() > MAX_SCORES) {
            // Remove last entry in the list!
            highScores.remove(highScores.size() - 1);
        }
    }

    /**
     * Checks if a score was higher than a score in the highscore list.
     * @param score An instance of a score.
     * @return A boolean.
     */
    public boolean compareScore(Score score) {
        for(final Score highScore : highScores) {
            // If streak of passed score is high than anyone in the list, return true
            if(score.getStreak() > highScore.getStreak()) {
                return true;
            }
        }
        // Passed score was not higher than any on current high score list, return false
        return false;
    }

    public String getFormattedString() {
        this.trim();
 /*       Format:
 /*       teamName:streak
 /*       teamName:streak 
  */
        final StringBuilder sb = new StringBuilder();
        for(final Score score : highScores) {
            sb.append(score.getFormattedScore() + '\n');
        }
        return sb.toString();
    }
}
