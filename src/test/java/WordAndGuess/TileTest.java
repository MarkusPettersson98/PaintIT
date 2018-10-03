package WordAndGuess;

import org.junit.Test;

import static org.junit.Assert.*;

public class TileTest {
    @Test
    public void setLetter() throws Exception {
        Tile tile = new Tile('c',2);
        tile.setLetter('a');

        assertEquals('a',tile.getLetter());
        assertEquals(2,tile.getPosAvailable());
    }

    @Test
    public void setStatus() throws Exception {
        Tile tile = new Tile('a',0);
        assertEquals(Tile.Status.Available,tile.getStatus());
        tile.setStatus(Tile.Status.Used);
        assertEquals(Tile.Status.Used,tile.getStatus());


    }

}