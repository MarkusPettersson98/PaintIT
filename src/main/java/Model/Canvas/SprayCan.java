package Model.Canvas;

import javafx.scene.paint.Color;
import lombok.Setter;

import java.util.Random;

/** Represents a SprayCan.
 *
 */
public class SprayCan implements Tool {

    Random r = new Random();
    /** Determines odds of pixel being colored.
     *
     */
    @Setter private double odds = 0.20;

    private MeasuringTool innerTool;
    private Color color;

    public SprayCan() {
        this.innerTool = new CircleTool(1);
    }

    public SprayCan(int radius) {
        this.innerTool = new CircleTool(radius);
    }

    public SprayCan(int radius, Color color) {
        this.innerTool = new CircleTool(radius);
        this.color = color;
    }

    @Override
    public boolean apply(int x0, int y0, int x, int y) {
        return (r.nextDouble() < odds && innerTool.inArea(x0, y0, x, y));
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