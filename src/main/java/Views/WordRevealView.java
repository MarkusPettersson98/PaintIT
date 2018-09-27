package Views;

import Util.ButtonFactory;
import com.PaintIT.app.TopController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.Random;
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

        setTimer(13);
        startTimer();

        revealNowButton.setId(ButtonFactory.createPaintingViewBtnId());
        revealNowButton.setOnAction(e -> {
            TopController.show(revealNowButton.getId()); });
    }


    private void startTimer(){
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (secondsleft <= 11){
                    Platform.runLater(() -> update());
                }

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
