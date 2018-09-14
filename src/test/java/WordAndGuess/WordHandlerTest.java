package WordAndGuess;


import WordAndGuess.WordHandler;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WordHandlerTest {


    @Test
    public void getRandomWord() throws Exception {
        WordHandler wh = new WordHandler();
        String word= null;
        for (int i = 0; i<100; i++) {
            wh.createRandomWord();
            word = wh.getCurrentWord();
        }
        assertNotNull(word);
    }


    @Test
    public void getWordList() throws Exception {
            WordHandler wh = new WordHandler();
            assertNotNull(wh.getWordList());
    }

    @Test
    public void randomNumber() throws Exception{ //tests the random index
        WordHandler wordHandler = new WordHandler();
        for (int i = 0; i<100; i++) {
            if(wordHandler.getRandomIndex()>wordHandler.getWordList().size()-1){ //if index out of bounds
                 assertFalse(true);
            }
            assertTrue(true);
        }
    }
    @Test
    public void createRandomTiles()throws Exception{ //Checks if the random tiles contains the word letters
        WordHandler wordHandler = new WordHandler();
        wordHandler.createRandomWord();
        String word = wordHandler.getCurrentWord();
        wordHandler.createRandomTiles(word);
        ArrayList<Character> tiles = wordHandler.getTiles();
        for(int i = 0; i<word.length(); i++){
          if(!tiles.contains(word.charAt(i))){
             assertFalse(true);
          }

        }
        System.out.println("----------------------createRandomTilesTest---------------------------");
        System.out.println("THIS IS THE WORD :" + word);
       System.out.println("These are the Tiles" + tiles);
        assertTrue(true);

    }

}