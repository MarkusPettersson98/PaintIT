package WordAndGuess;

import Util.GeneralUtil;

import java.util.ArrayList;

public class GuessLogic {
    private String currentWord;
    private ArrayList<Tile> guessWord;
    private ArrayList<Tile> availableTiles;
    private WordHandler wordHandler;

    public GuessLogic(){
        this.wordHandler = new WordHandler();
        this.availableTiles = wordHandler.getTiles();
        this.currentWord = wordHandler.getCurrentWord();
        guessWord = new ArrayList<>();

    }

    public String getCurrentWord() {
        return currentWord;
    }

    public ArrayList<Tile> getAvailableTiles() {
        return availableTiles;
    }

    public void addTileToGuess(Tile c){
        guessWord.add(c);
        if(guessWord.size() == currentWord.length()){
            guessCurrentWord();

        }
    }

    public String guessToString(){
        return  GeneralUtil.tileListToString(guessWord);
    }

    public ArrayList<Tile> getGuessWord() {
        return guessWord;
    }
    public void removeCharFromGuess() {
        if (guessWord.size() > 0) {
            guessWord.remove(guessWord.size() - 1);

        }
    }
    public boolean guessCurrentWord(){

        String guessWord = GeneralUtil.tileListToString(this.guessWord);
        System.out.println("Guess is: " + guessToString());
        System.out.println("Correct word is" + currentWord);
        if(guessWord.equals(currentWord)){
            return true;
        }else{
            return false;
        }
    }
}
