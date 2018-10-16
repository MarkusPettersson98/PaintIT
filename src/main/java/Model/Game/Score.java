package Model.Game;

import lombok.Getter;

public class Score {

    @Getter private String teamName;
    @Getter private int streak;

    public Score(String teamName, int streak) {
        this.teamName = teamName;
        this.streak = streak;
    }

    public String formattedScore() {
        return teamName + ":" + streak;
    }


}
