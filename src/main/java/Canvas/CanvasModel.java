package Canvas;

import lombok.Getter;

public class CanvasModel {

    @Getter private int x = 5, y = 5;
    private boolean[][] canvas = new boolean[x][y];

    public CanvasModel(boolean initValue) {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                canvas[j][i] = initValue;
            }
        }
    }

    public void setPixel(int x, int y, boolean newValue) throws IndexOutOfBoundsException {
        // Check if x and y are in bounds of canvas
        if(!inBounds(x, y)) {}

        canvas[x][y] = newValue;
    }


    public boolean getPixel(int x, int y) throws IndexOutOfBoundsException {
        // Check if x and y are in bounds of canvas
        if(!inBounds(x, y)) {}

        return canvas[x][y];

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < y; i++) {
            sb.append("[ ");
            for (int j = 0; j < x; j++) {
                sb.append(canvas[j][i] + " ");
            }
            sb.append("] \n");
        }
        return sb.toString();
    }

    protected boolean inBounds(int x, int y) throws IndexOutOfBoundsException {
        if((x < 0 || x > this.x) || (y < 0 || y > this.y)) {
            // x or y out of bounds, throw exception
            throw new IndexOutOfBoundsException();
        }
        // Within bounds
        return true;
    }

}