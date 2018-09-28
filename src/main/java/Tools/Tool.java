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
     * Determines if point is in circle or not.
     *
     * @param x0   x-value of the origin of circle.
     * @param y0   y-value of the origin of circle.
     * @param posx x-value of the point that is being checked.
     * @param posy y-value of the point that is being checked.
     * @param r    radius of circle.
     * @return Whether or not the point was in the circle or not.
     */

    public boolean inCircle(int x0, int y0, int posx, int posy, int r) {
        return ((Math.pow((posx - x0), 2) + Math.pow(posy - y0, 2)) <= Math.pow(r, 2));
    }


    /**
     * Checks square area around brush and fills a circular area.
     * Notifies Observers ({@link Canvas.CanvasController}) of the brush by giving them x and y-values that form a circle around the point that is formed by the arguments.
     * The appearance of the circle is determined by {@link Tool#color} and {@link Tool#radius}
     *
     * @param x0 Determines the x-value for the center of the circle.
     * @param y0 Determines the x-value for the center of the circle.
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
