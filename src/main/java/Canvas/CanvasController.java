package Canvas;

import Tools.Brush;
import Tools.Eraser;
import Tools.SprayCan;
import Tools.Tool;
import javafx.scene.paint.Color;
import lombok.Getter;

import java.util.*;

public class CanvasController {

    /**
     * The CanvasModel that is being drawn on.
     */
    @Getter CanvasModel canvasModel;

    /**
     * The CanvasView that shows the {@link CanvasController#canvasModel}
     */
    @Getter CanvasView canvasView;
    /**
     * Holds {@link ColorPoint} of drawn over pixels, practically saving old pixels
     */
    List<ColorPoint> undoArrayList = new ArrayList<>();

    /**
     * Holds the {@link CanvasController#undoArrayList} in a stack allowing for "first in last out"-undoing.
     */
    Stack<List<ColorPoint>> undoStack = new Stack<>();

    Map<String, Tool> tools = new HashMap<>();

    Tool currentTool;

    public CanvasController() {
        this.canvasModel = new CanvasModel(Color.WHITE);
        this.canvasView = new CanvasView(canvasModel);
        setupTools();
    }

    /**
     * testing only
     * @param xSize
     * @param ySize
     */
    public CanvasController(int xSize, int ySize) {
        this.canvasModel = new CanvasModel(xSize, ySize, Color.WHITE);
        this.canvasView = new CanvasView(canvasModel);
        setupTools();
    }

    public void generateNewCanvas() {
        this.canvasModel = new CanvasModel(Color.WHITE);
        this.canvasView = new CanvasView(canvasModel);
    }

    public void setupTools() {
        tools.put(Brush.class.getSimpleName(), new Brush());
        tools.put(SprayCan.class.getSimpleName(),new SprayCan());
        tools.put(Eraser.class.getSimpleName(), new Eraser());
    }

    public void setCurrentTool(String stringTool) {
        currentTool = tools.get(stringTool);
    }

    /** Paints model pixel with color
     *
     * @param x x-value of pixel.
     * @param y y-value of pixel.
     * @param newColor new color of pixel.
     */
    public void paint(int x, int y, Color newColor) {
        // Check if new color value is different from current value
        if(x > canvasModel.getXBound() || x < 0 || y > canvasModel.getYBound() || y < 0) {
            return;
        }
        Color tmpColor = canvasModel.getPixel(x,y);
        if(!tmpColor.equals(newColor)) {
            undoArrayList.add(getColorPoint(x,y));
            canvasModel.setPixel(x, y, newColor);
        }
    }

    /**
     * Returns the CanvasModels Color, x and y in form of a ColorPoint.
     * @param x the x-pixel in the canvas.
     * @param y the y-pixel in the canvas.
     * @return A new ColorPoint from the canvasModel.
     */

    public ColorPoint getColorPoint(int x, int y) {
        return new ColorPoint(x,y,canvasModel.getPixel(x,y));
    }

    /** Calls {@link CanvasModel#fillCanvas(Color)}
     *
     * @param color The color that fills the canvas.
     */
    public void fillCanvas(Color color) {
        canvasModel.fillCanvas(color);
    }


    /**
     * Copies entire model into {@link CanvasController#undoArrayList}, is called upon by {@link CanvasController#clear()}
     */
    public void copyModelToList() {
        for(int y = 0; y < canvasModel.getYMax(); y++) {
            for (int x = 0; x < canvasModel.getXMax(); x++) {
                undoArrayList.add(new ColorPoint(x,y,canvasModel.getPixel(x,y)));
            }
        }
    }


    /** Clears the canvas by calling {@link CanvasModel#resetCanvas()}. Also uses {@link CanvasController#copyModelToList()} to allow for {@link CanvasController#undo} to work.
     */
    public void clear() {
        while(!undoStack.empty())
          undoStack.pop();
        // TODO TEST AND FIX, STILL BUGGY
        // undoArrayList.clear();
        // copyModelToList();
        // undoStack.push(undoArrayList);
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

    /**
     * Redraws the {@link CanvasView} by copying the {@link CanvasModel}, incase of  {@link java.util.Observer} not working.
     */

    public void redrawCanvasView() {
        for(int y = 0; y < canvasModel.getYMax(); y++) {
            for (int x = 0; x < canvasModel.getXMax(); x++) {
                canvasView.setPixel(x, y, canvasModel.getPixel(x,y));
            }
        }
    }

    /**
     * Pushes the {@link CanvasController#undoArrayList} to the {@link CanvasController#undoStack}, also clears the {@link CanvasController#undoArrayList}
     * Is called upon when user has finished drawing a line.
     */
    public void pushToUndoStack() {
        undoStack.push(new ArrayList<>(undoArrayList));
        undoArrayList.clear();
    }

    /**
     * Redraws pixels that were painted over by popping the {@link CanvasController#undoStack} and painting them on the {@link CanvasModel}
     */
    public void undo() {
        if(!undoStack.empty()) {
            for (ColorPoint cp : undoStack.pop()) {
                canvasModel.setPixel(cp.getX(),cp.getY(),cp.getC());
            }
        }
    }
    public boolean isUndoAvailable() {
        return !undoStack.isEmpty();
    }
}