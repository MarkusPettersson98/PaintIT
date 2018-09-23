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

    public void handleTileClickedtest(){
        System.out.println("Controller Works");
    }

    private void setTilesEL(){ //eventListeners
       for(TileSlot t: tileBoard.getTileSlotList()){
           t.getTileButton().setOnAction(e-> handleTileClickedtest());
       }
    }
}
