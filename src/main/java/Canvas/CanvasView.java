package Canvas;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import lombok.Getter;

public class CanvasView extends Canvas {

    private static final int width = 1200;
    private static final int height = 500;

    private PixelWriter pixelWriter;

    @Getter private final GraphicsContext graphicsContext;

    public CanvasView() {
        super(width, height);
        this.graphicsContext = this.getGraphicsContext2D();
        this.pixelWriter = graphicsContext.getPixelWriter();
        graphicsContext.getCanvas().toString();
    }

    public void setPixel(int x, int y, Color color) {
        pixelWriter.setColor(x,y,color);
    }

}
