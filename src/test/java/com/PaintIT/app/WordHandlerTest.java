package com.PaintIT.app;

import org.junit.Test;

import static org.junit.Assert.*;

public class WordHandlerTest {
    @Test
    public void getRandomWord() throws Exception {
        WordHandler wh = new WordHandler();
        String word= null;
        for (int i = 0; i<100; i++) {
            word = wh.getRandomWord();
            System.out.println(word);
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

}