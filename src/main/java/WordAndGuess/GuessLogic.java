package WordAndGuess;

import Tools.Observable;
import Tools.Observer;
import Util.GeneralUtil;

import java.util.ArrayList;

public class GuessLogic implements Observable {
    private String currentWord;
    private ArrayList<Tile> guessWord;
    private ArrayList<Tile> availableTiles;
    private WordHandler wordHandler;
    private ArrayList<Observer> observers;

    public GuessLogic(){
        this.wordHandler = new WordHandler();
        this.availableTiles = wordHandler.getTiles();
        this.currentWord = wordHandler.getCurrentWord();
        guessWord = new ArrayList<>();
        observers = new ArrayList<>();

    }

    public String getCurrentWord() {
        return currentWord;
    }

    public ArrayList<Tile> getAvailableTiles() {
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

    public ArrayList<Tile> getGuessWord() {
        return guessWord;
    }
    public void removeTileFromGuess() {
        if (guessWord.size() > 0) {
            guessWord.get(guessWord.size()-1).setStatus(Tile.Status.Available);
            guessWord.remove(guessWord.size() - 1);
        }
        notifyObservers();
    }
    public boolean guessCurrentWord(){
        String guessWord = GeneralUtil.tileListToString(this.guessWord);
        System.out.println("Guess is: " + getGuessString());
        System.out.println("Correct word is" + currentWord);
        if(guessWord.equals(currentWord)){
            return true;
        }else{
            return false;
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
