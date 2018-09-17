package Tools;
import lombok.Setter;

import java.math.*;

public class Brush implements Tool {

    @Setter private int radius;
    @Setter private boolean color;
    public Brush() {
        color = true;
    }

    public Brush(Observer observer) {
        addObserver(observer);
        color = true;
    }

    public boolean inCircle(int x, int y, int posx, int posy, int r) {
        return (Math.pow((x-posx), 2) + Math.pow(y-posy, 2) <= Math.pow(r,2));
    }


    public void apply(int x, int y) {
        if (radius > 0) {
            // Check square area around cursor position
            for (int posy = Math.max(0, y - radius); posy <= y + radius; posy++) {
                for (int posx = Math.max(0, x - radius); posx <= x + radius; posx++) {
                    if (inCircle(x, y, posx, posy, radius)) {
                        // If inside circle with radius, notify observers
                        for (Observer observer : Observers) {
                            observer.update(posx, posy, color);
                            observer.update(posx,posy,color);
                        }
                    }
                }
            }
        }
    }


    @Override
    public void addObserver(Observer observer) {
        Observers.add(observer);
    }

}
