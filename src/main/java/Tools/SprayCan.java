package Tools;

import javafx.scene.paint.Color;
import lombok.Setter;

import java.util.Random;

public class SprayCan extends ToolAbstract implements Tool  {

    Random r = new Random();
    /** Determines odds of pixel being colored.
     *
     */
    @Setter private double odds = 0.20;

    /** Determines how big the circle that will be painted is.
     *
     */

    @Override
    public boolean apply(int x0, int y0, int x, int y) {
        if (getRadius() >= 0) {
            if (r.nextDouble() < odds) {
                if (super.inCircle(x0, y0, x, y, getRadius())) {
                    // If inside circle with radius, notify observers
                    return true;
                }
            }
        }
        return false;
    }
}

