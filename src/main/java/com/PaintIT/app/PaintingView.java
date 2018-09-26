package com.PaintIT.app;

import Tools.*;
import Canvas.CanvasController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.*;

public class PaintingView extends AnchorPane {

    @FXML
    HBox hbox;

    @FXML
    ColorPicker colorPicker;

    @FXML
    Slider radiusSlider;

    @FXML
    ToggleButton BrushToggleButton, SprayCanToggleButton, EraserToggleButton;

    @FXML
    Button clearBtn, undoBtn;

    final ToggleGroup group = new ToggleGroup();

    CanvasController canvasController;

    HashMap<String, Tool> tools = new HashMap<>();
    // List<Tool> tools = new ArrayList<>();
    Tool currentTool;

    public PaintingView() {

        this.canvasController = new CanvasController();
        Canvas canvas = canvasController.getCanvasView();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/paintingView.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.hbox.getChildren().add(canvas);

        colorPicker.setValue(Color.BLACK);

        // Set up tools

        tools.put(Brush.class.getSimpleName(), new Brush());
        tools.put(SprayCan.class.getSimpleName(),new SprayCan());
        tools.put(Eraser.class.getSimpleName(), new Eraser());

        setupButton(BrushToggleButton, Brush.class.getSimpleName());
        setupButton(SprayCanToggleButton, SprayCan.class.getSimpleName());
        setupButton(EraserToggleButton, Eraser.class.getSimpleName());

        group.selectedToggleProperty().addListener(e -> {
            ToggleButton selectedButton = (ToggleButton) group.getSelectedToggle();
            currentTool = tools.get(selectedButton.getText());
            colorPicker.setValue(currentTool.getColor());
        });

        currentTool = tools.get(Brush.class.getSimpleName());

        // Add event handlers
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, m -> {
            int x0 = (int) m.getX();
            int y0 = (int) m.getY();
            int radius = currentTool.getRadius();
            Color color = currentTool.getColor();
            for (int posx = (x0 - radius); posx <= (x0 + radius); posx++) {
                for (int posy = (y0 - radius); posy <= (y0 + radius); posy++) {
                    if (currentTool.apply(x0, y0, posx, posy)) {
                        canvasController.paint(posx, posy, color);
                    }
                }
            }
        });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, m -> {
            int x0 = (int) m.getX();
            int y0 = (int) m.getY();
            int radius = currentTool.getRadius();
            Color color = currentTool.getColor();
            for (int posx = (x0 - radius); posx <= (x0 + radius); posx++) {
                for (int posy = (y0 - radius); posy <= (y0 + radius); posy++) {
                    if (currentTool.apply(x0, y0, posx, posy)) {
                        canvasController.paint(posx, posy, color);
                    }
                }
            }
        });

        colorPicker.setOnAction(e -> {
            currentTool.setColor(colorPicker.getValue());
        });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, m -> {
            canvasController.pushToUndoStack();
                });


        clearBtn.setOnAction(e -> clearCanvas());

        undoBtn.setOnAction(e -> canvasController.undo());

        radiusSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            setRadius(newValue.intValue());
        });

        setRadius((int) radiusSlider.getValue());

        canvasController.fillCanvas(Color.RED);

    }

    private void setupButton(ToggleButton button, String name) {
        button.setText(name);
        button.setToggleGroup(this.group);
    }

    public void clearCanvas() {
        canvasController.clear();
        canvasController.redraw();
    }


    public void setRadius(int radius) {
        tools.forEach((k, v) -> v.setRadius(radius));
    }

}