package Views;

import Game.GameSession;
import Tools.*;
import Canvas.CanvasController;
import Canvas.CanvasView;
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
    Button clearBtn, undoBtn, doneBtn, noClearBtn, yesClearBtn;

    @FXML
    Label currentWordLbl;

    @FXML
    Pane clearPane;


    final ToggleGroup group = new ToggleGroup();

    CanvasController canvasController;

    Canvas canvas;


    private GameSession gameSession;

    public PaintingView(FXMLLoader fxmlLoader, GameSession gameSession) {
        this.gameSession = gameSession;

        this.canvasController = new CanvasController();
        this.canvas = canvasController.getCanvasView();

        fxmlLoader.setLocation(getClass().getResource("/fxml/paintingView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadCanvas();

        // Add event handlers to canvas
        canvasSetup();


        BrushToggleButton.setSelected(true);
        colorPicker.setValue(Color.BLACK);

        // Set up tools

        //

        setupButton(BrushToggleButton, Brush.class.getSimpleName());
        setupButton(SprayCanToggleButton, SprayCan.class.getSimpleName());
        setupButton(EraserToggleButton, Eraser.class.getSimpleName());

        group.selectedToggleProperty().addListener(e -> {
            ToggleButton selectedButton = (ToggleButton) group.getSelectedToggle();
            //
            canvasController.setCurrentTool(selectedButton.getText());
            //

            //
            colorPicker.setValue(canvasController.getToolColor());
            //
        });

        // is fixed in constructor of canvascontroller


        colorPicker.setOnAction(e -> {
            canvasController.setToolColor(colorPicker.getValue());

        });


        clearBtn.setOnAction(e -> {
            showclearPopup();
        });

        undoBtn.setOnAction(e -> {
            canvasController.undo();
            updateUndoBtn();
        });

        yesClearBtn.setOnAction(e -> {
            clearCanvas();
        });

        noClearBtn.setOnAction(e-> hideclearPopup());


        // TODO CHANGE BACK SO THAT WE GO TO GUESSINGVIEW INSTEAD OF DONEVIEW
        doneBtn.setId(ButtonFactory.createGuessingViewBtnId());
        // doneBtn.setId(ButtonFactory.createDoneViewBtnId());
        doneBtn.setOnAction(e -> {
            // Finished drawing
            hideclearPopup();
            gameSession.show(doneBtn.getId());
        });

        radiusSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            setRadius(newValue.intValue());
        });

        //TODO, make this talk with canvasController Tools.
        /*this.addEventHandler(KeyEvent.KEY_PRESSED, m-> {
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
                        updateUndoBtn();
                    }
                    break;
            }
        });*/

        setRadius((int) radiusSlider.getValue());

        hideclearPopup();

        updateUndoBtn();
    }

    private void showclearPopup() {
        clearPane.setVisible(true);
        clearPane.setDisable(false);
    }
    private void hideclearPopup() {
        clearPane.setVisible(false);
        clearPane.setDisable(true);
    }

    private void setupButton(ToggleButton button, String name) {
        button.setText(name);
        button.setToggleGroup(this.group);
    }

    public void clearCanvas() {
        canvasController.clear();
        canvasController.redrawCanvasView();

        hideclearPopup();
        updateUndoBtn();
    }


    public void setRadius(int radius) {
        canvasController.setToolRadius(radius);
    }

    @Override
    public void init() {
        // Update label with current word
        currentWordLbl.setText(gameSession.getCurrentWord().getWord());

        // Create a new canvas!
        canvasController.generateNewCanvas();
        // Update GameSession with new canvas
        this.canvas = canvasController.getCanvasView();
        loadCanvas();
        canvasSetup();
    }

    private void canvasSetup() {
        // Add event handlers
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, m -> {
            int x0 = (int) m.getX();
            int y0 = (int) m.getY();
           /* int radius = currentTool.getRadius();
            Color color = currentTool.getColor();
            for (int posx = (x0 - radius); posx <= (x0 + radius); posx++) {
                for (int posy = (y0 - radius); posy <= (y0 + radius); posy++) {
                    if (currentTool.apply(x0, y0, posx, posy)) {
                        canvasController.paint(posx, posy, color);
                    }
                }
            }*/
            canvasController.useTool(x0,y0);
        });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, m -> {
            int x0 = (int) m.getX();
            int y0 = (int) m.getY();
          /*  int radius = currentTool.getRadius();
            Color color = currentTool.getColor();
            for (int posx = (x0 - radius); posx <= (x0 + radius); posx++) {
                for (int posy = (y0 - radius); posy <= (y0 + radius); posy++) {
                    if (currentTool.apply(x0, y0, posx, posy)) {
                        canvasController.paint(posx, posy, color);
                    }
                }
            }*/
            canvasController.useTool(x0,y0);
        });

        canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, m -> {
            canvasController.pushToUndoStack();
            // For every push to the stack, we check if the stack is empty and changes the undoBTN accordingly.
            updateUndoBtn();
        });

    }

    private void loadCanvas() {
        this.hbox.getChildren().clear();
        this.hbox.getChildren().add(canvasController.getCanvasView());

        // Create new canvasView (without actionlisteners) in gamesession
        // which is subscribed to canvasModel
        CanvasView viewableCanvasView = new CanvasView(canvasController.getCanvasModel());
        gameSession.setCurrentPainting(viewableCanvasView);
    }

    private void updateUndoBtn() {
        if(canvasController.isUndoAvailable()){
            undoBtn.setDisable(false);
        }else{
            undoBtn.setDisable(true);
        }
    }

    @Override
    public Pane getPane() {
        return this;
    }
}