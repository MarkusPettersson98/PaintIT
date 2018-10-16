package Canvas;

import javafx.scene.paint.Color;
import lombok.Getter;

/**
 * Represents a Color on a single pixel in {@link CanvasView}
 */

public class ColorPoint {
    @Getter private int x, y;
    @Getter
    private Color c;

    public ColorPoint(int x, int y, Color c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }
}
