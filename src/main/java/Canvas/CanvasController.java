package Canvas;

import Game.GameSession;
import javafx.scene.paint.Color;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Stack;

public class CanvasController {

    @Getter CanvasModel canvasModel;
    @Getter CanvasView canvasView;

    ArrayList<ColorPoint> undoArrayList = new ArrayList<>();
    Stack<ArrayList<ColorPoint>> undoStack = new Stack<>();

    public CanvasController() {
        this.canvasModel = new CanvasModel(Color.WHITE);
        this.canvasView = new CanvasView(canvasModel);
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


    public void copyModelToList() {
        for(int y = 0; y < canvasModel.getYMax(); y++) {
            for (int x = 0; x < canvasModel.getXMax(); x++) {
                undoArrayList.add(new ColorPoint(x,y,canvasModel.getPixel(x,y)));

            }
        }
    }


    /** Calls {@link CanvasModel#resetCanvas()}
     *
     */
    public void clear() {
        //while(!undoStack.empty())
        //  undoStack.pop();
        undoArrayList.clear();
        copyModelToList();
        undoStack.push(undoArrayList);
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
                canvasModel.setPixel(cp.getX(),cp.getY(),cp.getC());
            }
        }
    }
}