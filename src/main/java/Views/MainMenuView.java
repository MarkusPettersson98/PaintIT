package Views;

import Game.GameSession;
import Util.ButtonFactory;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class MainMenuView extends AnchorPane implements GameScreen{

    @FXML private AnchorPane mainMenuAnchorPane;
    @FXML private Button play;
    @FXML private Button howToPlay;
    @FXML private Button highScore;

    @FXML private AnchorPane howToPlayAnchorPane;
    @FXML private AnchorPane lightBoxAnchorPane;
    @FXML private ImageView closeButtonImageView;
    @FXML private TextArea instructionsTextArea;

    public MainMenuView (FXMLLoader fxmlLoader, GameSession gameSession) {

        fxmlLoader.setLocation(getClass().getResource("/fxml/MainMenuView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        showMainMenu();

        play.setId(ButtonFactory.createGameSetupViewBtnId());
        play.setOnAction(e -> {
            gameSession.show(play.getId());
        });

        howToPlay.setOnAction(e -> {
                showHowToPlay();
        });

        highScore.setOnAction(e -> {
                System.out.println("tja");
        });

        lightBoxAnchorPane.setOnMouseClicked(e ->{
                showMainMenu();
        });

        howToPlayAnchorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseTrap(event);
            }
        });

    }

    /**
     * Changes the image for {@link MainMenuView#closeButtonImageView} when the mouse hovers over it.
     */
    @FXML
    private void closeButtonMouseEntered(){
        String path = "images/icon_close_hover.png";
        closeButtonImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(path)));
    }

    /**
     * Changes the image for {@link MainMenuView#closeButtonImageView} when it is clicked on,
     * as well as changes the focus to {@link MainMenuView#mainMenuAnchorPane}.
     */
    @FXML
    private void closeButtonMouseClicked(){
        String path = "images/icon_close.png";
        closeButtonImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(path)));
        showMainMenu();
    }

    /**
     * Changes the image for {@link MainMenuView#closeButtonImageView} when the mouse doesn't hover over it any more.
     */
    @FXML
    private void closeButtonMouseExited(){
        String path = "images/icon_close.png";
        closeButtonImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(path)));
    }

    /**
     * Sets {@link MainMenuView#mainMenuAnchorPane} as visible and sets {@link MainMenuView#howToPlayAnchorPane}
     * and {@link MainMenuView#lightBoxAnchorPane} as not visible.
     */
    private void showMainMenu (){
        mainMenuAnchorPane.setVisible(true);
        lightBoxAnchorPane.setVisible(false);
        howToPlayAnchorPane.setVisible(false);
    }

    /**
     * Sets {@link MainMenuView#howToPlayAnchorPane} and the background {@link MainMenuView#lightBoxAnchorPane}
     * as visible as well as sets {@link MainMenuView#mainMenuAnchorPane} as not visible.
     */
    private void showHowToPlay (){
        lightBoxAnchorPane.setVisible(true);
        howToPlayAnchorPane.setVisible(true);
        mainMenuAnchorPane.setVisible(false);
    }

    private void mouseTrap(Event event){
        event.consume();
    }

    @Override
    public void init() {

    }

    @Override
    public Pane getPane() {
        return this;
    }
}


