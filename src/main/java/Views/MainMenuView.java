package Views;

import Util.ButtonFactory;
import com.PaintIT.app.TopController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import sun.plugin.javascript.navig.Anchor;

import java.io.IOException;

public class MainMenuView extends AnchorPane{

    @FXML private AnchorPane mainMenuAnchorPane;
    @FXML private Button play;
    @FXML private Button howToPlay;
    @FXML private Button highScore;

    @FXML private AnchorPane howToPlayAnchorPane;
    @FXML private AnchorPane lightBoxAnchorPane;
    @FXML private ImageView closeButtonImageView;

    public MainMenuView (FXMLLoader fxmlLoader) {

        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainMenuView.fxml"));

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
            TopController.show(play.getId());
        });

        howToPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("tjena");
                showHowToPlay();
            }
        });

        highScore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("tja");
            }
        });

        closeButtonImageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showMainMenu();
                closeButtonMouseClickedOrExited();
            }
        });

        closeButtonImageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                closeButtonMouseEntered();
            }
        });

        closeButtonImageView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                closeButtonMouseClickedOrExited();
            }
        });

        lightBoxAnchorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showMainMenu();
            }
        });

        howToPlayAnchorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseTrap(event);
            }
        });

    }

    private void closeButtonMouseEntered(){
        String path = "images/icon_close_hover.png";
        closeButtonImageView.setImage((new Image(getClass().getClassLoader().getResourceAsStream((path)))));
    }

    private void closeButtonMouseClickedOrExited(){
        String path = "images/icon_close.png";
        closeButtonImageView.setImage((new Image(getClass().getClassLoader().getResourceAsStream((path)))));
    }

    private void showMainMenu (){
        mainMenuAnchorPane.toFront();
    }

    private void showHowToPlay (){
        lightBoxAnchorPane.toFront();
        howToPlayAnchorPane.toFront();
    }

    private void mouseTrap(Event event){
        event.consume();
    }

}


