package Controller;

import Views.GameScreen;
import com.PaintIT.app.App;
import javafx.scene.layout.Pane;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewController {

    /**
     * Class responsible for viewing a pane. A reference of {@link ViewController#currentView} is passed
     * to {@link javafx.scene.Scene} in {@link App}.
     */

    private static Map<String, GameScreen> applicationPanes = new HashMap<>();

    @Getter private Pane currentView = new Pane();

    @Getter private GameScreen nextScreen;

    public ViewController(List<GameScreen> gameScreens) {
        for(final GameScreen gameScreen : gameScreens) {
            applicationPanes.put(gameScreen.getClass().getSimpleName(), gameScreen);
        }
    }

    /**
     * Adds {@link ViewController#nextScreen} as child node of {@link ViewController#currentView}. The child
     * of {@link ViewController#currentView} is the one being displayed in the main application.
     */
    public void show() {
        currentView.getChildren().clear();
        currentView.getChildren().add(nextScreen.getPane());
    }

    /**
     * Sets {@link ViewController#nextScreen} to a new {@link Pane}.
     * @param url
     */
    public void prepareNextView(String url) {
        nextScreen = applicationPanes.get(url);
    }
}
