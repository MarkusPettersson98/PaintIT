package Canvas;

import Tools.Observer;

public class CanvasController implements Observer {

    CanvasModel canvasModel;

    public CanvasController() {
        this.canvasModel = new CanvasModel(false);
    }

    public CanvasController(int xSize, int ySize) {
        this.canvasModel = new CanvasModel(xSize, ySize, false);
    }

    public void paint(int x, int y, boolean newColor) {
        // Check if new color value is different from current value
        if(x > canvasModel.getXBound() || y > canvasModel.getYBound())
            return;

        if(canvasModel.getPixel(x, y) != newColor)
         canvasModel.setPixel(x,y, newColor);

      }

    public void clear() {
        canvasModel.resetCanvas();
    }
    @Override
    public String toString() {
        return canvasModel.toString();
    }

    @Override
    public void update(int x, int y, boolean color) {
        paint(x, y, color);
    }
}
