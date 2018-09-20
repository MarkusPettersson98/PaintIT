package Tools;

import javafx.scene.paint.Color;

public interface Tool extends Observable {

    public void apply(int x, int y);

    public void setRadius(int radius);

    public void setColor(Color color);
}
