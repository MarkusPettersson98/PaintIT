package Views;

import Controller.TopController;
import Util.ButtonFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.util.Timer;
import java.util.TimerTask;

import java.io.IOException;

public class WordRevealView extends AnchorPane implements GameScreen{

    @FXML private Label startingPlayerLabel;
    @FXML private Label instructionsLabel;
    @FXML private Label numberCountdownLabel;
    @FXML private Button revealNowButton;

    private Timer timer;
    private int secondsleft;
    private final int COUNTDOWNSTARTVALUE = 30;

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
            timer.cancel();
            topController.show(revealNowButton.getId());

        });
    }



    public void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Platform.runLater(() -> update());
                secondsleft--;

                if(secondsleft <= 0){
                    // time's up, go to next page
                    Platform.runLater(() -> revealNowButton.fire());
                }
            }
        },0,1000);
    }

    private void update(){
        numberCountdownLabel.setText(Integer.toString(secondsleft));
    }

    public void setTimer(int seconds){
        secondsleft = seconds;
    }


    @Override
    public void init() {
        // Generate word to guess
        topController.newTurn();
        // Start countdown
        setTimer(COUNTDOWNSTARTVALUE);
        startTimer();
        // Update player labels
        setPlayerNameLabels(topController.getDrawerName(), topController.getGuesserName());
    }

    private void setPlayerNameLabels(String drawer, String guesser) {
        startingPlayerLabel.setText(drawer + " starts drawing");
        instructionsLabel.setText(drawer + " turn the computer around, so that " + guesser + " can't see what you are drawing.");
    }

    @Override
    public Pane getPane() {
        return this;
    }
}
