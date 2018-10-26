package Game;

import Model.Game.HighScoreList;
import Model.Game.Score;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class HighScoreListTest {
    // A high score list holds 10 instances of Score at any time we try to
    // access it.
    private HighScoreList highScoreList;
    private List<Score> listOfScores;
    String dummyTeamName = "TestTeam";
    int dummyScore = 0;

    @Before
    public void setUp() {
        listOfScores = new ArrayList<>();
        // Create a list of random scores and add
        for(dummyScore = 0; dummyScore < 10; dummyScore++) {
            Score tmpScore = new Score(dummyTeamName + dummyScore, dummyScore);
            listOfScores.add(tmpScore);
        }
        highScoreList = new HighScoreList(listOfScores);
    }

    @Test
    public void add() {
        // We have a list of 10 tests. Try to add another test and check
        // if length of highScoreList still is 10.
        assertSame(highScoreList.getMAX_SCORES(), 10);
        assertSame(highScoreList.size(), 10);

        // Create a new score and try to add it to highScoreList
        Score tmpScore = new Score(dummyTeamName, dummyScore);
        highScoreList.add(tmpScore);

        assertSame(highScoreList.size(), highScoreList.getMAX_SCORES());

        // When we add an item to highScoreList, add method will internally call the
        // put method, which will sort the highScoreList in descending order.
        String dummyString =    "TestTeam:10\n" +
                                "TestTeam9:9\n" +
                                "TestTeam8:8\n" +
                                "TestTeam7:7\n" +
                                "TestTeam6:6\n" +
                                "TestTeam5:5\n" +
                                "TestTeam4:4\n" +
                                "TestTeam3:3\n" +
                                "TestTeam2:2\n" +
                                "TestTeam1:1\n";
        assertEquals(highScoreList.getFormattedString(), dummyString);
    }

    @Test
    public void size() {
        assertSame(listOfScores.size(), highScoreList.size());
    }

    @Test
    public void trim() {
        listOfScores = new ArrayList<>();
        // Create a list of random scores and add
        int dummyLimit = 1000;
        for(dummyScore = 0; dummyScore < dummyLimit; dummyScore++) {
            Score tmpScore = new Score(dummyTeamName + dummyScore, dummyScore);
            listOfScores.add(tmpScore);
        }
        highScoreList = new HighScoreList(listOfScores);

        // Check if we managed to add 1000 entries to highScoreList
        assertTrue(highScoreList.size() == dummyLimit);
        // Trim should remove excess entries untill ther are MAX_SCORES amount
        // of entries left
        assertTrue(highScoreList.getMAX_SCORES() == 10);
        highScoreList.trim();
        assertTrue(highScoreList.size() == highScoreList.getMAX_SCORES());
    }
    @Test
    public void getFormattedString() {
        String dummyString =    "TestTeam0:0\n" +
                                "TestTeam1:1\n" +
                                "TestTeam2:2\n" +
                                "TestTeam3:3\n" +
                                "TestTeam4:4\n" +
                                "TestTeam5:5\n" +
                                "TestTeam6:6\n" +
                                "TestTeam7:7\n" +
                                "TestTeam8:8\n" +
                                "TestTeam9:9\n";

        assertTrue(highScoreList.getFormattedString().equals(dummyString));
    }

    @Test
    public void compareScore() {
        // compareScore return true if it gets passed a Score which streak
        // is greater than that of the lowest streak present in highScoreList

        // The lowest streak in highScoreList is currently 0
        // Create a dummy Score with streak x > 0
        Score dummyHigherScore = new Score(dummyTeamName, 1);
        assertTrue(highScoreList.compareScore(dummyHigherScore));

        // compareScore returns false if it gets passed a Score which streak
        // is lower than or equal to the lowest streak currently present
        // in highScoreList
        Score dummyEqualScore = new Score(dummyTeamName, 0);
        Score dummyLowerScore = new Score(dummyTeamName, -1);
        assertTrue(!highScoreList.compareScore(dummyEqualScore));
        assertTrue(!highScoreList.compareScore(dummyLowerScore));
    }


}