package Game;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class PlayerTest {
    private Player player;

    @Before
    public void setUp(){
        player = new Player("Test");
    }

    @Test
    public void nameTest(){
        assertTrue(player.getName().equals("Test"));
    }

}
