package Tools;
import Controller.CanvasController;
import Model.Canvas.SprayCan;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;

public class SprayCanTest {

    private SprayCan sprayCan;
    private CanvasController canvasController;
    @Before
    public void beforeSprayCanTest(){
        sprayCan = new SprayCan();
        canvasController = new CanvasController(5,5);
        canvasController.setToolRadius(5);
        canvasController.setCurrentTool(sprayCan);
    }


    @Test
    public void testSpray() {
        canvasController.fillCanvas(Color.WHITE);
        String dummyCanvas =
                        "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                        "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                        "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                        "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                        "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n";
        canvasController.useTool(2,2);
        // checks if the canvas has changed at all, if not identical as dummyCanvas, it has changed.
        // this test is flawed, because it is possible to fail "the odds", meaning not painting. 0.20^25, which is 0,3,3554432e-18 %.
        assertFalse(dummyCanvas.equals(canvasController.canvasToString()));
    }

}