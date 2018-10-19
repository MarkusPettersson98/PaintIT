package Model.WordAndGuess;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
/**
 *Wordhandler has the list of words for the game and generates a random word for the Game.
 * It also generates the random tiles accompanying the word
 */
public class WordHandler {
    private Tile[] tiles;
    private static final int tileAmount = 8;
    private Dictionary dictionary;

    /**
     *Creates a List of words
     * Picks a random word
     * Creates random Tiles
     */
    public WordHandler(){
        dictionary = new Dictionary();
    }


    public Tile[] getTiles() {
        return tiles.clone();
    }

    /**
     * Adds letters of the current word plus some random letters (total of eight letters) to a list of {@link Tile}s.
     * @param word A string with the current word.
     */
    private void createRandomTiles(String word){
        final Random r = new Random();
        final ArrayList<Tile>  temp = new ArrayList<>();
            for(int i = 0; i< word.length(); i ++){
                final char c = Character.toUpperCase(word.charAt(i));
                temp.add(new Tile(c,i));
     }
     for(int i = word.length(); i<tileAmount; i++){
         final char d = Character.toUpperCase((char)(r.nextInt(26) + 'a'));
         temp.add(new Tile(d,i));
     }
        Collections.shuffle(temp);

        tiles = convertTilesToArray(temp);
    }

    /**
     * Converts a list of {@link Tile}s to an array.
     * @param tiles A list of tiles that needs to be converted to an array.
     * @return An array with tiles.
     */
    private Tile[] convertTilesToArray(ArrayList<Tile> tiles){
        Tile[] tileArray = new Tile[tiles.size()];
        for(int i = 0; i<tiles.size(); i++){
            tileArray[i] = tiles.get(i);
        }
        return tileArray;
    }

    public Word getCurrentWord() {
        return dictionary.getCurrentWord();
    }

    /**
     * Sets a choosen word to current word and calls on createRandomTiles(String word) to create eight new tiles.
     * @param word An instance of word.
     */
    public void setCurrentWord(Word word){
        dictionary.setCurrentWord(word);
        createRandomTiles(word.getWord());
    }

    public List<Word> getPossibleWords(){
        return dictionary.getPossibleWords();
    }

}
