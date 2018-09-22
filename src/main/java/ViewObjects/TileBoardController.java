package ViewObjects;

import WordAndGuess.WordHandler;

public class TileBoardController {


    TileBoard tileBoard;
    WordHandler wordHandler;
    public TileBoardController(WordHandler wordHandler, TileBoard tileBoard) {
        this.tileBoard = tileBoard;
        this.wordHandler = wordHandler;

    }
}
