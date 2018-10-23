package Model.Canvas;

import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;


public interface Tool {

    /**
     * Determines how big the circle that will be painted is.
     */
    //@Getter @Setter
    //private int radius;
    int getRadius();

    void setRadius(int radius);

    /**
     * Determines the color of the Tool.
     */
    // @Getter @Setter
    Color getColor();

    void setColor(Color color);

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


    //Notifies Observers ({@link Controller.CanvasController}) of the brush by giving them x and y-values that form a circle around the point that is formed by the arguments.
    // The appearance of the circle is determined by {@link PaintingView} and {@link Tool#radius}
    // TODO: fix this javadoc or functionality of apply
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
