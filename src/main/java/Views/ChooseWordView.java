package Views;

import Game.GameSession;
import Util.ButtonFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class ChooseWordView {

    private GameSession gameSession;

    @FXML
    ToggleButton easyWordbtn,mediumWordbtn,hardWordbtn;

    @FXML
    Button donebtn;

    final ToggleGroup toggleWordbtnGroup = new ToggleGroup();

    public ChooseWordView(FXMLLoader fxmlLoader, GameSession gameSession) {
        this.gameSession = gameSession;

        fxmlLoader.setLocation(getClass().getResource("/fxml/ChooseWordView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        easyWordbtn.setOnAction();
    }
}
