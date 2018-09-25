package Tools;

import javafx.scene.paint.Color;

public class Eraser extends Tool {

    public Eraser() {
        super(1, Color.WHITE);
    }

    public Eraser(int radius) {
        super(radius, Color.WHITE);
    }

    @Override
    public void setColor(Color color) {
        super.setColor(Color.WHITE);
    }

    /** Determines how big the circle that will be erased is.
     *
     */

    /** Checks square area around brush and fills a circular area.
     * Notifies Observers ({@link Canvas.CanvasController}) of the brush by giving them x and y-values that form a circle around the point that is formed by the arguments.
     * The appearance of the circle is determined by {@link com.PaintIT.app.PaintingView#currentColor} and {@link Brush#radius}
     *
     * @param x0 Determines the x-value for the center of the circle.
     * @param y0 Determines the x-value for the center of the circle.
     */


}
