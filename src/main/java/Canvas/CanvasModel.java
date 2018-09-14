package Canvas;

public class CanvasModel {

    private int x = 5, y = 5;
    private boolean[][] canvas = new boolean[x][y];

    public CanvasModel() {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                canvas[j][i] = false;
            }
        }
    }

    public void setPixel(int x, int y, boolean newValue) throws IndexOutOfBoundsException {
        // Check if x and y are in bounds of canvas
        if((x < 0 || x > this.x) || (y < 0 || y > this.y)) {
            // x or y out of bounds, return
            throw new IndexOutOfBoundsException();
        }
        // Update 'pixel' in canvas
        canvas[x][y] = newValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < y; i++) {
            sb.append("[");
            for (int j = 0; j < x; j++) {
                sb.append(canvas[j][i] + ", ");
            }
            sb.append("] \n");
        }
        return sb.toString();
    }

}
