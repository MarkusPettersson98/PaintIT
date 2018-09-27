package WordAndGuess;


public class Word {
    private String word;
    private difficulty difficulty_level;

    public Word(){
        word = "No word";
        difficulty_level = difficulty.NaN;
    }

    public Word(String word,difficulty difficulty_level){
        this.word = word;
        this.difficulty_level = difficulty_level;
    }

    public difficulty getDifficulty_level() {
        return difficulty_level;
    }

    public String getWord() {
        return word;
    }

    public enum difficulty{
        easy,medium,hard,NaN,
    }
}
