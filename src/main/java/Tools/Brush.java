package Tools;
import javafx.scene.paint.Color;
import lombok.Setter;

import java.math.*;

/** Represents a brush.
 *
 */
public class Brush extends ToolAbstract implements Tool {

    /** Determines how big the circle that will be painted is.
     *
     */
    // @Setter private int radius;

    @Override
    public boolean apply(int x0, int y0, int x, int y) {
        return super.apply(x0, y0, x, y);
    }

    /** Checks square area around brush and fills a circular area.
     * Notifies Observers ({@link Canvas.CanvasController}) of the brush by giving them x and y-values that form a circle around the point that is formed by the arguments.
     * The appearance of the circle is determined by {@link com.PaintIT.app.PaintingView#currentColor} and {@link Brush#radius}
     *
     * @param x0 Determines the x-value for the center of the circle.
     * @param y0 Determines the x-value for the center of the circle.
     */

}
