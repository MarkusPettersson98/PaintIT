package Model.Canvas;

import javafx.scene.paint.Color;

/**
 * {@link Tool} is implemented by classes who wants to apply a pattern onto the canvas.
 */

public interface Tool {

    int getRadius();

    void setRadius(int radius);

    /**
     * Determines the color of the Tool.
     */
    Color getColor();

    void setColor(Color color);


    /**  Returns whether or not the radius is less or equal to 0
     *
     * @param x0 The x-value for the center of the circle.
     * @param y0 The y-value for the center of the circle.
     * @param x The x-value to be checked if in circle.
     * @param y the y-value to be checked if in circle.
     * @return whether or not the radius is less or equal to 0
     */
    public boolean apply(int x0, int y0, int x, int y);
}
