package WordAndGuess;

import java.io.*;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.json.*;
import WordAndGuess.Word.Difficulty;

public class Dictionary {

    private Stack<Word> easyDictionary = new Stack();
    private Stack<Word> mediumDictionary = new Stack();
    private Stack<Word> hardDictionary = new Stack();
    private List<Word>  possibleWords = new ArrayList();
    private Word choosenWord;


    public Dictionary() {
        generateWordList();
    }


    private void generateWordList() {
        Random rand = new Random();
        String jsonContent = "";

        try {
            jsonContent = FileUtils.readFileToString(new File(getClass().getClassLoader().getResource("dictionary/dictionary.json").getFile()), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONObject(jsonContent).getJSONArray("dictionary");

        for (int i = 0; i < jsonArray.length(); i++) {
            String word = jsonArray.getJSONObject(i).getString("word");
            Difficulty difficulty_level = jsonArray.getJSONObject(i).getEnum(Difficulty.class, "difficulty_level");

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

    public Word getRandomWord() {
        Word nextWord = new Word("No more words!", Difficulty.EASY);

        if (easyDictionary.isEmpty() && mediumDictionary.isEmpty() && hardDictionary.isEmpty()) {
            return nextWord;
        }

        List<Stack<Word>> dictionaryStackList = Arrays.asList(easyDictionary, mediumDictionary, hardDictionary);
        Collections.shuffle(dictionaryStackList);

        for (Stack<Word> dictionary : dictionaryStackList) {
            if (!dictionary.isEmpty()) {
                nextWord = dictionary.pop();
                break;
            }
        }

        return nextWord;

    }

    public List<Word> getPossibleWords(){
        possibleWords.clear();

        for (Stack<Word> dictionary: Arrays.asList(easyDictionary, mediumDictionary, hardDictionary)) {
            if(!dictionary.isEmpty()){
                possibleWords.add(dictionary.pop());
            }
        }

        return possibleWords;
    }

    public void setChoosenWord(Word choosenWord){
        Random rand = new Random();
        this.choosenWord = choosenWord;

        possibleWords.remove(choosenWord);

        for (Word word: possibleWords) {
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

    public Word getChoosenWord(){
        return choosenWord;
    }


}
