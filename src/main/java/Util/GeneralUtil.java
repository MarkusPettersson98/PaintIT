package Util;

import Model.WordAndGuess.Tile;

import java.util.List;

public class GeneralUtil {

    public static String charArrayListToString(List<Character> charArray){
       StringBuilder sB = new StringBuilder();
       for (Character c: charArray){
            sB.append(c);
        }
        return sB.toString() ;
    }
    public static String tileListToString(List<Tile> tiles){

        StringBuilder sB = new StringBuilder();
        for(Tile t: tiles){
            sB.append(t.getLetter());
        }
        return sB.toString();
    }
    public static String tileArrayToString(Tile... tiles){
        StringBuilder sB = new StringBuilder();
        for (Tile tile: tiles) {
            if(tile != null){
                sB.append(tile.getLetter());
            }
        }
        return sB.toString();
    }
}
