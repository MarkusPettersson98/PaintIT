package Model.WordAndGuess;

import java.io.*;
import java.util.*;

import org.apache.commons.io.IOUtils;
import org.json.*;

/**
 * Dictionary reads and holds all words that the user can choose to paint.
 */
public class Dictionary {

    /**
     * Represent a stack of {@link Word}s with easy difficulty level.
     */
    private Stack<Word> easyDictionary = new Stack();

    /**
     * Represent a stack of {@link Word}s with medium difficulty level.
     */
    private Stack<Word> mediumDictionary = new Stack();

    /**
     * Represent a stack of {@link Word}s with hard difficulty level.
     */
    private Stack<Word> hardDictionary = new Stack();

    /**
     * Represent a list of {@link Word}s that the painter can choose to paint.
     */
    private List<Word>  possibleWords = new ArrayList();

    /**
     * The current {@link Word}.
     */
    private Word currentWord;

    /**
     *Calls method to stack up all words inside of dictionary.
     */
    public Dictionary() {
        generateWordList();
    }

    /**
     * Reads data from a json-file holding json-objects containing a word and a difficulty level.
     * Every json-object converts to a {@link Word} and pushes in to a relevant stack depening on difficulty level.
     */
    private void generateWordList() {
        final Random rand = new Random();
        String jsonContent = "";

        try {
            // Need to use InputStream, as the JAR executable will have trouble to read from file otherwise
            final InputStream jsonURL = getClass().getResourceAsStream("/dictionary/dictionary.json");
            final StringWriter writer = new StringWriter();
            IOUtils.copy(jsonURL, writer, "UTF-8");
            jsonContent = writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final JSONArray jsonArray = new JSONObject(jsonContent).getJSONArray("dictionary");

        for (int i = 0; i < jsonArray.length(); i++) {
            final String word = jsonArray.getJSONObject(i).getString("word");
            final Word.Difficulty difficulty_level = jsonArray.getJSONObject(i).getEnum(Word.Difficulty.class, "difficulty_level");

            switch (difficulty_level) {
                case EASY:
                    easyDictionary.add(rand.nextInt(easyDictionary.size() + 1), new Word(word, difficulty_level));
                    break;
                case MEDIUM:
                    mediumDictionary.add(rand.nextInt(mediumDictionary.size() + 1), new Word(word, difficulty_level));
                    break;
                case HARD:
                    hardDictionary.add(rand.nextInt(hardDictionary.size() + 1), new Word(word, difficulty_level));
                    break;
                default:
                    hardDictionary.add(rand.nextInt(hardDictionary.size() + 1), new Word(word, difficulty_level));

            }
        }


    }

    /** Returns one word, as long as the stack is not empty, from every difficulty level.
     *
     * @return A list with between zero and three {@link Word}s of different difficulty levels.
     */
    public List<Word> getPossibleWords(){
        possibleWords.clear();

        for (final Stack<Word> dictionary: Arrays.asList(easyDictionary, mediumDictionary, hardDictionary)) {
            if(!dictionary.isEmpty()){
                possibleWords.add(dictionary.pop());
            }
        }

        return possibleWords;
    }

    /** Sets a choosen word to the current word and pushes the not choosen words back to corresponding stack.
     *
     * @param choosenWord The word that has been choosen by the painter.
     */
    public void setCurrentWord(Word choosenWord){
        final Random rand = new Random();
        currentWord = choosenWord;

        possibleWords.remove(choosenWord);

        for (final Word word: possibleWords) {
            switch (word.getDifficulty_level()){
                case EASY:
                    easyDictionary.add(rand.nextInt(easyDictionary.size()+1),word);
                    break;
                case MEDIUM:
                    mediumDictionary.add(rand.nextInt(mediumDictionary.size()+1),word);
                    break;
                case HARD:
                    hardDictionary.add(rand.nextInt(hardDictionary.size()+1),word);
                    break;
            }
        }
    }

    /**
     * @return The current {@link Word} that has been set in a particular round.
     */
    public Word getCurrentWord(){
        return currentWord;
    }


}
