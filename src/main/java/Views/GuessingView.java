package Views;

import Canvas.CanvasView;
import Model.Game.GameSession;
import ViewObjects.TileBoardView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class GuessingView extends AnchorPane implements GameScreen {

    @FXML
    VBox vBox;
    private GameSession gameSession;
    private TileBoardView tileBoardView;
    private CanvasView canvasView;
    public GuessingView(FXMLLoader fxmlLoader, GameSession gameSession) {

        fxmlLoader.setLocation(getClass().getResource("/fxml/GuessingView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.gameSession = gameSession;
    }

    @Override
    public void init() {
        // this.canvasView = new CanvasView(gameSession.getCanvas());
        this.canvasView = gameSession.getCanvas();
        System.out.println(canvasView);
        this.tileBoardView = new TileBoardView(gameSession);
        vBox.getChildren().clear();
        vBox.getChildren().add(this.canvasView);
        vBox.getChildren().add(this.tileBoardView);
    }

    @Override
    public Pane getPane() {
        return this;
    }
}
