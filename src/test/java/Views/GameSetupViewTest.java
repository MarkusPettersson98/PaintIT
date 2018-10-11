package Views;

import Game.GameSession;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class GameSetupViewTest extends ApplicationTest{

    GameSession gameSession;
    Scene scene;

    @Override
    public void start (Stage stage){
        gameSession = new GameSession();

        scene = new Scene(gameSession.getCurrentPane());

        gameSession.show(GameSetupView.class.getSimpleName());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void savingNamesTest () {
        clickOn("#player1TextField");
        write("Test One");
        clickOn("#player2TextField");
        write("Test Two");
        clickOn(".button-play");
        assert (gameSession.getTeamName().equals("Test One and Test Two"));
    }

    @Test
    public void startDrawingTest (){
        clickOn(".button-play");
        assert (gameSession.getCurrentPane().getChildren().toString().contains("WordRevealView"));
    }

    @Test
    public void backButtonPressedTest (){
        clickOn(".button-mainMenu");
        assert (gameSession.getCurrentPane().getChildren().toString().contains("MainMenuView"));
    }
}
