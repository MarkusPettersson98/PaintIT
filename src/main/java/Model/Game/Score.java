package Model.Game;

import lombok.Getter;

public class Score implements Comparable<Score> {

    @Getter private String teamName;
    @Getter private int streak;

    public Score(String teamName, int streak) {
        this.teamName = teamName;
        this.streak = streak;
    }

    public String getFormattedScore() {
        return teamName + ":" + streak;
    }


    @Override
    public int compareTo(Score s) {
        // Used to sort scores
        // Return values for comparison a.streak > b.streak:
        //  1 -> a.streak is less than b.streak
        //  0 -> a.streak is exactly equal to b.streak
        //  -1 -> a.streak is greater than b.streak
        // This order can seem a bit reversed, but that's because it is!
        // We want to sort in descending order (highest --> lowest)
        if(this.streak == s.streak) {
            return 0;
        } else {
            return (this.streak > s.streak) ? -1 : 1;
        }
    }

}
