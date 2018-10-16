package WordAndGuess;

import Model.WordAndGuess.GuessLogic;
import Model.WordAndGuess.Tile;
import Model.WordAndGuess.WordHandler;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class WordHandlerTest {


    @Test
    public void getRandomWordTest() throws Exception {
        WordHandler wh = new WordHandler();
        wh.setCurrentWord(wh.getPossibleWords().get(0));
        
        String word = wh.getCurrentWord().getWord();
        assertNotNull(word);
    }


    @Test
    public void createRandomTiles()throws Exception{ //Checks if the random tiles contains the word letters
        GuessLogic guessLogic = new GuessLogic();
        guessLogic.setCurrentWord(guessLogic.getPossibleWords().get(0));

        Tile[] tiles = guessLogic.getAvailableTiles();
        ArrayList<Character> letters = new ArrayList<>();

        for(Tile t: tiles){
            letters.add(t.getLetter());
        }
        String word = guessLogic.getCurrentWord().getWord().toUpperCase();
        for(int i = 0; i<word.length(); i++){
             assertFalse(!letters.contains(word.charAt(i)));
        }

    }


}