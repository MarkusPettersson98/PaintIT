package com.PaintIT.app;

import Tools.Brush;
import Tools.Tool;
import Canvas.CanvasController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaintingView extends AnchorPane {

    @FXML
    Canvas canvas;

    CanvasController canvasController;

    List<Tool> tools = new ArrayList<>();
    Tool currentTool;

    public PaintingView(CanvasController canvasController) {

        this.canvas = canvasController.getCanvasView();
        this.canvasController = canvasController;

        this.getChildren().add(canvas);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/paintingView.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Set up tools
        this.currentTool = new Brush();
        this.currentTool.addObserver(canvasController);

        // Add event handlers
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, m -> {
            currentTool.apply((int) m.getX(), (int) m.getY());
        });
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, m -> {
            currentTool.apply((int) m.getX(), (int) m.getY());
        });
    }
}