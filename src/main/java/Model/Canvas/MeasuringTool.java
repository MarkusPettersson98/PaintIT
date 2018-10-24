package Model.Canvas;

/**
 * {@link MeasuringTool} is implemented by classes which check an area on the canvas and determines whether a set pixel is within
 * the boundaries of that area or not.
 */

public interface MeasuringTool {

    public boolean inArea(int x0, int y0, int posx, int posy);

    public int getRadius();

    public void setRadius(int radius);
}
