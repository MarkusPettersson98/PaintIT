package Views;

import Controller.TopController;
import Model.Canvas.Brush;
import Model.Canvas.Eraser;
import Model.Canvas.SprayCan;
import Controller.CanvasController;
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
    private ToggleButton BrushToggleButton, SprayCanToggleButton, EraserToggleButton, blueToggleBtn, greenToggleBtn,redToggleBtn, blackToggleBtn;

    @FXML
    private ToggleButton greyToggleBtn, whiteToggleBtn, yellowToggleBtn, beigeToggleBtn;

    @FXML
    private Button clearBtn, undoBtn, doneBtn, noClearBtn, yesClearBtn;

    @FXML
    private Label currentWordLbl;

    @FXML
    private Pane clearPane;

    private Map<String, Color> colorButtonMap = new HashMap<>();
    private final ToggleGroup colorToggleGroup = new ToggleGroup();
    private final ToggleGroup toolToggleGroup = new ToggleGroup();
    private CanvasController canvasController;
    private Canvas canvas;
    private TopController topController;

    public PaintingView(FXMLLoader fxmlLoader, TopController topController) {
        this.topController = topController;

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

        setupToggleButton(BrushToggleButton, Brush.class.getSimpleName(), toolToggleGroup);
        setupToggleButton(SprayCanToggleButton, SprayCan.class.getSimpleName(), toolToggleGroup);
        setupToggleButton(EraserToggleButton, Eraser.class.getSimpleName(), toolToggleGroup);

        setupColorButtons();

        toolToggleGroup.selectedToggleProperty().addListener(e -> {
            if((ToggleButton) toolToggleGroup.getSelectedToggle()==null) {
                return;
            }
            final ToggleButton selectedButton = (ToggleButton) toolToggleGroup.getSelectedToggle();
            canvasController.setCurrentTool(selectedButton.getText());
        });

        colorToggleGroup.selectedToggleProperty().addListener(e -> {
            if((ToggleButton) colorToggleGroup.getSelectedToggle()==null) {
                return;
            }
            final ToggleButton selectedButton = (ToggleButton) colorToggleGroup.getSelectedToggle();
            changeToolColor(colorButtonMap.get(selectedButton.getId()));
            canvasController.pushToUndoStack();
        });

        colorPicker.setOnAction(e -> {
            changeToolColor(colorPicker.getValue());
        });

        clearBtn.setOnAction(e -> {
            showclearPopup();
        });

        undoBtn.setOnAction(e -> {
            canvasController.undo();
            updateUndoClearBtn();
        });

        yesClearBtn.setOnAction(e -> {
            clearCanvas();
        });

        noClearBtn.setOnAction(e-> hideclearPopup());


        doneBtn.setId(ButtonFactory.createGuessingViewBtnId());
        doneBtn.setOnAction(e -> {
            // Finished drawing
            hideclearPopup();
            topController.show(doneBtn.getId());
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
                case U:
                case BACK_SPACE:
                    canvasController.undo();
                    updateUndoClearBtn();
                    break;
            }
        });

        setRadius((int) radiusSlider.getValue());
        hideclearPopup();
        updateUndoClearBtn();
    }

    private void setupColorButtons() {
        setupToggleButton(blackToggleBtn, "", colorToggleGroup);
        setupToggleButton(greyToggleBtn, "", colorToggleGroup);
        setupToggleButton(greenToggleBtn, "",colorToggleGroup);
        setupToggleButton(blueToggleBtn, "",colorToggleGroup);
        setupToggleButton(whiteToggleBtn, "",colorToggleGroup);
        setupToggleButton(beigeToggleBtn, "",colorToggleGroup);
        setupToggleButton(yellowToggleBtn, "",colorToggleGroup);
        setupToggleButton(redToggleBtn, "",colorToggleGroup);

        colorButtonMap.put(blackToggleBtn.getId(),Color.BLACK);
        colorButtonMap.put(greyToggleBtn.getId(),Color.GREY);
        colorButtonMap.put(greenToggleBtn.getId(), Color.GREEN);
        colorButtonMap.put(blueToggleBtn.getId(), Color.BLUE);
        colorButtonMap.put(whiteToggleBtn.getId(), Color.WHITE);
        colorButtonMap.put(beigeToggleBtn.getId(), Color.valueOf("ffe0bd"));
        colorButtonMap.put(yellowToggleBtn.getId(), Color.YELLOW);
        colorButtonMap.put(redToggleBtn.getId(), Color.RED);
    }

    private void changeToolColor(Color c) {
        canvasController.setToolColor(c);
        colorPicker.setValue(c);
    }
    private void showclearPopup() {
        clearPane.setVisible(true);
        clearPane.setDisable(false);
    }
    private void hideclearPopup() {
        clearPane.setVisible(false);
        clearPane.setDisable(true);
    }

    private void setupToggleButton(ToggleButton button, String name, ToggleGroup tG) {
        button.setText(name);
        button.setToggleGroup(tG);
    }

    private void clearCanvas() {
        canvasController.clear();
        canvasController.redrawCanvasView();

        hideclearPopup();
        updateUndoClearBtn();
    }


    private void setRadius(int radius) {
        canvasController.setToolRadius(radius);
    }

    @Override
    public void init() {
        // Update label with current word
        currentWordLbl.setText(topController.getCurrentWord().getWord());

        // Create a new canvas!
        canvasController.generateNewCanvas();
        // Update TopController with new canvas
        this.canvas = canvasController.getCanvasView();
        changeToolColor(Color.BLACK);
        canvasController.setToolRadius(10);
        radiusSlider.setValue(10);
        canvasController.setCurrentTool("Brush");
        BrushToggleButton.setSelected(true);


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
            updateUndoClearBtn();
        });

    }

    private void loadCanvas() {
        this.hbox.getChildren().clear();
        this.hbox.getChildren().add(canvasController.getCanvasView());

        // Push canvas model to backend
        topController.setCurrentPainting(canvasController.getCanvasModel());
    }

    private void updateUndoClearBtn() {
        if(canvasController.isUndoAvailable()){
            undoBtn.setDisable(false);
            clearBtn.setDisable(false);
        }else{
            undoBtn.setDisable(true);
            clearBtn.setDisable(true);
        }
    }

    @Override
    public Pane getPane() {
        return this;
    }
}