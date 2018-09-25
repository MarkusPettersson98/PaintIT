package ViewObjects;

import Util.GeneralUtil;
import WordAndGuess.GuessLogic;
import WordAndGuess.Tile;


public class TileBoardController {


    TileBoard tileBoard;
    GuessLogic guessLogic;
    public TileBoardController(GuessLogic guessLogic, TileBoard tileBoard) {
        this.tileBoard = tileBoard;
        this.guessLogic = guessLogic;
        setTilesActionListeners();
    }

    public void addTileToGuess(Tile c){
        guessLogic.addTileToGuess(c);
    }

    private void setTilesActionListeners(){ //eventListeners
       for(TileSlot t: tileBoard.getTileSlotList()){
           t.getTileButton().setOnAction(e-> addTileToGuess(t.getTile()));
       }
    }
}
