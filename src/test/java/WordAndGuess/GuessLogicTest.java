package WordAndGuess;

import org.junit.Test;

import static org.junit.Assert.*;

public class GuessLogicTest {

    @Test
    public void removeCharFromGuess() throws Exception {
        WordHandler wordHandler = new WordHandler();
        wordHandler.getGuessLogic().addCharToGuess('c');
        wordHandler.getGuessLogic().addCharToGuess('a');
        wordHandler.getGuessLogic().addCharToGuess('h');
        assertEquals(3,wordHandler.getGuessLogic().getGuessWord().size()); //Checks array size
        wordHandler.getGuessLogic().removeCharFromGuess();
        assertEquals(2,wordHandler.getGuessLogic().getGuessWord().size()); //makes sure removeCharFromGuess works
        assertEquals('c',(char)wordHandler.getGuessLogic().getGuessWord().get(0));
        assertEquals('a',(char)wordHandler.getGuessLogic().getGuessWord().get(1));

    }
    @Test
    public void guessCurrentWord() throws Exception {
        WordHandler wordHandler = new WordHandler();
        String word = wordHandler.getCurrentWord();
        String guess = word;

        for(int i = 0; i<guess.length(); i++){
            wordHandler.getGuessLogic().addCharToGuess(guess.charAt(i));
        }
        assertTrue(wordHandler.getGuessLogic().guessCurrentWord());
        wordHandler.getGuessLogic().addCharToGuess('x');
        assertFalse(wordHandler.getGuessLogic().guessCurrentWord());

    }
}