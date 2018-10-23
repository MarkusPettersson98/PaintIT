package Model.Canvas;

import lombok.Getter;
import lombok.Setter;

/**
 * {@link Tool} for checking wheter a set pixel is withing the boundaries of a circle with set radius.
 */

public class CircleTool implements MeasuringTool {


    /**
     * Radius of circle.
     */
    @Setter
    @Getter
    private int radius;

    public CircleTool(int radius) {
        this.radius = radius;
    }


    /**
     * Determines if point is in circle or not. Meaning, returns if the point (posx,posy) is in the circle that is formed around (x0,y0) with the radius r.
     * Equation for a circle is derived from Pythagoras theorem (x^2 + y^2 <= r^2).
     *
     * @param x0   x-value of the origin of circle.
     * @param y0   y-value of the origin of circle.
     * @param posx x-value of the point that is being checked.
     * @param posy y-value of the point that is being checked.
     * @return Whether or not x^2 + y^2 is less or equal to r^2.
     */
    @Override
    public boolean inArea(int x0, int y0, int posx, int posy) {
        return (Math.pow(posx - x0, 2) + Math.pow(posy - y0, 2)) <= Math.pow(radius, 2);
    }
}
