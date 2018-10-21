package Game;

import Model.Game.Team;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;


public class TeamTest {
    private Team team;
    String playerOne = "Player One";
    String playerTwo = "Player Two";

    @Before
    public void setUp(){
        team = new Team(playerOne, playerTwo);
    }

    @Test
    public void testTeamName (){
        assertTrue(team.getTeamName().equals(playerOne + " and " + playerTwo));
    }

    @Test
    public void testStreakAtStart (){
        assertTrue(team.getStreak() == 0);
    }

    @Test
    public void testIncrementStreak (){
        team.incrementStreak(1);
        assertTrue(team.getStreak() == 1);
    }

    @Test
    public void testResetStreak (){
        team.incrementStreak(1);
        team.resetStreak();
        assertTrue(team.getStreak() == 0);
    }

    @Test
    public void testSetGuesserAndDrawer (){
        if (team.getDrawerName().equals(playerOne)){
            assertTrue(team.getGuesserName().equals(playerTwo));
        }
        else{
            assertTrue(team.getGuesserName().equals(playerOne) &&
                    team.getDrawerName().equals(playerTwo));
        }
    }

    @Test
    public void testRandomGuesserAndDrawer(){
        int numberOfDrawer = 0;
        int numberOfGuesser = 0;
        for (int i = 0; i < 1000; i++) {
            Team randomTeam = new Team(playerOne, playerTwo);
            if (randomTeam.getDrawerName().equals(playerOne)){
                numberOfDrawer++;
            }
            else {
                numberOfGuesser++;
            }
        }
        assertTrue(numberOfDrawer > 300 && numberOfGuesser > 300);
    }

    @Test
    public void testChangeDrawer (){
        String drawer = team.getDrawerName();
        String guesser = team.getGuesserName();
        team.changeDrawer();
        String newDrawer = team.getDrawerName();
        String newGuesser = team.getGuesserName();
        assertTrue(drawer.equals(newGuesser) && guesser.equals(newDrawer));
    }

    @Test
    public void testGetPlayerNames (){
        assertTrue(team.getPlayerNames().getKey().equals(playerOne)
                && team.getPlayerNames().getValue().equals(playerTwo));
    }
}
