package Views;

import Controller.GameSession;
import Model.Game.Team;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class DoneViewTest extends ApplicationTest {

    GameSession gameSession;
    Scene scene;

    Label streakLabel;

    public <T extends Node> T find(final String query) {
        return lookup(query).query();
    }

    @Override
    public void start (Stage stage){

        gameSession = new GameSession();

        Team team = new Team ("Test One", "Test Two");
        gameSession.addTeam(team);

        scene = new Scene(gameSession.getCurrentPane());

        gameSession.show(DoneView.class.getSimpleName());
        stage.setScene(scene);
        stage.show();
        streakLabel = (Label) find("#teamStreakLbl");
    }

    @Test
    public void streakLabelTest (){
        int streak = gameSession.getTeamStreak();
        String labelStreak = streakLabel.getText();
        assert (streak == Integer.valueOf(labelStreak));
    }

    @Test
    public void startNewRoundTest (){
        clickOn(".button-play");
        assert (gameSession.getCurrentPane().getChildren().toString().contains("WordRevealView"));
    }

}