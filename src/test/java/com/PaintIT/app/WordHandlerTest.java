package com.PaintIT.app;


import WordAndGuess.WordHandler;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WordHandlerTest {
    @Test
    public void removeCharFromGuess() throws Exception {
        WordHandler wordHandler = new WordHandler();
        wordHandler.getGuess().addCharToGuess('c');
        wordHandler.getGuess().addCharToGuess('a');
        wordHandler.getGuess().addCharToGuess('h');
        assertEquals(3,wordHandler.getGuess().getGuessWord().size()); //Checks arraysize
        wordHandler.getGuess().removeCharFromGuess();
        assertEquals(2,wordHandler.getGuess().getGuessWord().size()); //makes sure removeCharFromGuess works
        assertEquals('c',(char)wordHandler.getGuess().getGuessWord().get(0));
        assertEquals('a',(char)wordHandler.getGuess().getGuessWord().get(1));
    }

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
    public void guessCurrentWord() throws Exception {
        WordHandler wordHandler = new WordHandler();
        String word = wordHandler.getCurrentWord();
        String guess = word;


        for(int i = 0; i<guess.length(); i++){
            wordHandler.getGuess().addCharToGuess(guess.charAt(i));
        }
          assertTrue(wordHandler.getGuess().guessCurrentWord());
        wordHandler.getGuess().addCharToGuess('x');
        assertFalse(wordHandler.getGuess().guessCurrentWord());
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