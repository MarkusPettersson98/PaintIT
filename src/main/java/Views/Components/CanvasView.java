package Views.Components;

import Model.CanvasModel;
import Util.Observer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import lombok.Getter;

public class CanvasView extends Canvas implements Observer {

    private static final int width = 1200;
    private static final int height = 500;

    /**
     * Is used to paint on {@link Canvas}, see Oracle.
     */
    private final PixelWriter pixelWriter;

    /**
     * How the view gets the pixel that has been changed
     */
    private CanvasModel canvasModel;

    @Getter private final GraphicsContext graphicsContext;

    public CanvasView(CanvasModel canvasModel) {
        super(width, height);
        this.graphicsContext = this.getGraphicsContext2D();
        this.pixelWriter = graphicsContext.getPixelWriter();
        this.canvasModel = canvasModel;
        redrawCanvasView();
        canvasModel.addObserver(this);

        graphicsContext.getCanvas().toString();
    }

    public void redrawCanvasView() {
        for(int y = 0; y < canvasModel.getYMax(); y++) {
            for (int x = 0; x < canvasModel.getXMax(); x++) {
                setPixel(x, y, canvasModel.getPixel(x,y));
            }
        }
    }

    /** Sets the color on itself.
     *
     * @param x The x value that has been updated.
     * @param y The y value that has been updated.
     * @param color The color of the pixel that has been updated.
     */
    public void setPixel(int x, int y, Color color) {
        pixelWriter.setColor(x,y,color);
    }

    /**
     * Gets called upon by {@link CanvasModel#notifyObservers()} and calls upon {@link CanvasView#setPixel(int, int, Color)} according to {@link CanvasModel}
     */
    @Override
    public void update() {
        final int x = canvasModel.getLatestPixelX();
        final int y = canvasModel.getLatestPixelY();
        setPixel(x, y, canvasModel.getPixel(x, y));
    }
}
