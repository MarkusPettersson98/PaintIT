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

}