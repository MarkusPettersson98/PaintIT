package Model.Game;

import Views.Components.CanvasView;
import Model.WordAndGuess.GuessLogic;
import Model.WordAndGuess.Word;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

class GameLogic {

    @Setter @Getter private CanvasView currentPainting;

    @Getter private  GuessLogic guessLogic;

    public GameLogic(){
        guessLogic = new GuessLogic();
    }

    public Word getCurrentWord() {
        return guessLogic.getCurrentWord();
    }

    public void setCurrentWord(Word word){
        guessLogic.setCurrentWord(word);

    }
    public List<Word> getPossibleWords(){return guessLogic.getPossibleWords();}

    public void newGame() {
        guessLogic = new GuessLogic();
    }
}
