package Views;

import Controller.TopController;
import Model.Game.Team;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class WordRevealViewTest extends ApplicationTest {

    TopController topController;
    Scene scene;

    @Override
    public void start (Stage stage){
        topController = new TopController();
        scene = new Scene(topController.getCurrentPane());

        Team team = new Team("Test One", "Test Two");
        topController.addTeam(team);
        topController.show(WordRevealView.class.getSimpleName());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void revealNowTest (){
        clickOn(".button-play");
        assert (topController.getCurrentPane().getChildren().toString().contains("ChooseWordView"));
    }

    @After
    public void cleanUp() {
        Runtime.getRuntime().gc();
    }

}
