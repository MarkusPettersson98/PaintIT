package Tools;

import Controller.CanvasController;
import Model.Tools.Eraser;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EraserTest {


   /* The reason why a lot of these tests are commented is because they are no longer relevant
    after the refactor of how tools work. The business logic is a lot more compact and therefore
    needs a lot fewer tests.
*/

    private Eraser eraser;
    public CanvasController canvasController;
    @Before
    public void beforeEraserTest(){
        eraser = new Eraser();
        canvasController = new CanvasController(5,5);
        canvasController.setCurrentTool("Eraser");
    }

    @Test
    public void eraseTest() {
        canvasController.fillCanvas(Color.BLACK);
        String dummyCircle =
                        "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                        "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 1.0, 1.0, 1.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                        "[ [ 0.0, 0.0, 0.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                        "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 1.0, 1.0, 1.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n" +
                        "[ [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] [ 0.0, 0.0, 0.0 ] ] \n";
        canvasController.setToolRadius(1);
        canvasController.useTool(2,2);
        assertEquals(dummyCircle,canvasController.canvasToString());
    }

}