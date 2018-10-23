package Tools;

import Model.Canvas.CircleTool;
import org.junit.Test;

import static org.junit.Assert.*;

public class CircleToolTest {

    int radius = 0;
    CircleTool circleTool = new CircleTool(radius);


    @Test
    public void inArea() {
        int x = 4, y = 4;
        int posx = 5, posy = 5;
        int radius = 2;
        circleTool.setRadius(radius);
        assertTrue(circleTool.inArea(x, y, posx, posy));
    }

    @Test
    public void outsideOfArea() {
        int x = 2, y = 2;
        int posx = 5, posy = 5;
        int radius = 2;
        circleTool.setRadius(radius);
        assertFalse(circleTool.inArea(x, y, posx, posy));
    }


}