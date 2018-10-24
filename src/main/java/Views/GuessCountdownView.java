package Views;

import Controller.TopController;
import Util.CountDownUser;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class GuessCountdownView extends AnchorPane implements GameScreen, CountDownUser {

    private TopController topController;

    public GuessCountdownView(FXMLLoader fxmlLoader, TopController topController) {
        this.topController = topController;
        fxmlLoader.setLocation(getClass().getResource("/fxml/GuessCountdownView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleSecondPassed(int secondsLeft) {

    }

    @Override
    public void handleTimerFinished() {

    }

    @Override
    public void init() {

    }

    @Override
    public Pane getPane() {
        return this;
    }
}
