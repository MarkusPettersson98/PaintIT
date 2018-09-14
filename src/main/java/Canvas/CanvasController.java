package Canvas;

public class CanvasController {
    CanvasModel canvasModel;

    public CanvasController() {
        this.canvasModel = new CanvasModel(false);
    }

    private void paint(int x, int y, boolean color) {
        canvasModel.setPixel(x, y, color);
    }

    private void clear() {
        canvasModel.resetCanvas();
    }
}
