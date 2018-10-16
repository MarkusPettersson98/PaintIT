package Model.WordAndGuess;

/**
 *Word is made of a paintable word and a difficulty level of painting/guessing the word (Easy, Medium or Hard).
 */
public class Word {

    /**
     * A string that holds the word.
     */
    private String word;

    /**
     * A {@link Difficulty} that holds the difficulty level.
     */
    private Difficulty difficulty_level;

    /**
     * @param word A string that represent the word.
     * @param difficulty_level A difficulty level of painting/guessing the word.
     */
    public Word(String word,Difficulty difficulty_level){
        this.word = word;
        this.difficulty_level = difficulty_level;
    }

    /**
     * @return The difficulty level.
     */
    public Difficulty getDifficulty_level() {
        return difficulty_level;
    }

    /**
     * @return The word.
     */
    public String getWord() {
        return word;
    }

    /**
     * Enum made of three elements: EASY, MEDIUM and HARD
     */
    public enum Difficulty {
        EASY, MEDIUM, HARD,
    }
}
