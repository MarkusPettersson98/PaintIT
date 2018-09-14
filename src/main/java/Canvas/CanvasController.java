package Canvas;

import Tools.Observer;

public class CanvasController implements Observer {

    CanvasModel canvasModel;

    public CanvasController() {
        this.canvasModel = new CanvasModel(false);
    }

    public void paint(int x, int y, boolean newColor) {
        // Check if new color value is different from current value
        int xBound = Math.min(x, canvasModel.getXBound());
        int yBound = Math.min(y, canvasModel.getYBound());
        if(canvasModel.getPixel(xBound, yBound) != newColor)
            canvasModel.setPixel(xBound,yBound, newColor);
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
