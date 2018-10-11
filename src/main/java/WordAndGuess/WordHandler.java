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
    private Word currentWord;
    private static final int tileAmount = 8;
    private Dictionary dictionary;


    /**
     *Creates a List of words
     * Picks a random word
     * Creates random Tiles
     */

    public WordHandler(){
        dictionary = new Dictionary();
        pickRandomWord();
        createRandomTiles(this.currentWord.getWord());
    }

    public int getTileAmount() {
        return tileAmount;
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
        return currentWord.getWord();
    }


    private void pickRandomWord(){
        currentWord =  dictionary.getRandomWord();
    }

}
