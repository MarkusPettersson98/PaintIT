package Views;

import Game.GameSession;
import Util.ButtonFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

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
    private final int COUNTDOWNSTARTVALUE = 6;

    private GameSession gameSession;

    public WordRevealView (FXMLLoader fxmlLoader, GameSession gameSession){
        this.gameSession = gameSession;

        fxmlLoader.setLocation(getClass().getResource("/fxml/WordRevealView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        revealNowButton.setId(ButtonFactory.createPaintingViewBtnId());
        revealNowButton.setOnAction(e -> {
            gameSession.show(revealNowButton.getId());
            timer.cancel();
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
        // Start countdown
        setTimer(COUNTDOWNSTARTVALUE);
        startTimer();
        // Update player labels
        setPlayerNameLabels(gameSession.getPlayerNames());
    }

    private void setPlayerNameLabels(Pair<String, String> players) {
        // Key is first value of pair, in this case drawing player
        startingPlayerLabel.setText(players.getKey());
    }

    @Override
    public Pane getPane() {
        return this;
    }
}
