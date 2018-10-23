package Model.Canvas;

public interface MeasuringTool {

    public boolean inArea(int x0, int y0, int posx, int posy);

    public int getRadius();

    public void setRadius(int radius);
}
