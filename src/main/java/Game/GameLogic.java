package Game;

import Canvas.CanvasController;
import Canvas.CanvasModel;
import Canvas.CanvasView;
import WordAndGuess.GuessLogic;
import WordAndGuess.Word;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

class GameLogic {

    @Setter @Getter private CanvasModel currentPainting;

    @Getter private  GuessLogic guessLogic;

    GameLogic(){
        guessLogic = new GuessLogic();
    }

    public String getCurrentWord() {
        return guessLogic.getCurrentWord();
    }

    public void updateGameWord(){
        guessLogic.pickNewWord();
    }

    public List<Word> getPossibleWords(){return guessLogic.getPossibleWords();}

}
