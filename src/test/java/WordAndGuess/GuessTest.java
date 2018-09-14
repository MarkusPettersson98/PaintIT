package WordAndGuess;

import org.junit.Test;

import static org.junit.Assert.*;

public class GuessTest {

    @Test
    public void removeCharFromGuess() throws Exception {
        WordHandler wordHandler = new WordHandler();
        wordHandler.getGuess().addCharToGuess('c');
        wordHandler.getGuess().addCharToGuess('a');
        wordHandler.getGuess().addCharToGuess('h');
        assertEquals(3,wordHandler.getGuess().getGuessWord().size()); //Checks array size
        wordHandler.getGuess().removeCharFromGuess();
        assertEquals(2,wordHandler.getGuess().getGuessWord().size()); //makes sure removeCharFromGuess works
        assertEquals('c',(char)wordHandler.getGuess().getGuessWord().get(0));
        assertEquals('a',(char)wordHandler.getGuess().getGuessWord().get(1));
    }
    @Test
    public void guessCurrentWord() throws Exception {
        WordHandler wordHandler = new WordHandler();
        String word = wordHandler.getCurrentWord();
        String guess = word;


        for(int i = 0; i<guess.length(); i++){
            wordHandler.getGuess().addCharToGuess(guess.charAt(i));
        }
        assertTrue(wordHandler.getGuess().guessCurrentWord());
        wordHandler.getGuess().addCharToGuess('x');
        assertFalse(wordHandler.getGuess().guessCurrentWord());
    }
}