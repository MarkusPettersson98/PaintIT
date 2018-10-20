package Controller;

import Model.WordAndGuess.GuessLogic;
import Model.WordAndGuess.Tile;

/** Connects the Actions in TileBoardView to methods in the Model, (GuessLogic)
 */

public class TileBoardController {
    private GuessLogic guessLogic;
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
    private void removeRightMostTileFromGuess(){
        guessLogic.removeRightMostTileFromGuess();
    }

    public void handleKeyCode(String keyCode) {
        if ("BACK_SPACE".equals(keyCode)){
            removeRightMostTileFromGuess();
            return;
        }
        for (final Tile tile : guessLogic.getAvailableTiles() ) {
            if(tile.toString().equals(keyCode)) {
                if(tile.getStatus().equals(Tile.Status.Available)) {
                    addTileToGuess(tile);
                    break;
                }
            }
        }
    }
}
