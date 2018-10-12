package WordAndGuess;

import org.junit.Test;

import static org.junit.Assert.*;

public class GuessLogicTest {

    @Test
    public void addAndRemoveCharFromGuess() throws Exception {
        GuessLogic guessLogic = new GuessLogic();
        guessLogic.setCurrentWord(guessLogic.getPossibleWords().get(0));
        guessLogic.addTileToGuess(new Tile('c',0));
        guessLogic.addTileToGuess(new Tile('a',1));
        guessLogic.addTileToGuess(new Tile('h',2));
        assertEquals("cah",guessLogic.getGuessString()); //Checks array size
        guessLogic.removeTileFromGuess(guessLogic.getGuessWord()[1]);
        assertEquals("ch",guessLogic.getGuessString()); //makes sure removeTileFromGuess works
        guessLogic.removeTileFromGuess(guessLogic.getGuessWord()[0]);
        assertEquals("h",guessLogic.getGuessString());
        assertEquals(null,guessLogic.getGuessWord()[0]);
    }
    @Test
    public void guessCurrentWord() throws Exception {
       GuessLogic guessLogic = new GuessLogic();
        guessLogic.setCurrentWord(guessLogic.getPossibleWords().get(0));
        String word = guessLogic.getCurrentWord().getWord();
        String guess = word;

        assertFalse(guessLogic.guessCurrentWord());
        for(int i = 0; i<guess.length(); i++){
            guessLogic.addTileToGuess(new Tile(guess.charAt(i),i));
        }
        assertTrue(guessLogic.guessCurrentWord());



    }
}