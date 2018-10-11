package WordAndGuess;

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
    public void addAndRemoveCharFromGuessTest() throws Exception {
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
    public void guessCurrentWordTest() throws Exception {
        String word = guessLogic.getCurrentWord();
        String guess = word;

        assertFalse(guessLogic.guessCurrentWord());
        for(int i = 0; i<guess.length(); i++){
            guessLogic.addTileToGuess(new Tile(guess.charAt(i),i));
        }
        assertTrue(guessLogic.guessCurrentWord());
    }

    @Test
    public void removeRightMostTileFromGuessTest(){
        guessLogic.addTileToGuess(new Tile('h',0));
        guessLogic.addTileToGuess(new Tile('s',1));
        guessLogic.addTileToGuess(new Tile('b',2));
        guessLogic.removeRightMostTileFromGuess();

        assertTrue(guessLogic.getGuessString().equals("hs"));

        guessLogic.removeRightMostTileFromGuess();

        assertTrue(guessLogic.getGuessString().equals("h"));
    }

    @Test
    public void getGuessStringTest(){
        guessLogic.addTileToGuess(new Tile('h',0));
        guessLogic.addTileToGuess(new Tile('e',1));
        guessLogic.addTileToGuess(new Tile('y',2));

        assertTrue(guessLogic.getGuessString().equals("hey"));
    }

    @Test
    public void getGuessWordTest(){
        guessLogic.addTileToGuess(new Tile('c',0));
        guessLogic.addTileToGuess(new Tile('o',1));
        guessLogic.addTileToGuess(new Tile('o',2));
        guessLogic.addTileToGuess(new Tile('l',3));
        String word = "cool";
        Tile[] tiles = guessLogic.getGuessWord();

        for(int i = 0; i < word.length()-1;i++){
            assertTrue(tiles[i].getLetter() == word.charAt(i));
        }
    }

    @Test
    public void isGuessFilledTest(){
        for(int i = 0; i < guessLogic.getGuessWord().length;i++){
            guessLogic.addTileToGuess(new Tile('a',i));
        }

        guessLogic.addTileToGuess(new Tile('b',0));

        assertFalse(guessLogic.getGuessString().contains("b"));
    }

    @Test
    public void getAvailableTilesTest(){
        assertTrue(guessLogic.getAvailableTiles().length == 8);
    }

    @Test
    public void getCurrentWordTest(){
        assertFalse(guessLogic.getCurrentWord().equals(""));
        assertFalse(guessLogic.getCurrentWord().equals(null));
    }


}