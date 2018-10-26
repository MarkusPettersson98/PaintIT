package Views.Components;

import Views.PaintingView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ColorSettingsView extends Pane {

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Slider radiusSlider;

    @FXML
    private ToggleButton greyToggleBtn, whiteToggleBtn, yellowToggleBtn, beigeToggleBtn,
            blueToggleBtn, greenToggleBtn, redToggleBtn, blackToggleBtn;

    private final ToggleGroup colorToggleGroup = new ToggleGroup();

    private Map<String, Color> colorButtonMap = new HashMap<>();


    public ColorSettingsView(PaintingView pV) {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/ColorSettingsView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // RadiusSlider Setup
        radiusSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            pV.setRadius(newValue.intValue());
        });

        radiusSlider.setValue(10);

        this.addEventHandler(KeyEvent.KEY_PRESSED, m-> {
            switch(m.getCode()) {
                case SLASH:
                    radiusSlider.decrement();
                    break;
                case MINUS:
                    radiusSlider.increment();
                    break;
            }
        });

        // Setup ColorPicker

        colorPicker.setOnAction(e -> {
            pV.changeToolColor(colorPicker.getValue());
        });

        colorPicker.setValue(Color.BLACK);

        // Setup Color Buttons
        setupColorButtons();

        colorToggleGroup.selectedToggleProperty().addListener(e -> {
            if((ToggleButton) colorToggleGroup.getSelectedToggle()==null) {
                return;
            }
            final ToggleButton selectedButton = (ToggleButton) colorToggleGroup.getSelectedToggle();
            Color tempC = colorButtonMap.get(selectedButton.getId());
            pV.changeToolColor(tempC);
            colorPicker.setValue(tempC);
            pV.pushToUndoStack();
        });
    }

    private ToggleButton setupToggleButton(ToggleButton button, ToggleGroup tG) {
        button.setToggleGroup(tG);
        return button;
    }

    private void setupColorButtons() {
        setupToggleButton(blackToggleBtn, colorToggleGroup);
        setupToggleButton(greyToggleBtn, colorToggleGroup);
        setupToggleButton(greenToggleBtn, colorToggleGroup);
        setupToggleButton(blueToggleBtn, colorToggleGroup);
        setupToggleButton(whiteToggleBtn, colorToggleGroup);
        setupToggleButton(beigeToggleBtn, colorToggleGroup);
        setupToggleButton(yellowToggleBtn, colorToggleGroup);
        setupToggleButton(redToggleBtn, colorToggleGroup);

        colorButtonMap.put(blackToggleBtn.getId(), Color.BLACK);
        colorButtonMap.put(greyToggleBtn.getId(), Color.GREY);
        colorButtonMap.put(greenToggleBtn.getId(), Color.GREEN);
        colorButtonMap.put(blueToggleBtn.getId(), Color.BLUE);
        colorButtonMap.put(whiteToggleBtn.getId(), Color.WHITE);
        colorButtonMap.put(beigeToggleBtn.getId(), Color.BISQUE);
        colorButtonMap.put(yellowToggleBtn.getId(), Color.YELLOW);
        colorButtonMap.put(redToggleBtn.getId(), Color.RED);
    }
}
