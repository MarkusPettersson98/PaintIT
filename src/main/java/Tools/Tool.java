package Tools;

import javafx.scene.paint.Color;

public interface Tool extends Observable {

    public void apply(int x, int y, Color color);

    public void setRadius(int radius);
}
