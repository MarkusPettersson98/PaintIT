package Views;

import Util.ButtonFactory;
import com.PaintIT.app.TopController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

import java.util.Timer;
import java.util.TimerTask;

import java.io.IOException;

public class WordRevealView extends AnchorPane{

    @FXML private Label startingPlayerLabel;
    @FXML private Label instructionsLabel;
    @FXML private Label numberCountdownLabel;
    @FXML private Button revealNowButton;
    private int secondsleft;

    public WordRevealView (FXMLLoader fxmlLoader){

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
            TopController.show(revealNowButton.getId()); });
    }


    public void setPlayerNameLabels(Pair<String, String> players) {
        // Key is first value of pair, in this case drawing player
        startingPlayerLabel.setText(players.getKey());
    }

    public void startTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Platform.runLater(() -> update());
                secondsleft--;

                if(secondsleft == 0){
                    Platform.runLater(() -> TopController.show(revealNowButton.getId()));
                    timer.cancel();
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


}
