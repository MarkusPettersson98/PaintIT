package WordAndGuess;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
/**
 *Wordhandler has the list of words for the game and generates a random word for the Game.
 * It also generates the random tiles accompanying the word
 */
public class WordHandler {
    private ArrayList<String> wordList;
    private Tile[] tiles;
    private String currentWord;
    private static final int tileAmount = 8;


    /**
     *Creates a List of words
     * Picks a random word
     * Creates random Tiles
     */

    public WordHandler(){
        createWordList();
        pickRandomWord();
        createRandomTiles(this.currentWord);
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


    public Tile[] getTiles() {
        return tiles.clone();
    }
    private void createRandomTiles(String word){
        Random r = new Random();
       ArrayList<Tile>  temp = new ArrayList<>();
     for(int i = 0; i< word.length(); i ++){
         char c = Character.toUpperCase(word.charAt(i));

         temp.add(new Tile(c,i));
     }
     for(int i = word.length(); i<tileAmount; i++){
         char d = Character.toUpperCase((char)(r.nextInt(26) + 'a'));
         temp.add(new Tile(d,i));
     }
        Collections.shuffle(temp);

        tiles = convertTilesToArray(temp);
    }
    private Tile[] convertTilesToArray(ArrayList<Tile> tiles){
        Tile[] tileArray = new Tile[tiles.size()];
        for(int i = 0; i<tiles.size(); i++){
            tileArray[i] = tiles.get(i);
        }
        return tileArray;
    }

    public String getCurrentWord() {
        return currentWord;
    }


    private void pickRandomWord(){
        currentWord =  wordList.get(getRandomIndex());
    }

    int getRandomIndex(){
        Random rand = new Random();
        int indexAmount = wordList.size();
        return rand.nextInt(indexAmount); //returns random index from the wordlist
    }

}
