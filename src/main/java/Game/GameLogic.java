package Game;

import Canvas.CanvasModel;
import WordAndGuess.GuessLogic;
import lombok.Getter;
import lombok.Setter;

class GameLogic {

    @Setter @Getter private CanvasModel currentPainting;

    @Getter private final GuessLogic guessLogic;

    GameLogic(){
        guessLogic = new GuessLogic();
    }

    public String getCurrentWord() {
        return guessLogic.getCurrentWord();
    }

    public void generateNewWord() {
        guessLogic.generateNewWord();
    }

}
