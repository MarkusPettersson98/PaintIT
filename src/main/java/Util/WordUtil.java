package Util;

import Model.WordAndGuess.Tile;

import java.util.List;

public class WordUtil {

    public static String charArrayListToString(List<Character> charArray){
       final StringBuilder sB = new StringBuilder();
       for (final Character c: charArray){
            sB.append(c);
        }
        return sB.toString() ;
    }
    public static String tileListToString(List<Tile> tiles){

        final StringBuilder sB = new StringBuilder();
        for(final Tile t: tiles){
            sB.append(t.getLetter());
        }
        return sB.toString();
    }
    public static String tileArrayToString(Tile... tiles){
        final StringBuilder sB = new StringBuilder();
        for (final Tile tile: tiles) {
            if(tile != null){
                sB.append(tile.getLetter());
            }
        }
        return sB.toString();
    }
}
