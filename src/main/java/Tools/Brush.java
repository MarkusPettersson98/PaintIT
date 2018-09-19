package Tools;
import javafx.scene.paint.Color;
import lombok.Setter;

import java.math.*;

public class Brush implements Tool {

    @Setter private int radius;
    @Setter private Color color;

    static int counter = 0;
    static int total = 0;

    public Brush() {
        color = Color.BLACK;
    }

    public Brush(Color color) {
        this.color = color;
    }

    public Brush(Observer observer) {
        addObserver(observer);
        color = Color.BLACK;
    }

    public boolean inCircle(int x0, int y0, int posx, int posy, int r) {
        // System.out.println("x: " + Math.pow(x0-posx,2));
        // System.out.println("y: " + Math.pow(y0-posy,2));
        total++;
        if(Math.pow(x0-posx,2) + Math.pow(y0-posy,2) <= Math.pow(r,2)) counter++;
        return ((Math.pow((posx - x0), 2) + Math.pow(posy - y0, 2)) <= Math.pow(r,2));

    }



    public void apply(int x0, int y0) {
        if (radius >= 0) {
            // Check square area around cursor position
            for (int posx = (x0 - radius); posx <= (x0 + radius); posx++) {
                for (int posy = (y0 - radius); posy <= (y0 + radius); posy++) {
                    if (inCircle(x0, y0, posx, posy, radius)) {
                        // If inside circle with radius, notify observers
                        for (Observer observer : Observers) {
                            observer.update(posx, posy, color);
                        }
                    }
                }
            }
        }
        System.out.println(counter);
        System.out.println(total);
    }


    @Override
    public void addObserver(Observer observer) {
        Observers.add(observer);
    }

}
