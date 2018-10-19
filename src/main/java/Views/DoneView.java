package Views;

import Controller.TopController;
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

    @FXML Label informationLabel;

    @FXML Button quitGameSessionButton;

    @FXML Button backToMainMenuButton;

    private TopController topController;

    public DoneView(FXMLLoader fxmlLoader, TopController topController) {
        this.topController = topController;

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
            topController.show(doneBtn.getId());
        });

        quitGameSessionButton.setOnAction(e->{
            changeToLoserView();
            topController.setToGameOver(true);
            topController.gameOver();
            quitGameSessionButton.setVisible(false);
        });

        backToMainMenuButton.setId(ButtonFactory.createMainMenuViewBtnId());
        backToMainMenuButton.setOnAction(e->{
            topController.setToGameOver(false);
            topController.show(backToMainMenuButton.getId());
        });
    }
    @Override
    public void init() {
        // Update labels
        if (!topController.getGameOver()){
            congratsLbl.setText("You made it!");
            // ..
            informationLabel.setText("");
            doneBtn.setVisible(true);
            quitGameSessionButton.setVisible(true);
            Integer currentStreak = topController.getTeamStreak();
            teamStreakLbl.setText(currentStreak.toString());
            backToMainMenuButton.setVisible(false);
        }
        else {
            changeToLoserView();
        }

    }

    @Override
    public Pane getPane() {
        return this;
    }

    private void changeToLoserView (){
        doneBtn.setVisible(false);
        backToMainMenuButton.setVisible(true);
        quitGameSessionButton.setVisible(false);
        congratsLbl.setText("Game Session Ended!");
        informationLabel.setText("Your final score is:");
        Integer currentStreak = topController.getTeamStreak();
        teamStreakLbl.setText(currentStreak.toString());

    }
}
