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
    public CanvasController canvasController;
    @Before
    public void beforeBrushTest(){
        brush = new Brush();
        canvasController = new CanvasController();
        brush.addObserver(canvasController);
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

        brush.setRadius(2);
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

        brush.setRadius(10);
        brush.apply(2,2);

        assertEquals(dummyCircle, canvasController.toString());
    }

    @Test
    public void testPaintNegativeRadius() {
        brush.setRadius(-10);
        String dummyCanvas = "[ false false false false false ] \n" +
                "[ false false false false false ] \n" +
                "[ false false false false false ] \n" +
                "[ false false false false false ] \n" +
                "[ false false false false false ] \n";
        brush.apply(2,2);

        assertEquals(dummyCanvas,canvasController.toString());
    }

    @Test
    public void testPaintZeroRadius() {
        brush.setRadius(0);
        String dummyCanvas = "[ false false false false false ] \n" +
                "[ false false false false false ] \n" +
                "[ false false false false false ] \n" +
                "[ false false false false false ] \n" +
                "[ false false false false false ] \n";
        brush.apply(2,2);

        assertEquals(dummyCanvas,canvasController.toString());
    }
    @Test
    public void testPaintOutOfBounds() { //TODO: review functionality with out-of-bounds data.
        brush.setRadius(1);
        brush.apply(10,10);
        String dummyCanvas = "[ false false false false false ] \n" +
                "[ false false false false false ] \n" +
                "[ false false false false false ] \n" +
                "[ false false false false false ] \n" +
                "[ false false false false true ] \n"; // <-

        assertEquals(dummyCanvas,canvasController.toString());
    }
}