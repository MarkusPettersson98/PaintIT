package WordAndGuess;

import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;

import com.sun.xml.internal.xsom.impl.WildcardImpl;
import org.apache.commons.io.FileUtils;
import org.json.*;
import WordAndGuess.Word.difficulty;

public class Dictionary {

    private Stack<Word> easyDictionary = new Stack();
    private Stack<Word> mediumDictionary = new Stack();
    private Stack<Word> hardDictionary = new Stack();


    public Dictionary(){
        try {
            generateWordList();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void generateWordList() throws IOException{
        Random rand = new Random();
        String dictionaryContent = FileUtils.readFileToString(new File(getClass().getClassLoader().getResource("dictionary/dictionary.json").getFile()),"UTF-8");
        JSONArray jsonArray = new JSONObject(dictionaryContent).getJSONArray("dictionary");

        for(int i = 0; i < jsonArray.length(); i++){
            String word = jsonArray.getJSONObject(i).getString("word");
            difficulty difficulty_level = jsonArray.getJSONObject(i).getEnum(Word.difficulty.class,"difficulty_level");

            switch (difficulty_level){
                case easy:
                    easyDictionary.add(rand.nextInt(easyDictionary.size()+1),new Word(word,difficulty_level));
                    break;
                case medium:
                    mediumDictionary.add(rand.nextInt(mediumDictionary.size()+1),new Word(word,difficulty_level));
                    break;
                case hard:
                    hardDictionary.add(rand.nextInt(hardDictionary.size()+1),new Word(word,difficulty_level));
                    break;
                default:
                    hardDictionary.add(rand.nextInt(hardDictionary.size()+1),new Word(word,difficulty_level));

            }
        }



    }

    public Word getNextEasyWord(){
            return easyDictionary.pop();
    }

    public Word getNextMediumWord(){
        return mediumDictionary.pop();
    }

    public Word getNextHardWord(){
        return hardDictionary.pop();
    }


}
