package Tools;

import Canvas.CanvasController;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SprayCanTest {
/*

    The reason why a lot of these tests are commented is because they are no longer relevant
    after the refactor of how tools work. The business logic is a lot more compact and therefore
    needs a lot fewer tests.

    private SprayCan sprayCan;
    public CanvasController canvasController;
    @Before
    public void beforeSprayCanTest(){
        sprayCan = new SprayCan();
        canvasController = new CanvasController(5,5);
        sprayCan.addObserver(canvasController);
    }


    @Test
    public void testSpray() {
        sprayCan.setRadius(5);
        canvasController.fillCanvas(Color.WHITE);
        sprayCan.setOdds(0.20);
        String dummyCanvas =
                        "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                        "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                        "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                        "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                        "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n";
        sprayCan.apply(2,2, Color.BLACK);
        // checks if the canvas has changed at all, if not identical as dummyCanvas, it has changed.
        // this test is flawed, because it is possible to fail "the odds", meaning not painting. 0.20^25, which is 0,3,3554432e-18 %.
        assertFalse(dummyCanvas.equals(canvasController.toString()));
    }
    */
}