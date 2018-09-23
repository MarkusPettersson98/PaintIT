package ViewObjects;

import WordAndGuess.WordHandler;
import javafx.event.EventHandler;


public class TileBoardController {


    TileBoard tileBoard;
    WordHandler wordHandler;
    public TileBoardController(WordHandler wordHandler, TileBoard tileBoard) {
        this.tileBoard = tileBoard;
        this.wordHandler = wordHandler;
        setTilesEL();
    }

    public void addTileToGuess(Character c){
        System.out.println(c.toString());
        wordHandler.getGuessLogic().addCharToGuess(c);
    }

    private void setTilesEL(){ //eventListeners
       for(TileSlot t: tileBoard.getTileSlotList()){
           t.getTileButton().setOnAction(e-> addTileToGuess(t.getTileLetter()));
       }
    }
}
