package Canvas;

import Tools.Observer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import lombok.Getter;

public class CanvasView extends Canvas implements Observer {

    private static final int width = 1200;
    private static final int height = 500;

    private PixelWriter pixelWriter;
    private CanvasModel canvasModel;

    @Getter private final GraphicsContext graphicsContext;

    public CanvasView(CanvasModel canvasModel) {
        super(width, height);
        this.graphicsContext = this.getGraphicsContext2D();
        this.pixelWriter = graphicsContext.getPixelWriter();
        this.canvasModel = canvasModel;
        graphicsContext.getCanvas().toString();
    }

    public void setPixel(int x, int y, Color color) {
        pixelWriter.setColor(x,y,color);
    }

    @Override
    public void update() {
        int x = canvasModel.getLatestPixelX();
        int y = canvasModel.getLatestPixelY();
        setPixel(x, y, canvasModel.getPixel(x, y));
    }
}
