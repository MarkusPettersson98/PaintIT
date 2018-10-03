package ViewObjects;

import WordAndGuess.GuessLogic;
import WordAndGuess.Tile;
import javafx.event.Event;
import javafx.scene.input.KeyCode;

import java.awt.event.KeyEvent;


public class TileBoardController {
    GuessLogic guessLogic;
    public TileBoardController(GuessLogic guessLogic) {
        this.guessLogic = guessLogic;
    }

    public void addTileToGuess(Tile t){
        guessLogic.addTileToGuess(t);
    }
    public void removeTileFromGuess(Tile t){
        guessLogic.removeTileFromGuess(t);
    }



}
