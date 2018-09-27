package Views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class WordRevealView extends AnchorPane{

    @FXML public Label startingPlayerLabel;
    @FXML public Label instructionsLabel;
    @FXML public Label numberCountdownLabel;
    @FXML public Button revealNowButton;

    public WordRevealView (FXMLLoader fxmlLoader){

        fxmlLoader.setLocation(getClass().getResource("/fxml/WordRevealView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
