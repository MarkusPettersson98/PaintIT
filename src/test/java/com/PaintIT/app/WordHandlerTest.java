package com.PaintIT.app;


import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WordHandlerTest {
    @Test
    public void removeCharFromGuess() throws Exception {
        WordHandler wordHandler = new WordHandler();
        wordHandler.addCharToGuess('c');
        wordHandler.addCharToGuess('a');
        wordHandler.addCharToGuess('h');
        assertEquals(3,wordHandler.getGuessWord().size()); //Checks arraysize
        wordHandler.removeCharFromGuess();
        assertEquals(2,wordHandler.getGuessWord().size()); //makes sure removeCharFromGuess works
        assertEquals('c',(char)wordHandler.getGuessWord().get(0));
        assertEquals('a',(char)wordHandler.getGuessWord().get(1));
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
        wordHandler.createRandomWord();
        String word = wordHandler.getCurrentWord();
        String guess = word;


        for(int i = 0; i<guess.length(); i++){
            wordHandler.addCharToGuess(guess.charAt(i));
        }
          assertTrue(wordHandler.guessCurrentWord());
        wordHandler.addCharToGuess('x');
        assertFalse(wordHandler.guessCurrentWord());
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