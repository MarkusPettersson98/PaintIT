package com.PaintIT.app;

import Tools.Brush;
import Tools.Eraser;
import Tools.SprayCan;
import Tools.Tool;
import Canvas.CanvasController;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
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

    @FXML
    ToggleButton BrushToggleButton, SprayCanToggleButton, EraserToggleButton;

    final ToggleGroup group = new ToggleGroup();

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
        this.currentTool = tools.get(0);

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


        SprayCanToggleButton.setToggleGroup(group);
        BrushToggleButton.setToggleGroup(group);
        EraserToggleButton.setToggleGroup(group);

        BrushToggleButton.setUserData(0);
        SprayCanToggleButton.setUserData(1);
        EraserToggleButton.setUserData(2);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle toggle, Toggle new_toggle) {
                if (new_toggle == null)
                    currentTool = tools.get(0);
                else
                    currentTool = tools.get((int)group.getSelectedToggle().getUserData());
            }
        });

    }

    public void setRadius(int radius) {
        for(Tool t : tools) {
            t.setRadius(radius);
        }
    }

    public void setColor(Color color) { this.currentTool.setColor(color);}
}