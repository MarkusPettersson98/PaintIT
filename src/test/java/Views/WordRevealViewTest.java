package Views;

import Game.GameSession;
import Game.Team;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class WordRevealViewTest extends ApplicationTest {

    GameSession gameSession;
    Scene scene;

    @Override
    public void start (Stage stage){
        gameSession = new GameSession();
        scene = new Scene(gameSession.getCurrentPane());

        Team team = new Team("Test One", "Test Two");
        gameSession.addTeam(team);
        gameSession.show(WordRevealView.class.getSimpleName());
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void revealNowTest (){
        clickOn(".button-play");
        assert (gameSession.getCurrentPane().getChildren().toString().contains("ChooseWordView"));
    }


}
