package Tools;

import lombok.Setter;

import java.util.Random;

/** Represents a SprayCan.
 *
 */
public class SprayCan extends Tool {

    Random r = new Random();
    /** Determines odds of pixel being colored.
     *
     */
    @Setter private double odds = 0.20;
    @Override
    public boolean apply(int x0, int y0, int x, int y) {
        return ((r.nextDouble() < odds) && super.apply(x0,y0,x,y));
    }
}