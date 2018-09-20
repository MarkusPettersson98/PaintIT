package Canvas;

import Tools.Observer;
import javafx.scene.paint.Color;

public class CanvasController implements Observer {

    CanvasModel canvasModel;
    CanvasView canvasView;

    public CanvasController() {
        this.canvasModel = new CanvasModel(Color.WHITE);
        this.canvasView = new CanvasView();
    }

    public CanvasController(CanvasView canvasView) {
        this.canvasModel = new CanvasModel(Color.WHITE);
        this.canvasView = canvasView;
    }

    public CanvasController(int xSize, int ySize) {
        this.canvasModel = new CanvasModel(xSize, ySize, Color.WHITE);
        this.canvasView = new CanvasView();
    }

    /** Paints model pixel with color
     *
     * @param x x-value of pixel.
     * @param y y-value of pixel.
     * @param newColor new color of pixel.
     */

    public void paint(int x, int y, Color newColor) {
        // Check if new color value is different from current value
        if(x > canvasModel.getXBound() || x < 0 || y > canvasModel.getYBound() || y < 0)
            return;

        if(!canvasModel.getPixel(x, y).equals(newColor))
         canvasModel.setPixel(x,y, newColor);

      }

    /** Calls {@link CanvasModel#fillCanvas(Color)}
     *
      * @param color The color that fills the canvas.
     */
    public void fillCanvas(Color color) {
        canvasModel.fillCanvas(color);
    }


    /** Calls {@link CanvasModel#resetCanvas()}
     *
     */
    public void clear() {
        canvasModel.resetCanvas();
    }

    /**
     * Calls {@link CanvasModel#toString()}
     * @return A string of the canvas.
     */
    @Override
    public String toString() {
        return canvasModel.toString();
    }
    
    @Override
    public void update(int x, int y, Color color) {
        paint(x, y, color);
        // Paint ACTUAL view
        //canvasView.getGraphicsContext().fillRect(x,y,x+1,y+1);
        canvasView.setPixel(x,y, color); // TODO CHECK WITH MODEL INSTEAD
    }
}
