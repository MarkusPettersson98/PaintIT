package Tools;

import Canvas.CanvasController;
import Canvas.CanvasModel;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class BrushTest  {

    private Brush brush;
    @Before
    public void beforeBrushTest(){
        brush = new Brush();
    }

    @Test
    public void testInCircle() {
        int x = 4, y = 4;
        int posx = 5, posy = 5;
        int radius = 2;
        assertTrue(brush.inCircle(x, y, posx, posy, radius));
    }

    @Test
    public void testPaintCircle() {

        String dummyCircle = "[ false false true false false ] \n" +
                             "[ false true true true false ] \n" +
                             "[ true true true true true ] \n" +
                             "[ false true true true false ] \n" +
                             "[ false false true false false ] \n";

        CanvasController canvasController = new CanvasController();

        brush.setRadius(2);
        brush.addObserver(canvasController);
        brush.apply(2,2);

        assertEquals(dummyCircle, canvasController.toString());
    }

    @Test
    public void testPaintCircleOutsideCanvas() {

        String dummyCircle = "[ true true true true true ] \n" +
                "[ true true true true true ] \n" +
                "[ true true true true true ] \n" +
                "[ true true true true true ] \n" +
                "[ true true true true true ] \n";

        CanvasController canvasController = new CanvasController();

        brush.setRadius(10);
        brush.addObserver(canvasController);
        brush.apply(2,2);

        assertEquals(dummyCircle, canvasController.toString());
    }

}