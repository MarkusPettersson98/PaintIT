package WordAndGuess;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class WordHandler {
    private ArrayList<String> wordList;
    private ArrayList<Character> tiles;
    private String currentWord;
    private final int tileAmount = 8;
    private Guess guess;

    public WordHandler(){

        wordList= new ArrayList();
        wordList.add("Cat");
        wordList.add("Dog");
        wordList.add("Man");
        wordList.add("Kingen");
        wordList.add("Aron");

        for(int i= 0; i<wordList.size();i++){
            wordList.set(i,wordList.get(i).toUpperCase());
        }
        createRandomWord();
        guess = new Guess(this.getCurrentWord());
    }

    public Guess getGuess() {
        return guess;
    }

    public int getTileAmount() {
        return tileAmount;
    }




    public static String charArrayToString(ArrayList<Character> list){
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
