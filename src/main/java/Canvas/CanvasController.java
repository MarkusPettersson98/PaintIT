package Canvas;

import Tools.Observer;

public class CanvasController implements Observer {

    CanvasModel canvasModel;

    public CanvasController() {
        this.canvasModel = new CanvasModel(false);
    }

    public void paint(int x, int y, boolean color) {
        canvasModel.setPixel(x, y, color);
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
