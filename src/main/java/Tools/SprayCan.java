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
    @Setter
    private int radius;

    @Override
    public void apply(int x0, int y0, Color color) {
        if (radius >= 0) {
            // Check square area around cursor position
            for (int posx = (x0 - radius); posx <= (x0 + radius); posx++) {
                for (int posy = (y0 - radius); posy <= (y0 + radius); posy++) {
                    if (r.nextDouble() < odds) {
                        if (super.inCircle(x0, y0, posx, posy, radius)) {
                            // If inside circle with radius, notify observers
                            for (Observer observer : Observers) {
                                observer.update(posx, posy, color);
                            }
                        }
                    }
                }
            }
        }
    }
}

