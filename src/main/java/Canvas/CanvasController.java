package Canvas;

import Tools.Observer;

public class CanvasController implements Observer {

    CanvasModel canvasModel;
    CanvasView canvasView;

    public CanvasController() {
        this.canvasModel = new CanvasModel(false);
        this.canvasView = new CanvasView();
    }

    public CanvasController(CanvasView canvasView) {
        this.canvasModel = new CanvasModel(false);
        this.canvasView = canvasView;
    }

    public CanvasController(int xSize, int ySize) {
        this.canvasModel = new CanvasModel(xSize, ySize, false);
        this.canvasView = new CanvasView();
    }

    public void paint(int x, int y, boolean newColor) {
        // Check if new color value is different from current value
        if(x > canvasModel.getXBound() || x < 0 || y > canvasModel.getYBound() || y < 0)
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
        // Paint ACTUAL view
        //canvasView.getGraphicsContext().fillRect(x,y,x+1,y+1);
        canvasView.setPixel(x,y); // TODO CHECK WITH MODEL INSTEAD
    }
}
