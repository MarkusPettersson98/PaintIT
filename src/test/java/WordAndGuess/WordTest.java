package WordAndGuess;

import Model.WordAndGuess.Word;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordTest {
    Word word;

    @Before
    public void setUpTest(){
        word = new Word("dog", Word.Difficulty.EASY);
    }

    @Test
    public void getWordTest(){
        assertTrue(word.getWord().equals("dog"));
    }

    @Test
    public void getDifficulty_levelTest(){
        assertTrue(word.getDifficulty_level().equals(Word.Difficulty.EASY));
    }
}
