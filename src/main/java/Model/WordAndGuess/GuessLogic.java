package Model.WordAndGuess;

import Util.Observable;
import Util.Observer;
import Util.WordUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *Contains the logic for the guess of the word of the Game.
 * It´s purpose is to keep track of the guess and check whether it is correct or not
 */
public class GuessLogic implements Observable {

    private String currentWord;

    private Tile[] guessWord;
    private Tile[] availableTiles;
    private WordHandler wordHandler;
    private List<Observer> observers;



    /**
     *Instansiates a wordHandler that generates a word for the game and random Tiles to pick from
     */
    public GuessLogic(){
        this.wordHandler = new WordHandler();
    }


    public Word getCurrentWord() {
        return wordHandler.getCurrentWord();
    }


    public Tile[] getAvailableTiles() {
        return availableTiles.clone();
    }

    /**
     *Adds a tile t to the first empty slot of the guessWord ( Tile[] )
     * @param t The tile that is added to the guess.
     */
    public void addTileToGuess(Tile t){
        if(!isGuessFilled()) {
            for (int i = 0; i < guessWord.length; i++) {
                if (guessWord[i] == null) {
                    guessWord[i] = t;
                    break;
                }
            }
            t.setStatus(Tile.Status.Used);
            notifyObservers();

        }
    }

    /**
     * Checks if the guesser has filled up all empty slots.
     * @return A boolean.
     */
    private boolean isGuessFilled(){
        for(final Tile t: guessWord){
            if(t == null){
                return false;
            }
        }
        return true;
    }

    public String getGuessString(){
        return  WordUtil.tileArrayToString(guessWord);
    }


    public Tile[] getGuessWord() {
        return guessWord.clone();
    }

    /**
     *Removes a tile From the guess
     * @param tile the tile that is removed from the guess.
     */
    public void removeTileFromGuess(Tile tile){
        int count = 0;
        for(final Tile t: guessWord){
            if(guessWord[count] == tile) {
                tile.setStatus(Tile.Status.Available);
                guessWord[count] = null;
                count++;
                break;
            }
            count++;
        }
        notifyObservers();
    }

    /**
     * Removes rightmost tile from the guessed word
     */
    public void removeRightMostTileFromGuess(){
        for (int c = currentWord.length()-1; c >= 0; c-- ) {
            if(guessWord[c]!=null) {
                removeTileFromGuess(guessWord[c]);
                break;
            }
        }
    }


    /**
     *@return false if guess was wrong
     * @return True if guess was correct
     */
    public boolean guessCurrentWord(){
        final String guessWord = WordUtil.tileArrayToString(this.guessWord);
        if(guessWord.toLowerCase().equals(currentWord.toLowerCase())){
            return true;
        }
        return false;
    }

    public void setCurrentWord(Word word){
        wordHandler.setCurrentWord(word);
        this.availableTiles = wordHandler.getTiles();
        this.currentWord = wordHandler.getCurrentWord().getWord();
        guessWord = new Tile[currentWord.length()];
        observers = new ArrayList<>();

    }
    public List<Word> getPossibleWords(){
        return wordHandler.getPossibleWords();
    }

    /**
     * Adds observers to a list of observers.
     * @param observer The Observer of the object.
     */
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Notify all observers.
     */
    @Override
    public void notifyObservers() {
        for (final Observer obs: observers){
            obs.update();
        }
    }
}
