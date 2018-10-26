package Views;

import Controller.TopController;
import Util.ButtonFactory;
import Util.CountDownUser;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class WordRevealView extends AnchorPane implements GameScreen, CountDownUser{

    @FXML private Label startingPlayerLabel;
    @FXML private Label instructionsLabel;
    @FXML private Label numberCountdownLabel;
    @FXML private Button revealNowButton;

    private final int countDownStartValue = 30;

    private TopController topController;

    public WordRevealView (FXMLLoader fxmlLoader, TopController topController){
        this.topController = topController;

        fxmlLoader.setLocation(getClass().getResource("/fxml/WordRevealView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        revealNowButton.setId(ButtonFactory.createChooseWordViewBtnId());
        revealNowButton.setOnAction(e -> {
            topController.resetTimer();
            topController.show(revealNowButton.getId());

        });
    }
    private void update(int secondsLeft){
        numberCountdownLabel.setText(Integer.toString(secondsLeft));
    }
    @Override
    public void init() {
        // Generate word to guess
        topController.newTurn();
        // Start countdown
        topController.startCountDown(countDownStartValue,this);
        update(countDownStartValue);
        // Update player labels
        setPlayerNameLabels(topController.getDrawerName(), topController.getGuesserName());
    }

    private void setPlayerNameLabels(String drawer, String guesser) {
        startingPlayerLabel.setText(drawer + "'s turn to draw");
        instructionsLabel.setText(drawer + " turn the computer around, so that " + guesser + " can't see what you are drawing.");
    }

    @Override
    public Pane getPane() {
        return this;
    }

    @Override
    public void handleSecondPassed(int secondsLeft) {
        update(secondsLeft);
    }

    @Override
    public void handleTimerFinished() {
        revealNowButton.fire();
    }
}
