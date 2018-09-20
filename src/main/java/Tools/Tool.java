package Tools;

public interface Tool extends Observable {

    public void apply(int x, int y);

    public void setRadius(int radius);
}
