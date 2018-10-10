package WordAndGuess;


public class Word {
    private String word;
    private Difficulty difficulty_level;

    public Word(String word,Difficulty difficulty_level){
        this.word = word;
        this.difficulty_level = difficulty_level;
    }

    public Difficulty getDifficulty_level() {
        return difficulty_level;
    }

    public String getWord() {
        return word;
    }

    public enum Difficulty {
        EASY, MEDIUM, HARD,NaN,
    }
}
