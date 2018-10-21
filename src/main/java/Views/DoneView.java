package Views;

import Controller.TopController;
import Model.Game.Team;
import Util.ButtonFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DoneView extends AnchorPane implements GameScreen {

    @FXML
    private Label congratsLbl, teamStreakLbl;

    @FXML
    private Button doneBtn;

    @FXML private Label informationLabel;

    @FXML private Button quitGameSessionButton;

    @FXML private Button backToMainMenuButton;

    @FXML private ImageView confettiImageView;

    @FXML private Button startNewGameButton;

    private TopController topController;

    private String playerOne;
    private String playerTwo;

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

        //When backToMainMenuButton is pressed, go to MainMenuView
        backToMainMenuButton.setId(ButtonFactory.createMainMenuViewBtnId());
        backToMainMenuButton.setOnAction(e->{
            topController.setToGameOver(false);
            topController.show(backToMainMenuButton.getId());
        });

        //When startNewGameButton is pressed, go to WordRevealView
        startNewGameButton.setId(ButtonFactory.createWordRevealViewBtnId());
        startNewGameButton.setOnAction(e->{
            topController.setToGameOver(false);
            topController.gameOver();
            topController.addTeam(new Team(playerOne, playerTwo));
            topController.show(startNewGameButton.getId());
        });
    }

    @Override
    public void init() {
        // First check if all the words are gone, which means the players have "won"
        if (topController.getIsLastWord()){
            changeToWinnerView();
        }
        //Then check so that the players have guessed the word correctly and can continue to play.
        else if(!topController.getGameOver()){
            changeToNormalView();
        }
        //This means that the players have failed to guess the word correctly and have lost
        else {
            changeToLoserView();
        }

    }

    @Override
    public Pane getPane() {
        return this;
    }

    /**
     * Changes the view to "Normal View", which means the game will continue as normal. Updates the text in
     * {@link DoneView#teamStreakLbl} to inform the players what their current score is. Sets
     * {@link DoneView#doneBtn} to visible, so that the players can continue playing.
     */
    private void changeToNormalView (){
        congratsLbl.setText("You made it!");
        informationLabel.setText("Your current score is:");
        doneBtn.setVisible(true);
        quitGameSessionButton.setVisible(true);
        final Integer currentStreak = topController.getTeamStreak();
        teamStreakLbl.setText(currentStreak.toString());
        backToMainMenuButton.setVisible(false);
        startNewGameButton.setVisible(false);
        confettiImageView.setVisible(false);
    }

    /**
     * Changes the view to "Loser View", which means that the game is over. Updates the text in
     * {@link DoneView#congratsLbl} and {@link DoneView#informationLabel} to inform the player that the game is over.
     * Sets {@link DoneView#doneBtn} and {@link DoneView#quitGameSessionButton} to not visible, to prevent the player
     * from being able to continue playing, as well as sets {@link DoneView#backToMainMenuButton} to visible.
     */
    private void changeToLoserView (){
        doneBtn.setVisible(false);
        backToMainMenuButton.setVisible(true);
        quitGameSessionButton.setVisible(false);
        startNewGameButton.setVisible(true);
        congratsLbl.setText("Game Session Ended!");
        informationLabel.setText("Your final score is:");
        final Integer currentStreak = topController.getTeamStreak();
        teamStreakLbl.setText(currentStreak.toString());
        saveTeamNames();
    }

    /**
     * Changes the view to "Winner View", which means that the players have completed all the words. Updates the text in
     * {@link DoneView#congratsLbl} and {@link DoneView#informationLabel} to inform the player that the have completed
     * all the words. Sets {@link DoneView#doneBtn} and {@link DoneView#quitGameSessionButton} to not visible,
     * as well as sets {@link DoneView#backToMainMenuButton} and {@link DoneView#startNewGameButton} to visible, to
     * give the players the chance to start a new game.
     */
    private void changeToWinnerView (){
        //Sets a new confetti-background
        confettiImageView.setVisible(true);
        doneBtn.setVisible(false);
        backToMainMenuButton.setVisible(true);
        quitGameSessionButton.setVisible(false);
        startNewGameButton.setVisible(true);
        congratsLbl.setText("You completed all the words!");
        informationLabel.setText("Your final score is:");
        Integer currentStreak = topController.getTeamStreak();
        teamStreakLbl.setText(currentStreak.toString());
    }

    /**
     * Saves the names of the player.
     */
    private void saveTeamNames (){
        playerOne = topController.getGuesserName();
        playerTwo = topController.getDrawerName();
    }

}
