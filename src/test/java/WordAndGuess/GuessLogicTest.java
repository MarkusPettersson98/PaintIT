package WordAndGuess;

import Model.WordAndGuess.GuessLogic;
import Model.WordAndGuess.Tile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuessLogicTest {

    GuessLogic guessLogic;

   @Before
   public void setUpTest(){
       guessLogic = new GuessLogic();
   }

    @Test
    public void addAndRemoveCharFromGuess() throws Exception {
        GuessLogic guessLogic = new GuessLogic();
        guessLogic.setCurrentWord(guessLogic.getPossibleWords().get(0));
        String correctWord = guessLogic.getCurrentWord().getWord();
        int count = 0;
        for(char c: correctWord.toCharArray()) {
            guessLogic.addTileToGuess(new Tile(c,count));
            count++;
        }
        assertEquals(guessLogic.guessCurrentWord(),true);
        guessLogic.removeRightMostTileFromGuess();
        assertEquals(guessLogic.guessCurrentWord(),false);
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

    @Test
    public void removeRightMostTileFromGuessTest(){
        GuessLogic guessLogic = new GuessLogic();
        guessLogic.setCurrentWord(guessLogic.getPossibleWords().get(0));
        guessLogic.addTileToGuess(new Tile('h',0));
        guessLogic.addTileToGuess(new Tile('s',1));
        guessLogic.removeRightMostTileFromGuess();

        assertTrue(guessLogic.getGuessString().equals("h"));

        guessLogic.removeRightMostTileFromGuess();

        assertTrue(guessLogic.getGuessString().equals(""));
    }

    @Test
    public void getGuessStringTest(){
        GuessLogic guessLogic = new GuessLogic();
        guessLogic.setCurrentWord(guessLogic.getPossibleWords().get(0));
        guessLogic.addTileToGuess(new Tile('h',0));
        guessLogic.addTileToGuess(new Tile('e',1));

        assertTrue(guessLogic.getGuessString().equals("he"));
    }


    @Test
    public void isGuessFilledTest(){
        GuessLogic guessLogic = new GuessLogic();
        guessLogic.setCurrentWord(guessLogic.getPossibleWords().get(0));
        for(int i = 0; i < guessLogic.getGuessWord().length;i++){
            guessLogic.addTileToGuess(new Tile('a',i));
        }

        guessLogic.addTileToGuess(new Tile('b',0));

        assertFalse(guessLogic.getGuessString().contains("b"));
    }

    @Test
    public void getAvailableTilesTest(){
        GuessLogic guessLogic = new GuessLogic();
        guessLogic.setCurrentWord(guessLogic.getPossibleWords().get(0));
        assertTrue(guessLogic.getAvailableTiles().length == 8);
    }

    @Test
    public void getCurrentWordTest(){
        GuessLogic guessLogic = new GuessLogic();
        guessLogic.setCurrentWord(guessLogic.getPossibleWords().get(0));
        assertFalse(guessLogic.getCurrentWord().equals(""));
        assertFalse(guessLogic.getCurrentWord().equals(null));
    }


}