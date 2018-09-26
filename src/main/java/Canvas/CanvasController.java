package Canvas;

import javafx.scene.paint.Color;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Stack;

public class CanvasController {

    CanvasModel canvasModel;
    @Getter CanvasView canvasView;

    ArrayList<ColorPoint> undoArrayList = new ArrayList<>();
    ArrayList<ColorPoint> redoArrayList = new ArrayList<>();

    Stack<ArrayList<ColorPoint>> undoStack = new Stack<>();
    Stack<ArrayList<ColorPoint>> redoStack = new Stack<>();

    public CanvasController() {
        this.canvasModel = new CanvasModel(Color.WHITE);
        this.canvasView = new CanvasView(canvasModel);
        canvasModel.addObserver(canvasView);
    }

    public CanvasController(int xSize, int ySize) {
        this.canvasModel = new CanvasModel(xSize, ySize, Color.WHITE);
        this.canvasView = new CanvasView(canvasModel);
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

        if(!canvasModel.getPixel(x, y).equals(newColor)) {
            undoArrayList.add(getOldColor(x,y));

            canvasModel.setPixel(x, y, newColor);
        }
      }

    public ColorPoint getOldColor(int x, int y) {
          return new ColorPoint(x,y,canvasModel.getPixel(x,y));
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
        //undoArrayList.clear();
    }

    /**
     * Calls {@link CanvasModel#toString()}
     * @return A string of the canvas.
     */
    @Override
    public String toString() {
        return canvasModel.toString();
    }

    public void redraw() {
        for(int y = 0; y < canvasModel.getYMax(); y++) {
            for (int x = 0; x < canvasModel.getXMax(); x++) {
                canvasView.setPixel(x, y, canvasModel.getPixel(x,y));
            }
        }
    }

    public void pushToUndoStack() {
        undoStack.push(new ArrayList<>(undoArrayList));
        undoArrayList.clear();
    }

    public void undo() {
        if(!undoStack.empty()) {
            for (ColorPoint cp : undoStack.pop()) {
                redoArrayList.add(getOldColor(cp.getX(), cp.getY()));
                paint(cp.getX(), cp.getY(), cp.getC());
            }
            redoStack.push(new ArrayList<>(redoArrayList));
            redoArrayList.clear();
        }
    }

    public void redo() {
        if(!redoStack.empty()) {
            for (ColorPoint cp : redoStack.pop()) {
                paint(cp.getX(), cp.getY(), cp.getC());
            }
            undoStack.push(new ArrayList<>(undoArrayList));
            undoArrayList.clear();
        }
    }
}