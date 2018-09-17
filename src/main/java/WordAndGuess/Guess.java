package WordAndGuess;

import java.util.ArrayList;

public class Guess {
    private String currentWord;
    private ArrayList<Character> guessWord;

    public Guess(String currentWord){
        this.currentWord = currentWord;
        guessWord = new ArrayList<>();
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
