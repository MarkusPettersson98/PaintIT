package WordAndGuess;


import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WordHandlerTest {


    @Test
    public void getRandomWord() throws Exception {
        WordHandler wh = new WordHandler();
        
        String word = wh.getCurrentWord();
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
        GuessLogic guessLogic = new GuessLogic();
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