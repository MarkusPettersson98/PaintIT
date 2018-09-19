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

    protected void setPixel(int x, int y, Color newValue) throws IndexOutOfBoundsException {
        // Check if xMax and yMax are in bounds of canvas
        if(!inBounds(x, y)) {
            throw new IndexOutOfBoundsException();
        }

        canvas[x][y] = newValue;
    }


    protected Color getPixel(int x, int y) throws IndexOutOfBoundsException {
        // Check if xMax and yMax are in bounds of canvas
        if(!inBounds(x, y)) {}

        return canvas[x][y];

    }

    protected void resetCanvas() {
        fillCanvas(Color.WHITE);
    }

    public void fillCanvas(Color color) {
        for (int i = 0; i < yMax; i++) {
            for (int j = 0; j < xMax; j++) {
                canvas[j][i] = color;
            }
        }
    }


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
