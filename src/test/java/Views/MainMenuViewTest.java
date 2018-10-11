package Views;

import Game.GameSession;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class MainMenuViewTest extends ApplicationTest{

    GameSession gameSession;
    Scene scene;

    @Override
    public void start(Stage stage) {
        gameSession = new GameSession();

        scene = new Scene(gameSession.getCurrentPane());

        gameSession.show(MainMenuView.class.getSimpleName());

        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void playButtonTest(){
      
    }
}
