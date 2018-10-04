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
        for(int i = 0; i<guessWord.length;i++){
            if(guessWord[i] == null){
                guessWord[i] = t;
                t.setPosGuess(i);
                break;
            }
        }
        t.setStatus(Tile.Status.Used);
        notifyObservers();
    }

    public String getGuessString(){
        return  GeneralUtil.tileArrayToString(guessWord);
    }

    public Tile[] getGuessWord() {
        return guessWord;
    }
    public void removeTileFromGuess() {
      for(int i = guessWord.length-1; i>=0; i--){
          if (guessWord[i]!= null){
              guessWord[i].setStatus(Tile.Status.Available);
              guessWord[i] = null;
                  break;
          }
      }
        notifyObservers();
    }
    public void removeTileFromGuess(Tile tile){
        guessWord[tile.getPosGuess()] = null;
        tile.setStatus(Tile.Status.Available);
        tile.setPosGuess(-1);
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
