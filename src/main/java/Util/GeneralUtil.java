package Util;

import java.util.ArrayList;

public class GeneralUtil {


    public static String CharArrayListToString(ArrayList<Character> charArray){

       StringBuilder sB = new StringBuilder();
       for (Character c: charArray){
            sB.append(c);
        }

        return sB.toString() ;
    }

}
