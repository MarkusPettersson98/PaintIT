package WordAndGuess;

import Tools.Observable;
import Tools.Observer;
import Util.GeneralUtil;

import java.util.ArrayList;
import java.util.List;

public class GuessLogic implements Observable {
    private String currentWord;
    private List<Tile> guessWord;
    private List<Tile> availableTiles;
    private WordHandler wordHandler;
    private List<Observer> observers;
    private Boolean correctGuessMade;

    public Boolean getCorrectGuessMade() {
        return correctGuessMade;
    }

    public GuessLogic(){
        correctGuessMade = false;
        this.wordHandler = new WordHandler();
        this.availableTiles = wordHandler.getTiles();
        this.currentWord = wordHandler.getCurrentWord();
        guessWord = new ArrayList<>();
        observers = new ArrayList<>();

    }

    public String getCurrentWord() {
        return currentWord;
    }

    public List<Tile> getAvailableTiles() {
        return availableTiles;
    }

    public void addTileToGuess(Tile c){
        guessWord.add(c);
        c.setStatus(Tile.Status.Used);
        if(guessWord.size() == currentWord.length()){
            guessCurrentWord();
        }
        notifyObservers();
    }

    public String getGuessString(){
        return  GeneralUtil.tileListToString(guessWord);
    }

    public List<Tile> getGuessWord() {
        return guessWord;
    }
    public void removeTileFromGuess() {
        if (guessWord.size() > 0) {
            guessWord.get(guessWord.size()-1).setStatus(Tile.Status.Available);
            guessWord.remove(guessWord.size() - 1);
        }
        notifyObservers();
    }
    public void guessCurrentWord(){
        String guessWord = GeneralUtil.tileListToString(this.guessWord);
        if(guessWord.equals(currentWord)){
            correctGuessMade = true;
        }
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
