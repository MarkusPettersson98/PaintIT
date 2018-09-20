package com.PaintIT.app;

import Tools.Brush;
import Tools.Eraser;
import Tools.SprayCan;
import Tools.Tool;
import Canvas.CanvasController;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PaintingView extends AnchorPane {

    @FXML
    Canvas canvas;

    @FXML
    ColorPicker colorPicker;

    Color currentColor;

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

        tools.add(new Brush());
        tools.add(new SprayCan());
        tools.add(new Eraser());

        for(Tool t : tools) {
            t.addObserver(canvasController);
        }
        this.currentTool = tools.get(1);

        // Add event handlers
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, m -> {
            currentTool.apply((int) m.getSceneX(), (int) m.getSceneY());
        });
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, m -> {
            currentTool.apply((int) m.getSceneX(), (int) m.getSceneY());
        });

        colorPicker.setOnAction(e -> {
            currentColor = colorPicker.getValue();
            currentTool.setColor(currentColor); // TODO refactor so that tool does not know about color, only paintingview needs to know this
        });

    }

    public void setRadius(int radius) { this.currentTool.setRadius(radius);}

    public void setColor(Color color) { this.currentTool.setColor(color);}
}