package Tools;

public class Brush implements Tool {


    public Brush() {

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
