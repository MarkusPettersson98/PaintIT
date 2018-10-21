package Model.Canvas;

import javafx.scene.paint.Color;

public interface ObservableCanvasModel {

    public int getLatestPixelX();

    public int getXMax();

    public int getLatestPixelY();

    public int getYMax();

    public Color getPixel(int x, int y);
}
