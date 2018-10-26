package Game;

import Model.Game.Score;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTest {

    Score dummyScore;
    String dummyTeamName = "dummyTeam";
    int dummyStreak = 42;

    @Before
    public void setup() {
        dummyScore = new Score(dummyTeamName, dummyStreak);
    }

    @Test
    public void getFormattedScore() {
        // Should print out on format "TeamName:TeamStreak";
        String expected = dummyTeamName + ':' + dummyStreak;
        String actual = dummyScore.getFormattedScore();
        assertEquals(expected, actual);
    }

    @Test
    public void compareTo() {
        // Return values for comparison a.streak > b.streak:
        //  1 -> a.streak is less than b.streak
        //  0 -> a.streak is exactly equal to b.streak
        //  -1 -> a.streak is greater than b.streak
        // This order can seem a bit reversed, but that's because it is!
        // We want to sort in descending order (highest --> lowest)
        Score lowerScore = new Score(dummyTeamName, dummyStreak - 10);
        Score equalScore = new Score(dummyTeamName, dummyStreak);
        Score higherScore = new Score(dummyTeamName, dummyStreak + 10);

        assertEquals(dummyScore.compareTo(lowerScore), -1);
        assertEquals(dummyScore.compareTo(equalScore), 0);
        assertEquals(dummyScore.compareTo(higherScore), 1);
    }
}