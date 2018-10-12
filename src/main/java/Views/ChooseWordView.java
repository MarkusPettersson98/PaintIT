package Views;

import Game.GameSession;
import Util.ButtonFactory;
import Util.ViewFactory;
import WordAndGuess.Word;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChooseWordView extends AnchorPane implements GameScreen{

    private GameSession gameSession;

    @FXML
    ToggleButton easyWordbtn,mediumWordbtn,hardWordbtn;

    @FXML
    Button donebtn;

    final ToggleGroup toggleWordbtnGroup = new ToggleGroup();

    List<Word> wordList;

    public ChooseWordView(FXMLLoader fxmlLoader, GameSession gameSession) {
        this.gameSession = gameSession;

        fxmlLoader.setLocation(getClass().getResource("/fxml/ChooseWordView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        wordList = gameSession.getPossibleWords();

        donebtn.setId(ButtonFactory.createChooseWordViewBtnId());
        donebtn.setOnAction(event -> {gameSession.show(donebtn.getId());});

        easyWordbtn.setText(wordList.get(0).getWord());

    }

    @Override
    public void init() {

    }

    @Override
    public Pane getPane() {
        return this;
    }
}
