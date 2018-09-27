package com.PaintIT.app;

import Tools.*;
import Canvas.CanvasController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.*;

import static javafx.scene.input.KeyCode.*;

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
    Button clearBtn, undoBtn;

    final KeyCombination ctrlZ = new KeyCodeCombination(Z,
            KeyCombination.CONTROL_DOWN);

    final ToggleGroup group = new ToggleGroup();

    CanvasController canvasController;

    HashMap<String, Tool> tools = new HashMap<>();
    Tool currentTool;

    public PaintingView() {

        this.canvasController = new CanvasController();
        this.canvas = canvasController.getCanvasView();

        this.getChildren().add(canvas);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/paintingView.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BrushToggleButton.setSelected(true);
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
            int x0 = (int) m.getSceneX();
            int y0 = (int) m.getSceneY();
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
            int x0 = (int) m.getSceneX();
            int y0 = (int) m.getSceneY();
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

        this.addEventHandler(KeyEvent.KEY_PRESSED, m-> {
                if (m.getCode().equals(Z) && (m.isControlDown()||m.isMetaDown())) {
                    canvasController.undo();
                }
        });

        this.addEventHandler(KeyEvent.KEY_PRESSED, m-> {
            if (m.getCode().equals(E)) {
                currentTool = tools.get(Eraser.class.getSimpleName());
                EraserToggleButton.setSelected(true);
            }
        });

        this.addEventHandler(KeyEvent.KEY_PRESSED, m-> {
            if (m.getCode().equals(B)) {
                currentTool = tools.get(Brush.class.getSimpleName());
                BrushToggleButton.setSelected(true);
            }
        });

        this.addEventHandler(KeyEvent.KEY_PRESSED, m-> {
            if (m.getCode().equals(S)) {
                currentTool = tools.get(SprayCan.class.getSimpleName());
                SprayCanToggleButton.setSelected(true);
            }
        });

        this.addEventHandler(KeyEvent.KEY_PRESSED, m-> {
            if (m.getCode().equals(SLASH)) {
                System.out.println("you pressed -");
                radiusSlider.decrement();
            }
        });

        this.addEventHandler(KeyEvent.KEY_PRESSED, m-> { //So this is actually +
            if (m.getCode().equals(MINUS)) {
                System.out.println("you pressed +");
                radiusSlider.increment();
            }
        });


        setRadius((int) radiusSlider.getValue());
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