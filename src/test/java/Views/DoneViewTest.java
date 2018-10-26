package Views;

import Controller.TopController;
import Model.Game.Team;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class DoneViewTest extends ApplicationTest {

    TopController topController;
    Scene scene;

    Label streakLabel;

    Button backToMainMenuButton;
    Button doneBtn;
    Button quitGameSessionButton;
    Button startNewGameButton;

    public <T extends Node> T find(final String query) {
        return lookup(query).query();
    }

    @Override
    public void start (Stage stage){

        topController = new TopController();

        Team team = new Team ("Test One", "Test Two");
        topController.addTeam(team);

        scene = new Scene(topController.getCurrentPane());

        topController.show(DoneView.class.getSimpleName());
        stage.setScene(scene);
        stage.show();
        streakLabel = (Label) find("#teamStreakLbl");
        backToMainMenuButton = (Button) find(".button-backToMainMenu");
        doneBtn = (Button) find(".button-play");
        quitGameSessionButton = (Button) find (".button-quitGameSession");
        startNewGameButton = (Button) find (".button-startNewGame");


    }

    @Test
    public void streakLabelTest (){
        int streak = topController.getTeamStreak();
        String labelStreak = streakLabel.getText();
        assert (streak == Integer.parseInt(labelStreak));
    }

    @Test
    public void gameIsNotOverVisibleButtonsTest (){
        assert (!backToMainMenuButton.isVisible() && !startNewGameButton.isVisible() &&
                doneBtn.isVisible() && quitGameSessionButton.isVisible());
    }


    //TODO : Something have happened with the commented tests, unsure of what... it lies within quitGameSessionButton.

    @Test
    public void quitGameVisibleButtonsTest (){
        clickOn(quitGameSessionButton);
        assert (backToMainMenuButton.isVisible() && startNewGameButton.isVisible() &&
                !doneBtn.isVisible() && !quitGameSessionButton.isVisible());
    }

    @Test
    public void quitGameGameIsOverTest (){
        clickOn(quitGameSessionButton);
        Boolean gameOver = topController.getGameOver();
        assert (gameOver);
    }

    @Test
    public void startNewRoundNamesSavedTest (){
        String playerOne = topController.getGuesserName();
        String playerTwo = topController.getDrawerName();
        String newTeamName = new String (playerOne + " and " + playerTwo);
        clickOn(quitGameSessionButton);
        clickOn(startNewGameButton);
        String teamName = topController.getTeamName();
        assert (newTeamName.equals(teamName));
    }
    
}