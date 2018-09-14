package Canvas;

import lombok.Getter;

public class CanvasModel {


    @Getter private int xMax = 5, yMax = 5;
    private boolean[][] canvas = new boolean[xMax][yMax];

    protected CanvasModel(boolean initValue) {
        fillCanvas(initValue);
    }

    protected void setPixel(int x, int y, boolean newValue) throws IndexOutOfBoundsException {
        // Check if xMax and yMax are in bounds of canvas
        if(!inBounds(x, y)) {}

        canvas[x][y] = newValue;
    }


    protected boolean getPixel(int x, int y) throws IndexOutOfBoundsException {
        // Check if xMax and yMax are in bounds of canvas
        if(!inBounds(x, y)) {}

        return canvas[x][y];

    }

    protected void resetCanvas() {
        fillCanvas(false);
    }

    private void fillCanvas(boolean color) {
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
                sb.append(canvas[j][i] + " ");
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
