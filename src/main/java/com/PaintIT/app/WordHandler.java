package com.PaintIT.app;

import java.util.ArrayList;
import java.util.Random;

public class WordHandler {

    private ArrayList<String> wordList;
    private char[] tiles;
    private String currentWord;

    public WordHandler(){
        wordList= new ArrayList();
        wordList.add("CAT");
        wordList.add("DOG");
        wordList.add("MAN");
    }

    public ArrayList getWordList() {
        return wordList;
    }

    public char[] getTiles() {
        return tiles;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public String getRandomWord(){
        return wordList.get(getRandomIndex());

    }
    public int getRandomIndex(){
        Random rand = new Random();
        int indexAmount = wordList.size();
        return rand.nextInt(indexAmount); //returns random index from the wordlist
    }
}
