package Util;

import Model.WordAndGuess.Tile;

import java.util.List;

public class WordUtil {

    /**
     * Convert an Arraylist of Charaters to a String
     * @param charArray
     * @return String of the charArray
     */
    public static String charArrayListToString(List<Character> charArray){
       final StringBuilder sB = new StringBuilder();
       for (final Character c: charArray){
            sB.append(c);
        }
        return sB.toString() ;
    }

    /**
     * Puts the characters from a ArrayList of Tiles together to a String
     * @param tiles
     * @return A String of the Characters
     */
    public static String tileListToString(List<Tile> tiles){

        final StringBuilder sB = new StringBuilder();
        for(final Tile t: tiles){
            sB.append(t.getLetter());
        }
        return sB.toString();
    }

    /**
     * Makes a string of the characters from every Tile in a Tile[]
     * @param tiles
     * @return a String
     */
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
