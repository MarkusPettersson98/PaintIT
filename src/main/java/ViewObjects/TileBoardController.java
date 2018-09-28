package ViewObjects;

import WordAndGuess.GuessLogic;
import WordAndGuess.Tile;

public class TileBoardController {
    GuessLogic guessLogic;
    public TileBoardController(GuessLogic guessLogic) {
        this.guessLogic = guessLogic;
    }

    public void addTileToGuess(Tile t){
        guessLogic.addTileToGuess(t);
    }
    public void removeTileFromGuess(){
        guessLogic.removeTileFromGuess();
    }



}
