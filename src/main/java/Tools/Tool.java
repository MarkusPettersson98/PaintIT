package Tools;

import Views.PaintingView;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;


public abstract class Tool {

    /**
     * Determines how big the circle that will be painted is.
     */
    @Getter @Setter
    private int radius;

    /**
     * Determines the color of the Tool.
     */
    @Getter @Setter
    private Color color;

    public Tool() {
        this.radius = 1;
        this.color = Color.BLACK;
    }

    public Tool(int radius) {
        this.radius = radius;
        this.color = Color.BLACK;
    }

    public Tool(Color color) {
        this.radius = 1;
        this.color = color;
    }

    public Tool(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }

    /**
     * Determines if point is in circle or not. Meaning, returns if the point (posx,posy) is in the circle that is formed around (x0,y0) with the radius r.
     *
     * @param x0   x-value of the origin of circle.
     * @param y0   y-value of the origin of circle.
     * @param posx x-value of the point that is being checked.
     * @param posy y-value of the point that is being checked.
     * @param r    radius of circle.
     * @return Whether or not x^2 + y^2 is less or equal to r^2.
     */

    public boolean inCircle(int x0, int y0, int posx, int posy, int r) {
        return ((Math.pow((posx - x0), 2) + Math.pow(posy - y0, 2)) <= Math.pow(r, 2));

    }


    //Notifies Observers ({@link Canvas.CanvasController}) of the brush by giving them x and y-values that form a circle around the point that is formed by the arguments.
    // The appearance of the circle is determined by {@link PaintingView} and {@link Tools.Tool#radius}
    // TODO: fix this javadoc or functionality of apply
    /** Doesn't really do anything.
     *
     *
     * @param x0 The x-value for the center of the circle.
     * @param y0 The y-value for the center of the circle.
     * @param x The x-value to be checked if in circle.
     * @param y the y-value to be checked if in circle.
     * @return whether or not the radius is less or equal to 0, uses {@link Tool#inCircle}
     */
    public boolean apply(int x0, int y0, int x, int y) {
        if (radius >= 0) {
            // Check square area around cursor position
            //for (int posx = (x0 - radius); posx <= (x0 + radius); posx++) {
                //for (int posy = (y0 - radius); posy <= (y0 + radius); posy++) {
                    if (inCircle(x0, y0, x, y, /*posx, posy,*/ radius)) {
                        // If inside circle with radius, return true
                        return true;
                    }
              //  }
           // }
        }
        // Pixel out of circle, return false
        return false;
    }

}
