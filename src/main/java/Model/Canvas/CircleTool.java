package Model.Canvas;

import lombok.Getter;
import lombok.Setter;

public class CircleTool implements MeasuringTool {

    @Setter
    @Getter
    private int radius;

    public CircleTool(int radius) {
        this.radius = radius;
    }

    @Override
    public boolean inArea(int x0, int y0, int posx, int posy) {
        return (Math.pow(posx - x0, 2) + Math.pow(posy - y0, 2)) <= Math.pow(radius, 2);
    }
}
