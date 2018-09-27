package Views;

import Canvas.CanvasView;
import Game.GameSession;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class GuessingView extends AnchorPane {

    @FXML
    HBox hbox;

    public GuessingView(FXMLLoader fxmlLoader) {

        fxmlLoader.setLocation(getClass().getResource("/fxml/GuessingView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CanvasView canvasView = new CanvasView(GameSession.getInstance().getCanvas());
        hbox.getChildren().add(canvasView);
    }

}
