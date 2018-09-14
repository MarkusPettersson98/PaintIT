package com.PaintIT.app;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class WordHandler {
    private ArrayList<Character> guessWord;
    private ArrayList<String> wordList;
    private ArrayList<Character> tiles;
    private String currentWord;
    private final int tileAmount = 8;

    public WordHandler(){
        guessWord = new ArrayList<>();
        wordList= new ArrayList();
        wordList.add("Cat");
        wordList.add("Dog");
        wordList.add("Man");
        wordList.add("Kingen");
        wordList.add("Aron");

        for(int i= 0; i<wordList.size();i++){
            wordList.set(i,wordList.get(i).toUpperCase());
        }
    }

    public void addCharToGuess(char c){
        guessWord.add(c);
    }

    public ArrayList<Character> getGuessWord() {
        return guessWord;
    }

    public int getTileAmount() {
        return tileAmount;
    }

    public void removeCharFromGuess(){
        if(guessWord.size()>0){
            guessWord.remove(guessWord.size()-1);

        }
    }
    public boolean guessCurrentWord(){

        String guessWord = charArrayToString(this.guessWord);
        if(guessWord.equals(currentWord)){
            return true;
        }else{
            return false;
        }
    }
    public String charArrayToString(ArrayList<Character> list){
        StringBuilder sB= new StringBuilder();
        for(Character c: list){
            sB.append(c);
        }
        return sB.toString();
    }

    public ArrayList getWordList() {
        return wordList;
    }

    public ArrayList<Character> getTiles() {

        return tiles;
    }
    public void createRandomTiles(String word){
        Random r = new Random();

        tiles = new ArrayList<>();
     for(int i = 0; i< word.length(); i ++){
         char c = Character.toUpperCase(word.charAt(i));
         tiles.add(c);
     }
     for(int i = word.length(); i<tileAmount; i++){
         char d = Character.toUpperCase((char)(r.nextInt(26) + 'a'));
         tiles.add(d);
     }
        Collections.shuffle(tiles);
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void createRandomWord(){
        currentWord =  wordList.get(getRandomIndex());

    }
    public int getRandomIndex(){
        Random rand = new Random();
        int indexAmount = wordList.size();
        return rand.nextInt(indexAmount); //returns random index from the wordlist
    }

}
