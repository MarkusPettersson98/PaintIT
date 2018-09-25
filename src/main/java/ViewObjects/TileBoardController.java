package ViewObjects;

import WordAndGuess.GuessLogic;
import WordAndGuess.Tile;


public class TileBoardController {


    TileBoardView tileBoardView;
    GuessLogic guessLogic;
    public TileBoardController(GuessLogic guessLogic) {
        this.tileBoardView = tileBoardView;
        this.guessLogic = guessLogic;
    }

    public void addTileToGuess(Tile c){
        guessLogic.addTileToGuess(c);
        System.out.println("IT WORKS IN TILEBOARD");

    }


}
