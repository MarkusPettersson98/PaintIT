package Views;

import Canvas.CanvasView;
import Game.GameSession;
import ViewObjects.TileBoardView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
        System.out.println("GAME SESSION CURRENT WORD " + gameSession.getCurrentWord());
        System.out.println("GuessLogic CURRENT WORD " + gameSession.getGuessLogic().getCurrentWord());
        this.canvasView = new CanvasView(gameSession.getCanvas());
        this.tileBoardView = new TileBoardView(gameSession.getGuessLogic());
        vBox.getChildren().add(this.canvasView);
        vBox.getChildren().add(this.tileBoardView);
        System.out.println("GAME SESSION CURRENT WORD " + gameSession.getCurrentWord());
        System.out.println("GuessLogic CURRENT WORD " + gameSession.getGuessLogic().getCurrentWord());

    }

    @Override
    public Pane getPane() {
        return this;
    }
}
