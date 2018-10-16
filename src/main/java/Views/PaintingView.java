package Views;

import Model.Game.GameSession;
import Model.Tools.Brush;
import Model.Tools.Eraser;
import Model.Tools.SprayCan;
import Controller.CanvasController;
import Views.Components.CanvasView;
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
import java.util.HashMap;
import java.util.Map;

public class PaintingView extends AnchorPane implements GameScreen {

    @FXML
    private HBox hbox;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Slider radiusSlider;

    @FXML
    private ToggleButton BrushToggleButton, SprayCanToggleButton, EraserToggleButton, blueToggleBtn, greenToggleBtn,redToggleBtn;

    @FXML
    private Button clearBtn, undoBtn, doneBtn, noClearBtn, yesClearBtn;

    @FXML
    private Label currentWordLbl;

    @FXML
    private Pane clearPane;

    private Map<String, Color> colorMap = new HashMap<>();



    private final ToggleGroup colorToggleGroup = new ToggleGroup();
    private final ToggleGroup toolToggleGroup = new ToggleGroup();
    private CanvasController canvasController;
    private Canvas canvas;
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

        setupButton(BrushToggleButton, Brush.class.getSimpleName(), toolToggleGroup);
        setupButton(SprayCanToggleButton, SprayCan.class.getSimpleName(), toolToggleGroup);
        setupButton(EraserToggleButton, Eraser.class.getSimpleName(), toolToggleGroup);

        setupButton(blueToggleBtn, "blue",colorToggleGroup);
        setupButton(redToggleBtn, "red",colorToggleGroup);
        setupButton(greenToggleBtn, "green",colorToggleGroup);

        colorMap.put(blueToggleBtn.getId(), Color.BLUE);
        colorMap.put(greenToggleBtn.getId(), Color.GREEN);
        colorMap.put(redToggleBtn.getId(), Color.RED);


        toolToggleGroup.selectedToggleProperty().addListener(e -> {
            if((ToggleButton) toolToggleGroup.getSelectedToggle()==null) {
                return;
            }
            ToggleButton selectedButton = (ToggleButton) toolToggleGroup.getSelectedToggle();
            canvasController.setCurrentTool(selectedButton.getText());
            colorPicker.setValue(canvasController.getToolColor());
        });

        colorToggleGroup.selectedToggleProperty().addListener(e -> {
            if((ToggleButton) toolToggleGroup.getSelectedToggle()==null) {
                return;
            }
            ToggleButton selectedButton = (ToggleButton) colorToggleGroup.getSelectedToggle();
            System.out.println(selectedButton.getId());
            canvasController.setToolColor(colorMap.get(selectedButton.getId()));
            colorPicker.setValue(colorMap.get(selectedButton.getId()));
        });

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

        this.addEventHandler(KeyEvent.KEY_PRESSED, m-> {
            switch(m.getCode()) {
                case E:
                    canvasController.setCurrentTool(Eraser.class.getSimpleName());
                    EraserToggleButton.setSelected(true);
                    break;
                case B:
                    canvasController.setCurrentTool(Brush.class.getSimpleName());
                    BrushToggleButton.setSelected(true);
                    break;
                case S:
                    canvasController.setCurrentTool(SprayCan.class.getSimpleName());
                    SprayCanToggleButton.setSelected(true);
                    break;
                case SLASH:
                    radiusSlider.decrement();
                    break;
                case MINUS:
                    radiusSlider.increment();
                    break;
                case Z:
                    if(!(m.isControlDown() || m.isMetaDown())) {
                        break;
                    }
                case BACK_SPACE:
                    canvasController.undo();
                    updateUndoBtn();
                    break;
            }
        });

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

    private void setupButton(ToggleButton button, String name, ToggleGroup tG) {
        button.setText(name);
        button.setToggleGroup(tG);
    }

    private void clearCanvas() {
        canvasController.clear();
        canvasController.redrawCanvasView();

        hideclearPopup();
        updateUndoBtn();
    }


    private void setRadius(int radius) {
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
        canvas.addEventHandler(MouseEvent.MOUSE_ENTERED, m -> hideclearPopup());
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, m ->  canvasController.useTool((int) m.getX(),(int) m.getY()));
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, m ->  canvasController.useTool((int) m.getX(),(int) m.getY()));
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