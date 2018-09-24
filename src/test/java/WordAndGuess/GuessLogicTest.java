package WordAndGuess;

import org.junit.Test;

import static org.junit.Assert.*;

public class GuessLogicTest {

    @Test
    public void removeCharFromGuess() throws Exception {
        GuessLogic guessLogic = new GuessLogic();
        guessLogic.addCharToGuess('c');
        guessLogic.addCharToGuess('a');
        guessLogic.addCharToGuess('h');
        assertEquals(3,guessLogic.getGuessWord().size()); //Checks array size
        guessLogic.removeCharFromGuess();
        assertEquals(2,guessLogic.getGuessWord().size()); //makes sure removeCharFromGuess works
        assertEquals('c',(char)guessLogic.getGuessWord().get(0));
        assertEquals('a',(char)guessLogic.getGuessWord().get(1));

    }
    @Test
    public void guessCurrentWord() throws Exception {
       GuessLogic guessLogic = new GuessLogic();
        String word = guessLogic.getCurrentWord();
        String guess = word;

        for(int i = 0; i<guess.length(); i++){
            guessLogic.addCharToGuess(guess.charAt(i));
        }
        assertTrue(guessLogic.guessCurrentWord());
        guessLogic.addCharToGuess('x');
        assertFalse(guessLogic.guessCurrentWord());

    }
}