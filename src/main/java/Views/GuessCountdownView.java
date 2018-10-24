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

public class GuessCountdownView extends AnchorPane implements GameScreen, CountDownUser {

    private final static int COUNTDOWNAMOUNT = 5;
    private TopController topController;
    @FXML Button revealNowButton;
    @FXML Label countdownLabel;
    @FXML Label guessingPlayerLbl;

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
        initButton();
    }

    private void initButton(){
        revealNowButton.setId(ButtonFactory.createGuessingViewBtnId());
        revealNowButton.setOnAction(e->handleRevealNowBtn());
    }
    private void handleRevealNowBtn(){
        topController.resetTimer();
        topController.show(revealNowButton.getId());
    }

    @Override
    public void handleSecondPassed(int secondsLeft) {
        countdownLabel.setText(Integer.toString(secondsLeft));
    }

    @Override
    public void handleTimerFinished() {
        topController.show(revealNowButton.getId());
    }

    @Override
    public void init() {
        countdownLabel.setText(Integer.toString(COUNTDOWNAMOUNT));
        topController.startCountDown(COUNTDOWNAMOUNT, this);
        guessingPlayerLbl.setText(topController.getGuesserName() + " Starts Guessing in:");

    }

    @Override
    public Pane getPane() {
        return this;
    }
}
