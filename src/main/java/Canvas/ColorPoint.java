package Canvas;

import javafx.scene.paint.Color;
import lombok.Getter;


public class ColorPoint {
    @Getter int x, y;
    @Getter
    Color c;

    public ColorPoint(int x, int y, Color c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }
}
