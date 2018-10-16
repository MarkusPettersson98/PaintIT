package WordAndGuess;

import Model.WordAndGuess.Dictionary;
import Model.WordAndGuess.Word;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DictionaryTest {
    Dictionary dictionary;

    @Before
    public void setUpTest(){
        dictionary = new Dictionary();
    }

    @Test
    public void getPossibleWordsTest(){
        List<Word> words = dictionary.getPossibleWords();

        assertTrue(words.size() == 3);
        for(Word word: words){
            assertNotNull(word);
        }
    }

    @Test
    public void setCurrentWordTest(){
        List<Word> words = dictionary.getPossibleWords();
        Word word = words.get(0);
        dictionary.setCurrentWord(words.get(0));
        assertTrue(dictionary.getCurrentWord().equals(word));
    }
}
