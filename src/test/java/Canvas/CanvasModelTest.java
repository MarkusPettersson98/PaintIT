package Canvas;

import junit.framework.TestCase;

public class CanvasModelTest extends TestCase {
    public void testToString() throws Exception {
        String dummyCanvas = "[false, false, false, false, false, ] \n" +
                             "[false, false, false, false, false, ] \n" +
                             "[false, false, false, false, false, ] \n" +
                             "[false, false, false, false, false, ] \n" +
                             "[false, false, false, false, false, ] \n";
         CanvasModel canvasModel = new CanvasModel();

         assertTrue(dummyCanvas.equals(canvasModel.toString()));
    }

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

}