package Views;

import Views.Components.CanvasView;
import Controller.TopController;
import Views.Components.TileBoardView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class GuessingView extends AnchorPane implements GameScreen {

    @FXML
    VBox vBox;
    private TopController topController;
    private TileBoardView tileBoardView;
    private CanvasView canvasView;
    public GuessingView(FXMLLoader fxmlLoader, TopController topController) {

        fxmlLoader.setLocation(getClass().getResource("/fxml/GuessingView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.topController = topController;
    }

    @Override
    public void init() {
        this.canvasView = new CanvasView(topController.getCanvas());

        System.out.println(canvasView);
        this.tileBoardView = new TileBoardView(topController);
        vBox.getChildren().clear();
        vBox.getChildren().add(this.canvasView);
        vBox.getChildren().add(this.tileBoardView);
    }

    @Override
    public Pane getPane() {
        return this;
    }
}
