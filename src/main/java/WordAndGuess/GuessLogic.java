package WordAndGuess;

import Util.GeneralUtil;

import java.util.ArrayList;

public class GuessLogic {
    private String currentWord;
    private ArrayList<Character> guessWord;
    private ArrayList<Character> availableTiles;
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

    public ArrayList<Character> getAvailableTiles() {
        return availableTiles;
    }

    public void addCharToGuess(char c){
        guessWord.add(c);
        if(guessWord.size() == currentWord.length()){
            guessCurrentWord();

        }
    }

    public ArrayList<Character> getGuessWord() {
        return guessWord;
    }
    public void removeCharFromGuess() {
        if (guessWord.size() > 0) {
            guessWord.remove(guessWord.size() - 1);

        }
    }
    public boolean guessCurrentWord(){
        String guessWord = GeneralUtil.CharArrayListToString(this.guessWord);
        System.out.println("Guess is: " + guessWord);
        System.out.println("Correct word is" + currentWord);
        if(guessWord.equals(currentWord)){
            return true;
        }else{
            return false;
        }
    }
}
