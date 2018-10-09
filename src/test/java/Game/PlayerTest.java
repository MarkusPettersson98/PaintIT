package Game;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class PlayerTest {
    public Player player;

    @Before
    public void setUp(){
        player = new Player();
        player.setName("Greta");
    }

    @Test
    public void nameTest(){
        assertTrue(player.getName().equals("Greta"));
    }

}
