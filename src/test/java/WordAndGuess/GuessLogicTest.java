package WordAndGuess;

import org.junit.Test;

import static org.junit.Assert.*;

public class GuessLogicTest {

    @Test
    public void removeCharFromGuess() throws Exception {
        GuessLogic guessLogic = new GuessLogic();
        guessLogic.addTileToGuess(new Tile('c',0));
        guessLogic.addTileToGuess(new Tile('a',1));
        guessLogic.addTileToGuess(new Tile('h',2));
        assertEquals(3,guessLogic.getGuessWord().size()); //Checks array size
        guessLogic.removeCharFromGuess();
        assertEquals(2,guessLogic.getGuessWord().size()); //makes sure removeCharFromGuess works
        assertEquals('c',guessLogic.getGuessWord().get(0).getLetter());
        assertEquals('a',guessLogic.getGuessWord().get(1).getLetter());

    }
    @Test
    public void guessCurrentWord() throws Exception {
       GuessLogic guessLogic = new GuessLogic();
        String word = guessLogic.getCurrentWord();
        String guess = word;

        for(int i = 0; i<guess.length(); i++){
            //TODO CREATE SOME FORLOOP THAT CREATES TILES OR SOMETHING NICE
            guessLogic.addTileToGuess(new Tile(guess.charAt(i),i));
        }
        assertTrue(guessLogic.guessCurrentWord());
        guessLogic.addTileToGuess(new Tile('x',4));
        assertFalse(guessLogic.guessCurrentWord());

    }
}