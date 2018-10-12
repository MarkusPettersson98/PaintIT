package WordAndGuess;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class WordHandlerTest {


    @Test
    public void getRandomWordTest() throws Exception {
        WordHandler wh = new WordHandler();
        wh.pickRandomWord();
        
        String word = wh.getCurrentWord();
        assertNotNull(word);
    }


    @Test
    public void createRandomTiles()throws Exception{ //Checks if the random tiles contains the word letters
        GuessLogic guessLogic = new GuessLogic();
        guessLogic.pickNewWord();

        Tile[] tiles = guessLogic.getAvailableTiles();
        ArrayList<Character> letters = new ArrayList<>();

        for(Tile t: tiles){
            letters.add(t.getLetter());
        }
        String word = guessLogic.getCurrentWord();
        for(int i = 0; i<word.length(); i++){
          if(!letters.contains(word.charAt(i))){
             assertFalse(true);
          }
        }
        System.out.println("----------------------createRandomTilesTest---------------------------");
        System.out.println("THIS IS THE WORD :" + word);
        System.out.println("These are the Tiles" + letters);
        assertTrue(true);

    }


}