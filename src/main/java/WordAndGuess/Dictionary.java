package WordAndGuess;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.xsom.impl.WildcardImpl;
import org.apache.commons.io.FileUtils;
import org.json.*;
import WordAndGuess.Word.difficulty;

public class Dictionary {

    private List<Word> easyDictionary = new ArrayList();
    private List<Word> mediumDictionary = new ArrayList();
    private List<Word> hardDictionary = new ArrayList();

    public Dictionary(){
        try {
            generateWordList();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void generateWordList() throws IOException{
        File file = new File(getClass().getClassLoader().getResource("dictionary/dictionary.json").getFile());
        String content = FileUtils.readFileToString(file,"UTF-8");

        JSONArray jsonArray = new JSONObject(content).getJSONArray("dictionary");

        for(int i = 0; i < jsonArray.length(); i++){
            String word = jsonArray.getJSONObject(i).getString("word");
            difficulty difficulty_level = jsonArray.getJSONObject(i).getEnum(Word.difficulty.class,"difficulty_level");

            switch (difficulty_level){
                case easy:
                    easyDictionary.add(new Word(word,difficulty_level));
                    break;
                case medium:
                    mediumDictionary.add(new Word(word,difficulty_level));
                    break;
                case hard:
                    hardDictionary.add(new Word(word,difficulty_level));
                    break;
                default:
                    hardDictionary.add(new Word(word,difficulty.hard));
            }
        }



    }


}
