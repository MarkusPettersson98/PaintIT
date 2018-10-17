package Game;

import Model.Game.GameSession;
import Model.Game.Team;
import Views.MainMenuView;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertTrue;

public class GameSessionTest extends ApplicationTest {
    GameSession gameSession;
    Team team;

    @Before
    public void setUp(){
        gameSession = new GameSession();
        team = new Team ("Test One", "Test Two");
    }

    @Test
    public void testGetTeamName (){
        String teamName = gameSession.getTeamName();
        assertTrue("There's no team!".equals(teamName));
    }

    @Test
    public void addTeamWhenAlreadyExists (){
        gameSession.addTeam(team);
        Team newTeam = new Team ("New One", "New Two");
        gameSession.addTeam(newTeam);
        assertTrue(gameSession.getTeamName().equals("Test One and Test Two"));
    }

    @Test
    public void newTurnTest (){
        gameSession.addTeam(team);
        String drawer = gameSession.getDrawerName();
        String guesser = gameSession.getGuesserName();
        gameSession.newTurn();
        String newDrawer = gameSession.getDrawerName();
        String newGuesser = gameSession.getGuesserName();
        assertTrue(drawer.equals(newGuesser) && guesser.equals(newDrawer));
    }

    @Test
    public void resetTeamStreakTest (){
        gameSession.addTeam(team);
        gameSession.resetTeamStreak();
        assertTrue(gameSession.getTeamStreak() == 0);
    }

    @Test
    public void incrementTeamStreakTest (){
        gameSession.addTeam(team);
        gameSession.resetTeamStreak();
        gameSession.incrementTeamStreak();
        assertTrue(gameSession.getTeamStreak() == 1);
    }

    @Test
    public void showTest (){
        gameSession.show(MainMenuView.class.getSimpleName());
        assertTrue(gameSession.getCurrentPane().getChildren().toString().contains("MainMenuView"));
    }

}
