package com.PaintIT.app;

import Tools.*;
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
    Slider radiusSlider;

    @FXML
    ToggleButton BrushToggleButton, SprayCanToggleButton, EraserToggleButton;

    @FXML
    Button clearBtn;

    final ToggleGroup group = new ToggleGroup();

    Color currentColor;

    CanvasController canvasController;

    List<ToolAbstract> tools = new ArrayList<>();
    ToolAbstract currentTool;

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

        this.currentTool = tools.get(0);

        currentColor = colorPicker.getValue();

        // Add event handlers
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, m -> {
            int x0 = (int) m.getSceneX();
            int y0 = (int) m.getSceneY();
            int radius = currentTool.getRadius();
            for (int posx = (x0 - radius); posx <= (x0 + radius); posx++) {
                for (int posy = (y0 - radius); posy <= (y0 + radius); posy++) {
                    if (currentTool.apply(x0, y0, posx, posy)) {
                        canvasController.paint(posx, posy, currentColor);
                    }
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, m -> {
            //currentTool.apply((int) m.getSceneX(), (int) m.getSceneY(), currentColor);
            int x0 = (int) m.getSceneX();
            int y0 = (int) m.getSceneY();
            int radius = currentTool.getRadius();
            for (int posx = (x0 - radius); posx <= (x0 + radius); posx++) {
                for (int posy = (y0 - radius); posy <= (y0 + radius); posy++) {
                    if (currentTool.apply(x0, y0, posx, posy)) {
                        canvasController.paint(posx, posy, currentColor);
                    }
                }
            }
        });

            colorPicker.setOnAction(e -> {
            currentColor = colorPicker.getValue();
        });


        SprayCanToggleButton.setToggleGroup(group);
        BrushToggleButton.setToggleGroup(group);
        EraserToggleButton.setToggleGroup(group);

        BrushToggleButton.setUserData(0);
        SprayCanToggleButton.setUserData(1);
        EraserToggleButton.setUserData(2);

        group.selectedToggleProperty().addListener(e -> {
            ToggleButton selectedButton = (ToggleButton) group.getSelectedToggle();
            currentTool = tools.get((int) selectedButton.getUserData());
        });

        clearBtn.setOnAction(e -> clearCanvas());

        radiusSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            setRadius(newValue.intValue());
        });
    }

    public void clearCanvas() {
        canvasController.clear();
        canvasController.redraw();
    }


    public void setRadius(int radius) {
        for(ToolAbstract t : tools) {
            t.setRadius(radius);
        }
    }

}