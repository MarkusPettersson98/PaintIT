package WordAndGuess;

import org.junit.Test;

import static org.junit.Assert.*;

public class GuessLogicTest {

    @Test
    public void addAndRemoveCharFromGuess() throws Exception {
        GuessLogic guessLogic = new GuessLogic();
        guessLogic.addTileToGuess(new Tile('c',0));
        guessLogic.addTileToGuess(new Tile('a',1));
        guessLogic.addTileToGuess(new Tile('h',2));
        assertEquals("cah",guessLogic.getGuessString()); //Checks array size
        guessLogic.removeTileFromGuess();
        assertEquals("ca",guessLogic.getGuessString()); //makes sure removeTileFromGuess works


    }
    @Test
    public void guessCurrentWord() throws Exception {
       GuessLogic guessLogic = new GuessLogic();
        String word = guessLogic.getCurrentWord();
        String guess = word;

        assertFalse(guessLogic.guessCurrentWord());
        for(int i = 0; i<guess.length(); i++){
            guessLogic.addTileToGuess(new Tile(guess.charAt(i),i));
        }
        assertTrue(guessLogic.guessCurrentWord());



    }
}