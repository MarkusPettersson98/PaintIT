package ViewObjects;

import WordAndGuess.GuessLogic;
import WordAndGuess.Tile;
import javafx.event.Event;
import javafx.scene.input.KeyCode;

import java.awt.event.KeyEvent;


public class TileBoardController {


    TileBoardView tileBoardView;
    GuessLogic guessLogic;
    public TileBoardController(GuessLogic guessLogic) {
        this.tileBoardView = tileBoardView;
        this.guessLogic = guessLogic;
    }

    public void addTileToGuess(Tile t){
        guessLogic.addTileToGuess(t);
        System.out.println("IT WORKS IN TILEBOARD");
    }
    public void removeTileFromGuess(){
        System.out.println("remove tile from gues");
        guessLogic.removeTileFromGuess();
    }



}
