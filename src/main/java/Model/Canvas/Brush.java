package Model.Canvas;

import javafx.scene.paint.Color;

/** Represents a brush.
 *
 */
public class Brush implements Tool {

    private MeasuringTool innerTool;

    private Color color;

    public Brush() {
        this.innerTool = new CircleTool(1);
    }

    public Brush(int radius) {
        this.innerTool = new CircleTool(radius);
    }

    public Brush(int radius, Color color) {
        this.innerTool = new CircleTool(radius);
        this.color = color;
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
        this.color = color;
    }
}
