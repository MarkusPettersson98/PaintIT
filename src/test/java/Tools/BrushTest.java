package Tools;

import Canvas.CanvasController;
import Canvas.CanvasModel;
import javafx.scene.paint.Color;
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
        canvasController = new CanvasController(5,5);
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

        String dummyCircle = "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 0.0, 0.0, 0.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                             "[ [ 1.0, 1.0, 1.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                             "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                             "[ [ 1.0, 1.0, 1.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                             "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 0.0, 0.0, 0.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n";

        brush.setRadius(2);
        brush.apply(2,2);

        assertEquals(dummyCircle, canvasController.toString());
    }

    @Test
    public void testPaintCircleOutsideCanvas() {

        String dummyCircle =
                "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n";

        brush.setColor(Color.BLACK);
        canvasController.fillCanvas(Color.WHITE);
        brush.setRadius(10);
        brush.apply(2,2);

        assertEquals(dummyCircle, canvasController.toString());
    }

    @Test
    public void testPaintNegativeRadius() {
        brush.setRadius(-10);
        canvasController.fillCanvas(Color.BLACK);
        String dummyCanvas =
                "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                        "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                        "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                        "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                        "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n";
        brush.setColor(Color.WHITE);
        brush.apply(2,2);

        assertEquals(dummyCanvas,canvasController.toString());
    }

    @Test
    public void testPaintZeroRadius() {
        brush.setRadius(0);
        String dummyCanvas =
                "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 0.0, 0.0, 0.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n";
        brush.apply(2,2);

        assertEquals(dummyCanvas,canvasController.toString());
    }
    @Test
    public void testPaintOutOfBounds() {
        brush.setRadius(1);
        brush.apply(10,10);
        String dummyCanvas =
                        "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                        "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                        "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                        "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                        "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n";

        assertEquals(dummyCanvas,canvasController.toString());
    }
}