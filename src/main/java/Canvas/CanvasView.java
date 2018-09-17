package Canvas;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import lombok.Getter;

public class CanvasView extends Canvas {

    private static final int width = 300;
    private static final int height = 300;

    private PixelWriter pixelWriter;

    @Getter private final GraphicsContext graphicsContext;

    public CanvasView() {
        super(width, height);
        this.graphicsContext = this.getGraphicsContext2D();
        this.pixelWriter = graphicsContext.getPixelWriter();
    }

    public void setPixel(int x, int y) {
        //this.getGraphicsContext2D().fillOval(x,y,1,1);
        pixelWriter.setColor(x,y,Color.CYAN);
    }

}
