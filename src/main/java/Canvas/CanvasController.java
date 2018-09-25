package Canvas;

import javafx.scene.paint.Color;
import lombok.Getter;

import java.util.ArrayList;

public class CanvasController {

    CanvasModel canvasModel;
    @Getter CanvasView canvasView;

    ArrayList<ColorPoint> tempArray = new ArrayList<>();
    ArrayList<ColorPoint> tempArray2 = new ArrayList<>();

    Color tempColor;

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
            tempColor = canvasModel.getPixel(x,y);

            tempArray.add(new ColorPoint(x,y, tempColor));

            canvasModel.setPixel(x, y, newColor);
        }
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
        tempArray2.clear();
        tempArray.clear();
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

    public void regret() {
        for (ColorPoint cp : tempArray) {
            tempArray2.add(cp);
        }
        for (ColorPoint cp2 : tempArray2) {
            paint(cp2.getX(),cp2.getY(), cp2.getC());
        }
    }
}