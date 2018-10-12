package WordAndGuess;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DictionaryTest {
    Dictionary dictionary;

    @Before
    public void setUpTest(){
        dictionary = new Dictionary();
    }

    @Test
    public void getNextEasyWordTest(){
        Word easyWord = dictionary.getNextEasyWord();
        assertTrue(easyWord.getDifficulty_level().equals(Word.Difficulty.EASY));
        assertFalse(easyWord.getWord().equals(""));
        assertNotNull(easyWord.getWord());
    }

    @Test
    public void getNextMediumWordTest(){
        Word easyWord = dictionary.getNextMediumWord();
        assertTrue(easyWord.getDifficulty_level().equals(Word.Difficulty.MEDIUM));
        assertFalse(easyWord.getWord().equals(""));
        assertNotNull(easyWord.getWord());
    }

    @Test
    public void getNextHardWordTest(){
        Word easyWord = dictionary.getNextHardWord();
        assertTrue(easyWord.getDifficulty_level().equals(Word.Difficulty.HARD));
        assertFalse(easyWord.getWord().equals(""));
        assertNotNull(easyWord.getWord());
    }
}
