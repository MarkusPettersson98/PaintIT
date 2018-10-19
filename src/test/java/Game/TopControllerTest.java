package Game;

import Controller.TopController;
import Model.Game.Team;
import Views.MainMenuView;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TopControllerTest extends ApplicationTest {
    TopController topController;
    Team team;

    @Before
    public void setUp(){
        topController = new TopController();
        team = new Team ("Test One", "Test Two");
    }

    @Test
    public void testGetTeamName (){
        String teamName = topController.getTeamName();
        assertTrue("There's no team!".equals(teamName));
    }

    @Test
    public void addTeamWhenAlreadyExists (){
        topController.addTeam(team);
        Team newTeam = new Team ("New One", "New Two");
        topController.addTeam(newTeam);
        assertTrue(topController.getTeamName().equals("Test One and Test Two"));
    }

    @Test
    public void newTurnTest (){
        topController.addTeam(team);
        String drawer = topController.getDrawerName();
        String guesser = topController.getGuesserName();
        topController.newTurn();
        String newDrawer = topController.getDrawerName();
        String newGuesser = topController.getGuesserName();
        assertTrue(drawer.equals(newGuesser) && guesser.equals(newDrawer));
    }

    @Test
    public void resetTeamStreakTest (){
        topController.addTeam(team);
        topController.resetTeamStreak();
        assertTrue(topController.getTeamStreak() == 0);
    }

    @Test
    public void incrementTeamStreakTest (){
        topController.addTeam(team);
        topController.resetTeamStreak();
        topController.incrementTeamStreak();
        assertTrue(topController.getTeamStreak() == 1);
    }

    @Test
    public void showTest (){
        topController.show(MainMenuView.class.getSimpleName());
        assertTrue(topController.getCurrentPane().getChildren().toString().contains("MainMenuView"));
    }

    @Test
    public void setGameOverTest (){
        topController.setToGameOver(true);
        assertTrue(topController.getGameOver());
    }

    @Test
    public void gameNotOverWhenStartingTest (){
        assertFalse(topController.getGameOver());
    }

}
