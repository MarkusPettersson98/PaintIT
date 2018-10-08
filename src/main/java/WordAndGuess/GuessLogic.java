package WordAndGuess;

import Tools.Observable;
import Tools.Observer;
import Util.GeneralUtil;

import java.util.ArrayList;

public class GuessLogic implements Observable {
    private String currentWord;
    private Tile[] guessWord;
    private Tile[] availableTiles;
    private WordHandler wordHandler;
    private ArrayList<Observer> observers;



    public GuessLogic(){

        this.wordHandler = new WordHandler();
        this.availableTiles = wordHandler.getTiles();
        this.currentWord = wordHandler.getCurrentWord();
        guessWord = new Tile[currentWord.length()];
        observers = new ArrayList<>();

    }

    public String getCurrentWord() {
        return currentWord;
    }

    public Tile[] getAvailableTiles() {
        return availableTiles;
    }

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
    private boolean isGuessFilled(){
        for(Tile t: guessWord){
            if(t == null){
                return false;
            }
        }
        return true;
    }
    public String getGuessString(){
        return  GeneralUtil.tileArrayToString(guessWord);
    }

    public Tile[] getGuessWord() {
        return guessWord;
    }


    public void removeTileFromGuess(Tile tile){
        int count = 0;
        for(Tile t: guessWord){
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


    public boolean guessCurrentWord(){
        String guessWord = GeneralUtil.tileArrayToString(this.guessWord);
        if(guessWord.equals(currentWord)){
            return true;
        }
        return false;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer obs: observers){
            obs.update();
        }
    }
}
