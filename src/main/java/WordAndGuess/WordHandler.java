package WordAndGuess;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WordHandler {
    private List<String> wordList;
    private List<Tile> tiles;
    private String currentWord;
    private final int tileAmount;


    public WordHandler(){
        createWordList();
        pickRandomWord();
        createRandomTiles(this.currentWord);
        tileAmount = 8;
    }
    private void createWordList(){
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



    public int getTileAmount() {
        return tileAmount;
    }


    public List getWordList() {
        return wordList;
    }

    public List<Tile> getTiles() {
        return tiles;
    }
    public void createRandomTiles(String word){
        Random r = new Random();

        tiles = new ArrayList<>();

     for(int i = 0; i< word.length(); i ++){
         char c = Character.toUpperCase(word.charAt(i));

         tiles.add(new Tile(c,i));
     }
     for(int i = word.length(); i<tileAmount; i++){
         char d = Character.toUpperCase((char)(r.nextInt(26) + 'a'));
         tiles.add(new Tile(d,i));
     }
        Collections.shuffle(tiles);
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void pickRandomWord(){
        currentWord =  wordList.get(getRandomIndex());

    }
    int getRandomIndex(){
        Random rand = new Random();
        int indexAmount = wordList.size();
        return rand.nextInt(indexAmount); //returns random index from the wordlist
    }

}
