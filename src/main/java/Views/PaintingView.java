package Views;

import Game.GameSession;
import Tools.*;
import Canvas.CanvasController;
import Util.ButtonFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.*;

public class PaintingView extends AnchorPane implements GameScreen {

    @FXML
    HBox hbox;

    @FXML
    ColorPicker colorPicker;

    @FXML
    Slider radiusSlider;

    @FXML
    ToggleButton BrushToggleButton, SprayCanToggleButton, EraserToggleButton;

    @FXML
    Button clearBtn, undoBtn, doneBtn;

    @FXML
    Label currentWordLbl;


    final ToggleGroup group = new ToggleGroup();

    CanvasController canvasController;

    Map<String, Tool> tools = new HashMap<>();

    Tool currentTool;

    private GameSession gameSession;

    public PaintingView(FXMLLoader fxmlLoader, GameSession gameSession) {
        this.gameSession = gameSession;

        this.canvasController = new CanvasController();
        Canvas canvas = canvasController.getCanvasView();

        fxmlLoader.setLocation(getClass().getResource("/fxml/paintingView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.hbox.getChildren().add(canvasController.getCanvasView());

        gameSession.setCanvasModel(canvasController.getCanvasModel());


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

        // TODO CHANGE BACK SO THAT WE GO TO GUESSINGVIEW INSTEAD OF DONEVIEW
        doneBtn.setId(ButtonFactory.createGuessingViewBtnId());
        // doneBtn.setId(ButtonFactory.createGuessingViewBtnId());
        doneBtn.setOnAction(e -> {
            // Finished drawing
            gameSession.show(doneBtn.getId());
        });

        radiusSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            setRadius(newValue.intValue());
        });

        this.addEventHandler(KeyEvent.KEY_PRESSED, m-> {
            switch(m.getCode()) {
                case E:
                    currentTool = tools.get(Eraser.class.getSimpleName());
                    EraserToggleButton.setSelected(true);
                    break;
                case B:
                    currentTool = tools.get(Brush.class.getSimpleName());
                    BrushToggleButton.setSelected(true);
                    break;
                case S:
                    currentTool = tools.get(SprayCan.class.getSimpleName());
                    SprayCanToggleButton.setSelected(true);
                    break;
                case SLASH:
                    radiusSlider.decrement();
                    break;
                case MINUS:
                    radiusSlider.increment();
                    break;
                case Z:
                    if(m.isControlDown() || m.isMetaDown()) {
                        canvasController.undo();
                    }
                    break;
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
        canvasController.redrawCanvasView();
    }


    public void setRadius(int radius) {
        tools.forEach((k, v) -> v.setRadius(radius));
    }

    @Override
    public void init() {
        // Update label with current word
        currentWordLbl.setText(gameSession.getCurrentWord());
    }

    @Override
    public Pane getPane() {
        return this;
    }
}