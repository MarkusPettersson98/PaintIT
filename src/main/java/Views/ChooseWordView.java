package Views;

import Controller.TopController;
import Util.ButtonFactory;
import Model.WordAndGuess.Word;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.*;

public class ChooseWordView extends AnchorPane implements GameScreen{

    private TopController topController;

    @FXML
    ToggleButton easyWordbtn,mediumWordbtn,hardWordbtn;

    @FXML
    Button donebtn;

    final ToggleGroup toggleWordbtnGroup = new ToggleGroup();

    Map<String, Word> words = new HashMap<>();

    List<Word> wordList;
    List<ToggleButton> toggleButtons;

    public ChooseWordView(FXMLLoader fxmlLoader, TopController topController) {
        this.topController = topController;

        fxmlLoader.setLocation(getClass().getResource("/fxml/ChooseWordView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        toggleButtons = Arrays.asList(easyWordbtn,mediumWordbtn,hardWordbtn);

        donebtn.setId(ButtonFactory.createPaintingViewBtnId());
        donebtn.setOnAction(event -> {
            topController.setCurrentWord(words.get(((ToggleButton) toggleWordbtnGroup.getSelectedToggle()).getText()));
            topController.show(donebtn.getId());

        });

        for(ToggleButton toggleButton: toggleButtons){
            toggleButton.setOnAction(e ->{
                donebtn.setDisable(true);
                for(ToggleButton toggleButton1: toggleButtons){
                    if(toggleButton1.isSelected()){
                        donebtn.setDisable(false);
                    }
                }
            });
        }

    }

    private void setupButton(ToggleButton button, String name) {
        button.setText(name);
        button.setToggleGroup(this.toggleWordbtnGroup);
    }

    private void clear(){
        for (ToggleButton toggleButton: toggleButtons){
            toggleButton.setSelected(false);
            toggleButton.setDisable(true);
            words.clear();
            toggleButton.setText("No More Words");
        }
    }

    @Override
    public void init() {

        wordList = topController.getPossibleWords();
        clear();

        if (wordList.isEmpty()){
            topController.setIsLastWord(true);
            donebtn.setId(ButtonFactory.createDoneViewBtnId());
            donebtn.setText("Finish!");
            donebtn.setDisable(false);
            donebtn.setOnAction(e -> {
                topController.show(donebtn.getId());
            });
        }
        else {
            // Make sure that 'confirm' button is disabled
            donebtn.setDisable(true);
        }

        for(Word word: wordList){
            switch (word.getDifficulty_level()){
                case EASY:
                    setupButton(toggleButtons.get(0),word.getWord());
                    words.put(word.getWord(),word);
                    toggleButtons.get(0).setDisable(false);
                    break;
                case MEDIUM:
                    setupButton(toggleButtons.get(1),word.getWord());
                    words.put(word.getWord(),word);
                    toggleButtons.get(1).setDisable(false);
                    break;
                case HARD:
                    setupButton(toggleButtons.get(2),word.getWord());
                    words.put(word.getWord(),word);
                    toggleButtons.get(2).setDisable(false);
                    break;
            }
        }

    }

    @Override
    public Pane getPane() {
        return this;
    }
}
