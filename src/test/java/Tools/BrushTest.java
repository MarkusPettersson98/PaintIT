package Tools;

import Controller.CanvasController;
import Model.Canvas.Brush;
import javafx.scene.paint.Color;
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
        brush.setColor(Color.BLACK);
        canvasController.setCurrentTool("Brush");
    }

    @Test
    public void testInCircle() {
        int x = 4, y = 4;
        int posx = 5, posy = 5;
        int radius = 2;
        assertTrue(brush.inCircle(x, y, posx, posy, radius));
    }



   /* The reason why a lot of these tests are commented is because they are no longer relevant
    after the refactor of how tools work. The business logic is a lot more compact and therefore
    needs a lot fewer tests.*/


    @Test
    public void testPaintCircle() {

        String dummyCircle = "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 0.0, 0.0, 0.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                             "[ [ 1.0, 1.0, 1.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                             "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                             "[ [ 1.0, 1.0, 1.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                             "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 0.0, 0.0, 0.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n";
        canvasController.setToolRadius(2);
        canvasController.useTool(2,2);
        assertEquals(dummyCircle, canvasController.canvasToString());
    }


    @Test
    public void testPaintCircleOutsideCanvas() {

        String dummyCircle =
                "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n";

        canvasController.fillCanvas(Color.WHITE);
        canvasController.setToolRadius(10);
        canvasController.useTool(2,2);

        assertEquals(dummyCircle, canvasController.canvasToString());
    }

    @Test
    public void testPaintNegativeRadius() {
        String dummyCanvas =
                "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                        "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                        "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                        "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                        "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n";
        canvasController.setToolRadius(-10);
        canvasController.fillCanvas(Color.BLACK);
        canvasController.setToolColor(Color.WHITE);
        canvasController.useTool(2,2);

        assertEquals(dummyCanvas,canvasController.canvasToString());
    }

    @Test
    public void testPaintZeroRadius() {
        String dummyCanvas =
                "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 0.0, 0.0, 0.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n";
        canvasController.setToolRadius(0);
        canvasController.useTool(2,2);

        assertEquals(dummyCanvas,canvasController.canvasToString());
    }
    @Test
    public void testPaintOutOfBounds() {
        canvasController.setToolRadius(1);
        canvasController.useTool(10,10);
        String dummyCanvas =
                        "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                        "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                        "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                        "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                        "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n";

        assertEquals(dummyCanvas,canvasController.canvasToString());
    }


}