package WordAndGuess;

import java.io.*;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.json.*;
import WordAndGuess.Word.difficulty;

public class Dictionary {

    private Stack<Word> easyDictionary = new Stack();
    private Stack<Word> mediumDictionary = new Stack();
    private Stack<Word> hardDictionary = new Stack();


    public Dictionary(){
        generateWordList();
    }


    private void generateWordList() {
        Random rand = new Random();
        String jsonContent = "";

        try {
            jsonContent = FileUtils.readFileToString(new File(getClass().getClassLoader().getResource("dictionary/dictionary.json").getFile()),"UTF-8");
        }catch (IOException e){
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONObject(jsonContent).getJSONArray("dictionary");

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
            if(easyDictionary.empty()){
                return null;
            }

            return easyDictionary.pop();
    }

    public Word getNextMediumWord(){
        if(mediumDictionary.empty()){
            return null;
        }

        return mediumDictionary.pop();
    }

    public Word getNextHardWord(){
        if(hardDictionary.empty()){
            return null;
        }

        return hardDictionary.pop();
    }


}
