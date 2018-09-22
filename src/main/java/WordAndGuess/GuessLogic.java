package WordAndGuess;

import java.util.ArrayList;

public class GuessLogic {
    private String currentWord;
    private ArrayList<Character> guessWord;
    private ArrayList<Character> availableTiles;

    public GuessLogic(String currentWord,ArrayList<Character> availableTiles){
        this.availableTiles = availableTiles;
        this.currentWord = currentWord;
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
        String guessWord = WordHandler.charArrayToString(this.guessWord);
        if(guessWord.equals(currentWord)){
            return true;
        }else{
            return false;
        }
    }
}
