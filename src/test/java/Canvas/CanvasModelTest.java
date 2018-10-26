package Canvas;

import Model.Canvas.CanvasModel;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CanvasModelTest {
    private CanvasModel canvasModel;

    @Before
    public void setupTest() {
        this.canvasModel = new CanvasModel(5,5, Color.WHITE);
    }
    @Test
    public void testToString() throws Exception {
        String dummyCanvas = "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                             "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                             "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                             "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                             "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n";
         assertEquals(dummyCanvas,canvasModel.toString());
    }


    @Test
    public void testSetPixel() {

        // Sets canvasmodel to be a 2x2 matrix filled with the color white.
        this.canvasModel = new CanvasModel(2,2,Color.WHITE);

        // Attempts to set pixel [1][1] with the color black.
        canvasModel.setPixel(1,1,Color.BLACK);

        // A comparison String where the array is represented in RGB ranging
        // in decimals from 0 to 1.
        String dummyCanvas =
                "[ [ 1.0, 1.0, 1.0 ] [ 1.0, 1.0, 1.0 ] ] \n" +
                "[ [ 1.0, 1.0, 1.0 ] [ 0.0, 0.0, 0.0 ] ] \n";


        assertEquals(dummyCanvas, canvasModel.toString());
    }

    @Test
    public void testResetCanvas() throws Exception {
        // Fill a new canvas with true value
        CanvasModel trueCanvasModel = new CanvasModel(Color.BLACK);
        String dummyCanvas = new CanvasModel(Color.WHITE).toString();

        trueCanvasModel.resetCanvas();

        assertEquals(dummyCanvas,trueCanvasModel.toString());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSetPixelOutOfBounds() {
        canvasModel.setPixel(canvasModel.getXMax() + 1, canvasModel.getYMax() + 1, Color.WHITE);
    }

    @Test
    public void testGetPixel() {
        assertEquals(canvasModel.getPixel(1,1), Color.WHITE);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetPixelOutOfBounds() {
        canvasModel.getPixel(canvasModel.getXMax() + 1,canvasModel.getYMax() + 1);
    }

    @Test
    public void testInBounds() {
        assertTrue(canvasModel.inBounds(2, 2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOutOfBounds() {
        canvasModel.inBounds(canvasModel.getXMax() + 1,canvasModel.getYMax() + 1);
    }

}