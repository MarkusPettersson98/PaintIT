package Tools;

import Views.PaintingView;
import javafx.scene.paint.Color;

/** Represents an Eraser.
 *
 */
public class Eraser extends Tool {

    public Eraser() {
        super(1, Color.WHITE);
    }
    public Eraser(int radius) {
        super(radius, Color.WHITE);
    }

    @Override
    public void setColor(Color color) {
        super.setColor(Color.WHITE);
    }
}
