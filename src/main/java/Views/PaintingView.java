package Views;

import Controller.TopController;
import Model.Canvas.Brush;
import Model.Canvas.Eraser;
import Model.Canvas.SprayCan;
import Controller.CanvasController;
import Util.ButtonFactory;
import Views.Components.ColorSettingsView;
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

public class PaintingView extends AnchorPane implements GameScreen {

    @FXML
    private HBox hbox;

    @FXML
    private ToggleButton BrushToggleButton, SprayCanToggleButton, EraserToggleButton;

    @FXML
    private Button clearBtn, undoBtn, doneBtn, noClearBtn, yesClearBtn;

    @FXML
    private Label currentWordLbl;

    @FXML
    private Pane clearPane;

    @FXML
    private Pane colorSettingsView;

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

        ColorSettingsView colorSettingsView = new ColorSettingsView(this);
        this.colorSettingsView.getChildren().add(colorSettingsView);
        loadCanvas();

        // Add event handlers to canvas
        canvasSetup();

        BrushToggleButton.setSelected(true);

        setupToggleButton(BrushToggleButton, toolToggleGroup).setText(Brush.class.getSimpleName());
        setupToggleButton(SprayCanToggleButton, toolToggleGroup).setText(SprayCan.class.getSimpleName());
        setupToggleButton(EraserToggleButton, toolToggleGroup).setText(Eraser.class.getSimpleName());


        toolToggleGroup.selectedToggleProperty().addListener(e -> {
            if((ToggleButton) toolToggleGroup.getSelectedToggle()==null) {
                return;
            }
            final ToggleButton selectedButton = (ToggleButton) toolToggleGroup.getSelectedToggle();
            canvasController.setCurrentTool(selectedButton.getText());
            canvasController.pushToUndoStack();
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


        doneBtn.setId(ButtonFactory.createGuessCountdownViewBtnId());
        doneBtn.setOnAction(e -> {
            // Finished drawing
            hideclearPopup();
            topController.show(doneBtn.getId());
        });

        this.addEventHandler(KeyEvent.KEY_PRESSED, m-> {
            switch(m.getCode()) {
                case E:
                    EraserToggleButton.fire();
                    break;
                case B:
                    BrushToggleButton.fire();
                    break;
                case S:
                    SprayCanToggleButton.fire();
                    break;
                case Z:
                    if(!(m.isControlDown() || m.isMetaDown())) {
                        break;
                    }
                case U:
                case BACK_SPACE:
                    undoBtn.fire();
                    break;
            }
        });
        hideclearPopup();
        updateUndoClearBtn();
    }

    public void changeToolColor(Color c) {
        canvasController.setToolColor(c);
    }

    public void pushToUndoStack() {
        canvasController.pushToUndoStack();
    }

    private void showclearPopup() {
        clearPane.setVisible(true);
        clearPane.setDisable(false);
    }
    private void hideclearPopup() {
        clearPane.setVisible(false);
        clearPane.setDisable(true);
    }
    
    private ToggleButton setupToggleButton(ToggleButton button, ToggleGroup tG) {
        button.setToggleGroup(tG);
        return button;
    }

    private void clearCanvas() {
        canvasController.clear();
        canvasController.redrawCanvasView();

        hideclearPopup();
        updateUndoClearBtn();
    }


    public void setRadius(int radius) {
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