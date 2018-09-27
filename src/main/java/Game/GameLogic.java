package Game;

import Canvas.CanvasModel;
import lombok.Getter;
import lombok.Setter;

class GameLogic {

    @Setter @Getter private CanvasModel currentPainting;

    private String word;

    private char[] guess;

}
