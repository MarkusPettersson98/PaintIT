package Controller;

import Views.Components.CanvasView;
import Util.ColorPoint;
import Model.Canvas.CanvasModel;
import Model.Canvas.Brush;
import Model.Canvas.Eraser;
import Model.Canvas.SprayCan;
import Model.Canvas.Tool;
import javafx.scene.paint.Color;
import lombok.Getter;

import java.util.*;

public class CanvasController {

    /**
     * The CanvasModel that is being drawn on.
     */
    @Getter private CanvasModel canvasModel;

    /**
     * The CanvasView that shows the {@link CanvasController#canvasModel}
     */
    @Getter private CanvasView canvasView;
    /**
     * Holds {@link ColorPoint} of drawn over pixels, practically saving old pixels
     */
    private List<ColorPoint> undoArrayList = new ArrayList<>();

    /**
     * Holds the {@link CanvasController#undoArrayList} in a stack allowing for "first in last out"-undoing.
     */
    private Stack<List<ColorPoint>> undoStack = new Stack<>();

    private Map<String, Tool> tools = new HashMap<>();

    private Tool currentTool;

    private int currentx0;
    private int currenty0;

    public CanvasController() {
        generateNewCanvas();
        setupTools();
        setCurrentTool(Brush.class.getSimpleName());
    }

    /**
     * testing only
     * @param xSize
     * @param ySize
     */
    public CanvasController(int xSize, int ySize) {
        generateNewCanvas(xSize, ySize);
        setupTools();
    }

    public void generateNewCanvas(int x, int y) {
        this.canvasModel = new CanvasModel(x, y, Color.WHITE);
        this.canvasView = new CanvasView(canvasModel);
        canvasModel.addObserver(canvasView);
    }

    public void generateNewCanvas() {
        this.canvasModel = new CanvasModel(Color.WHITE);
        this.canvasView = new CanvasView(canvasModel);
        canvasModel.addObserver(canvasView);
    }

    private void setupTools() {
        tools.put(Brush.class.getSimpleName(), new Brush());
        tools.put(SprayCan.class.getSimpleName(),new SprayCan());
        tools.put(Eraser.class.getSimpleName(), new Eraser());
    }

    public void setCurrentTool(String stringTool) {
        currentTool = tools.get(stringTool);
    }

    public void setCurrentTool(Tool tool) {
        currentTool = tool;
    }


    private boolean inCircle(int posx, int posy) {
        return (Math.pow(posx - currentx0, 2) + Math.pow(posy - currenty0, 2)) <= Math.pow(currentTool.getRadius(), 2);
    }

    public void useTool(int x0, int y0) {
        final int radius = currentTool.getRadius();
        this.currentx0 = x0;
        this.currenty0 = y0;
        for (int posx = (x0 - radius); posx <= (x0 + radius); posx++) {
            for (int posy = (y0 - radius); posy <= (y0 + radius); posy++) {
                if (checkPoint(posx,posy)) {
                    paint(posx, posy, currentTool.getColor());
                }
            }
        }
    }

    private boolean checkPoint(int posx, int posy) {
        return (currentTool.apply(currentx0, currenty0, posx, posy) && inCircle(posx,posy));
    }

    /** Paints model pixel with color
     *
     * @param x x-value of pixel.
     * @param y y-value of pixel.
     * @param newColor new color of pixel.
     */
    private void paint(int x, int y, Color newColor) {
        // Check if new color value is different from current value
        if(x > canvasModel.getXBound() || x < 0 || y > canvasModel.getYBound() || y < 0) {
            return;
        }
        final Color tmpColor = canvasModel.getPixel(x,y);
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

    private ColorPoint getColorPoint(int x, int y) {
        return new ColorPoint(x,y,canvasModel.getPixel(x,y));
    }

    /** Calls {@link CanvasModel#fillCanvas(Color)}
     *
     * @param color The color that fills the canvas.
     */
    public void fillCanvas(Color color) {
        canvasModel.fillCanvas(color);
    }

    /** Clears the canvas by calling {@link CanvasModel#resetCanvas()}. Also uses {@link CanvasController#copyModelToList()} to allow for {@link CanvasController#undo} to work.
     */
    public void clear() {
        while(!undoStack.empty()) {
            undoStack.pop();
        }
        canvasModel.resetCanvas();
    }

    /**
     * Calls {@link CanvasModel#toString()}
     * @return A string of the canvas.
     */
    public String canvasToString() {
        return canvasModel.toString();
    }

    /**
     * Redraws the {@link CanvasView} by copying the {@link CanvasModel}, incase of  {@link java.util.Observer} not working.
     */

    public void redrawCanvasView() {
        canvasView.redrawCanvasView();
    }

    /**
     * Pushes the {@link CanvasController#undoArrayList} to the {@link CanvasController#undoStack}, also clears the {@link CanvasController#undoArrayList}
     * Is called upon when user has finished drawing a line.
     */
    public void pushToUndoStack() {
        if(!undoArrayList.isEmpty()) {
            undoStack.push(new ArrayList<>(undoArrayList));
            undoArrayList.clear();
        }
    }

    /**
     * Redraws pixels that were painted over by popping the {@link CanvasController#undoStack} and painting them on the {@link CanvasModel}
     */
    public void undo() {
        if(!undoStack.empty()) {
            for (final ColorPoint cp : undoStack.pop()) {
                canvasModel.setPixel(cp.getX(),cp.getY(),cp.getC());
            }
        }
    }
    public boolean isUndoAvailable() {
        return !undoStack.isEmpty();
    }

    public void setToolColor(Color color) {
        tools.forEach((k,v) -> v.setColor(color));
    }

    public Color getToolColor() {
        return currentTool.getColor();
    }

    public void setToolRadius(int toolRadius) {
        tools.forEach((k,v) -> v.setRadius(toolRadius));
    }
}