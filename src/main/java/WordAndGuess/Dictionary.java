package WordAndGuess;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;

public class Dictionary {

    private List<Word> dictionary = new ArrayList();

    Dictionary(){

    }


    private void generateWordList() throws IOException{
        Gson gson = new GsonBuilder().create();

        Reader reader = new InputStreamReader(getClass().getResourceAsStream("/dictionary.json"), "UTF-8");

        while(true){
            dictionary.add(new Word());
        }
    }


}
