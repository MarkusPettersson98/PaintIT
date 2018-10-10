package Views;

import Canvas.CanvasView;
import Game.GameSession;
import ViewObjects.TileBoardView;
import WordAndGuess.GuessLogic;
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

    public GuessingView(FXMLLoader fxmlLoader, GameSession gameSession) {

        this.gameSession = gameSession;
        fxmlLoader.setLocation(getClass().getResource("/fxml/GuessingView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        TileBoardView tileBoardView = new TileBoardView(gameSession.getGuessLogic());

        CanvasView canvasView = new CanvasView(gameSession.getCanvas());
        vBox.getChildren().add(canvasView);
        vBox.getChildren().add(tileBoardView);
    }

    @Override
    public void init() {

    }

    @Override
    public Pane getPane() {
        return this;
    }
}
