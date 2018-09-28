package Views;

import Canvas.CanvasView;
import Game.GameSession;
import ViewObjects.TileBoardView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class GuessingView extends AnchorPane {

    @FXML
    VBox vBox;

    public GuessingView(FXMLLoader fxmlLoader) {

        fxmlLoader.setLocation(getClass().getResource("/fxml/GuessingView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TileBoardView tileBoardView = new TileBoardView();

        CanvasView canvasView = new CanvasView(GameSession.getInstance().getCanvas());
       vBox.getChildren().add(canvasView);
       vBox.getChildren().add(tileBoardView);

    }

}
