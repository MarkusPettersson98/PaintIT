package Views;

import Controller.GameSession;
import javafx.scene.layout.Pane;

/**
 * Abstraction of 'main views' in the game, e.g. views that are part of the game's flow.
 */

public interface GameScreen {

    /**
     * Every {@link GameScreen} has a start-up routine that will be invoked whenever it's shown.
     * The {@link GameScreen#init()} method should only invoke class specific methods, which in term
     * can rely upon data from {@link GameSession}.
     */
    public void init();

    /**
     * Every class implementing {@link GameScreen} should be able to return a Pane, e.g. a reference
     * to the implementing class itself.
     * @return
     */
    public Pane getPane();

}
