package Tools;
import java.math.*;

public class Brush implements Tool {

    int radius;
    public Brush() {

    }

    public boolean inCircle(int x, int y, int posx, int posy, int r) {
        return (Math.pow((x-posx), 2) + Math.pow(y-posy, 2) <= Math.pow(r,2));
    }


    public void apply(int x, int y) {
        for(Observer observer : Observers)
            observer.update(x, y, true);
    }


    @Override
    public void addObserver(Observer observer) {
        Observers.add(observer);
    }

}
