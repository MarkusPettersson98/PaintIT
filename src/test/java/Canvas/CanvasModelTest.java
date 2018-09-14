package Canvas;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CanvasModelTest {
    public CanvasModel canvasModel;

    @Before
    public void setupTest() {
        this.canvasModel = new CanvasModel();
    }
    @Test
    public void testToString() throws Exception {
        String dummyCanvas = "[false, false, false, false, false, ] \n" +
                             "[false, false, false, false, false, ] \n" +
                             "[false, false, false, false, false, ] \n" +
                             "[false, false, false, false, false, ] \n" +
                             "[false, false, false, false, false, ] \n";
         assertTrue(dummyCanvas.equals(canvasModel.toString()));
    }
    @Test
    public void testSetPixel() {
        CanvasModel canvasModel = new CanvasModel();
        canvasModel.setPixel(3,3,true);

        String dummyCanvas = "[false, false, false, false, false, ] \n" +
                             "[false, false, false, false, false, ] \n" +
                             "[false, false, false, false, false, ] \n" +
                             "[false, false, false, true, false, ] \n"  +
                             "[false, false, false, false, false, ] \n";

        assertTrue(dummyCanvas.equals(canvasModel.toString()));
    }
    @Test
    public void testGetPixel() {
        CanvasModel canvasModel = new CanvasModel();
        assertEquals(canvasModel.getPixel(1,1), false);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetPixelOutOfBounds() {
        CanvasModel canvasModel = new CanvasModel();
        canvasModel.getPixel(10,10);
    }
}