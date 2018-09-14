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
