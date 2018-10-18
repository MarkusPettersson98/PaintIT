package Views;

import Controller.TopController;
import Model.Game.Team;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class DoneViewTest extends ApplicationTest {

    TopController topController;
    Scene scene;

    Label streakLabel;

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
    }

    @Test
    public void streakLabelTest (){
        int streak = topController.getTeamStreak();
        String labelStreak = streakLabel.getText();
        assert (streak == Integer.valueOf(labelStreak));
    }

    @Test
    public void startNewRoundTest (){
        clickOn(".button-play");
        assert (topController.getCurrentPane().getChildren().toString().contains("WordRevealView"));
    }

}