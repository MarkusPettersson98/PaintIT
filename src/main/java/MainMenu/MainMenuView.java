package MainMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainMenuView extends AnchorPane{

    @FXML private Button play;
    @FXML private Button howToPlay;
    @FXML private Button highScore;

    public MainMenuView () {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainMenuView.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("hej");
            }
        });

        howToPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("tjena");
            }
        });

        highScore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("tja");
            }
        });

    }

}


