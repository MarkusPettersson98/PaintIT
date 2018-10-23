package Model.Canvas;

import javafx.scene.paint.Color;

/** Represents an Eraser.
 *
 */
public class Eraser implements Tool {

    private MeasuringTool innerTool;
    final private Color color = Color.WHITE;

    public Eraser() {
        this.innerTool = new CircleTool(1);
    }
    public Eraser(int radius) {
        this.innerTool = new CircleTool(radius);
    }

    @Override
    public boolean apply(int x0, int y0, int x, int y) {
        return innerTool.inArea(x0, y0, x, y);
    }

    @Override
    public int getRadius() {
        return innerTool.getRadius();
    }

    @Override
    public void setRadius(int radius) {
        this.innerTool.setRadius(radius);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {

    }
}
