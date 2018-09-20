package Canvas;

import javafx.scene.paint.Color;
import lombok.Getter;

public class CanvasModel {


    @Getter private int xMax = 300, yMax = 300;
    private Color[][] canvas = new Color[xMax][yMax];

    protected CanvasModel(Color initValue) {
        fillCanvas(initValue);
    }

    protected CanvasModel(int xSize, int ySize, Color initColor) {
        this.xMax = xSize; this.yMax = ySize;
        this.canvas = new Color[xSize][ySize];
        fillCanvas(initColor);
    }

    /** Sets the color of a pixel.
     *
     * @param x x-coordinate of pixel.
     * @param y y-coordinate of pixel.
     * @param newValue New color of the pixel.
     * @throws IndexOutOfBoundsException Exception when you try to paint outside the canvas.
     */
    protected void setPixel(int x, int y, Color newValue) throws IndexOutOfBoundsException {
        // Check if xMax and yMax are in bounds of canvas
        if(!inBounds(x, y)) {
            throw new IndexOutOfBoundsException();
        }

        canvas[x][y] = newValue;
    }

    /** Returns the color of a pixel.
     * @param x x-coordinate of pixel.
     * @param y y-coordinate of pixel.
     * @return The color of the chosen pixel.
     * @throws IndexOutOfBoundsException Exception when you try to get a pixel outside the canvas.
     */

    protected Color getPixel(int x, int y) throws IndexOutOfBoundsException {
        // Check if xMax and yMax are in bounds of canvas
        if(!inBounds(x, y)) {}

        return canvas[x][y];

    }

    /**
     * Sets entire canvas to white
     */
    protected void resetCanvas() {
        fillCanvas(Color.WHITE);
    }

    /**
     * Sets entire canvas to chosen color.
     * @param color The color of which the canvas will be filled with.
     */

    public void fillCanvas(Color color) {
        for (int i = 0; i < yMax; i++) {
            for (int j = 0; j < xMax; j++) {
                canvas[j][i] = color;
            }
        }
    }


    /**
     * Creates and returns a string of the canvas, used exclusively for testing.
     * @return Returns a String of the Canvas.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < yMax; i++) {
            sb.append("[ ");
            for (int j = 0; j < xMax; j++) {
                Color temp = canvas[j][i];
                sb.append("[ " + temp.getRed() + ", " + temp.getGreen() + ", " + temp.getBlue() + " ] ");
            }
            sb.append("] \n");
        }
        return sb.toString();
    }

    /** Checks if x and y value is within CanvasModels bounds.
     *
     * @param x x-value to check.
     * @param y y-value to check.
     * @return Whether or not the values is within CanvasModel bounds.
     * @throws IndexOutOfBoundsException Throws exception if out of bounds.
     */
    protected boolean inBounds(int x, int y) throws IndexOutOfBoundsException {
        if((x < 0 || x > this.xMax) || (y < 0 || y > this.yMax)) {
            // xMax or yMax out of bounds, throw exception
            throw new IndexOutOfBoundsException();
        }
        // Within bounds
        return true;
    }
    public int getXBound() {
        return xMax-1;
    }

    public int getYBound() {
        return yMax-1;
    }
}
