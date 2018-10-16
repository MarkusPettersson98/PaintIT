package Util;

import Model.WordAndGuess.Tile;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GeneralUtilTest {

    @Test
    public void charArrayListToStringTest(){
        List<Character> characterList = new ArrayList();
        characterList.add('b');
        characterList.add('o');
        characterList.add('n');
        characterList.add('d');

        assertTrue(GeneralUtil.charArrayListToString(characterList).equals("bond"));
    }

    @Test
    public void tileListToStringTest(){
        List<Tile> tileList = new ArrayList();
        tileList.add(new Tile('h',0));
        tileList.add(new Tile('e',1));
        tileList.add(new Tile('y',2));

        assertTrue(GeneralUtil.tileListToString(tileList).equals("hey"));
    }

    @Test
    public void tileArrayToStringTest(){
        Tile[] tiles = new Tile[4];
        tiles[0] = new Tile('j',0);
        tiles[1] = new Tile('a',1);
        tiles[2] = new Tile('v',2);
        tiles[3] = new Tile('a',3);

        assertTrue(GeneralUtil.tileArrayToString(tiles).equals("java"));
    }
}
