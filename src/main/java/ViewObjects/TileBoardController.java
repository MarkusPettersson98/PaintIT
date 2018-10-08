package ViewObjects;

import WordAndGuess.GuessLogic;
import WordAndGuess.Tile;
import javafx.event.Event;
import javafx.scene.input.KeyCode;

import java.awt.event.KeyEvent;

/** Connects the Actions in TileBoardView to methods in the Model, (GuessLogic)
 */
public class TileBoardController {
    GuessLogic guessLogic;
    public TileBoardController(GuessLogic guessLogic) {
        this.guessLogic = guessLogic;
    }

    /** Adds a Tile to the Guess in the Model, (GuessLogic)
     *
     * @param t The Tile Pressed in TileBoardView
     */
    public void addTileToGuess(Tile t){
        guessLogic.addTileToGuess(t);
    }

    /** Removes a Tile to the Guess in the Model, (GuessLogic)
     *
     * @param t The Tile Pressed in TileBoardView
     */
    public void removeTileFromGuess(Tile t){
        guessLogic.removeTileFromGuess(t);
    }



}
