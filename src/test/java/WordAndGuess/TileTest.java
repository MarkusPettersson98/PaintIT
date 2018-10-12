package WordAndGuess;

import org.junit.Test;

import static org.junit.Assert.*;

public class TileTest {
    Tile tile;

    @Test
    public void setLetterTest() throws Exception {
        tile = new Tile('c',2);
        tile.setLetter('a');

        assertEquals('a',tile.getLetter());
        assertEquals(2,tile.getPosAvailable());
    }

    @Test
    public void setStatusTest() throws Exception {
        tile = new Tile('a',0);
        assertEquals(Tile.Status.Available,tile.getStatus());
        tile.setStatus(Tile.Status.Used);
        assertEquals(Tile.Status.Used,tile.getStatus());
    }

    @Test
    public void toStringTest(){
        tile = new Tile('b',0);
        assertTrue(tile.toString().equals("b"));
    }

    @Test
    public void setPosAvailableTest(){
        tile = new Tile('a',0);
        assertTrue(tile.getPosAvailable() == 0);
        tile.setPosAvailable(1);
        assertTrue(tile.getPosAvailable() == 1);
    }

}