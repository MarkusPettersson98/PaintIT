package Views;

import Game.GameSession;
import Util.ButtonFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DoneView extends AnchorPane implements GameScreen {

    @FXML
    Label congratsLbl, teamStreakLbl;

    @FXML
    Button doneBtn;

    private GameSession gameSession;

    public DoneView(FXMLLoader fxmlLoader, GameSession gameSession) {
        this.gameSession = gameSession;

        fxmlLoader.setLocation(getClass().getResource("/fxml/DoneView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // When doneBtn is pressed, go to WordRevealView
        doneBtn.setId(ButtonFactory.createWordRevealViewBtnId());
        doneBtn.setOnAction(e -> {
            gameSession.show(doneBtn.getId());
        });
    }
    @Override
    public void init() {
        // TODO MOVE THIS LOGIC TO GUESSINGVIEW?
        gameSession.incrementTeamStreak();
        // Update labels
        congratsLbl.setText("You made it!");
        Integer currentStreak = gameSession.getTeamStreak();
        teamStreakLbl.setText(currentStreak.toString());
    }

    @Override
    public Pane getPane() {
        return this;
    }
}
