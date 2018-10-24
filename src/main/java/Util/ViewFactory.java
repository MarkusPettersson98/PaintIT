package Util;

import Controller.TopController;
import Views.*;
import javafx.fxml.FXMLLoader;
import java.util.ArrayList;
import java.util.List;

public abstract class ViewFactory {

    private static FXMLLoader fxmlLoader = new FXMLLoader();

    /** Creates an instance of {@link MainMenuView} and injects a reference to a {@link TopController}.
     *
     * @param topController
     * @return
     */

    public static MainMenuView createMainMenuView(TopController topController) { return new MainMenuView(fxmlLoader, topController); }

    /** Creates an instance of {@link GameSetupView} and injects a reference to a {@link TopController}.
     *
     * @param topController
     * @return
     */
    public static GameSetupView createGameSetupView(TopController topController) { return new GameSetupView(fxmlLoader, topController); }

    /** Creates an instance of {@link WordRevealView} and injects a reference to a {@link TopController}.
     *
     * @param topController
     * @return
     */
    public static WordRevealView createWordRevealView(TopController topController) { return new WordRevealView(fxmlLoader, topController); }

    /** Creates an instance of {@link PaintingView} and injects a reference to a {@link TopController}.
     *
     * @param topController
     * @return
     */
    public static PaintingView createPaintingView(TopController topController) { return new PaintingView(fxmlLoader, topController); }

    /** Creates an instance of {@link GuessingView} and injects a reference to a {@link TopController}.
     *
     * @param topController
     * @return
     */
    public static GuessingView createGuessingView(TopController topController) { return new GuessingView(fxmlLoader, topController); }

    /** Creates an instance of {@link DoneView} and injects a reference to a {@link TopController}.
     *
     * @param topController
     * @return
     */
    public static DoneView createDoneView(TopController topController) { return new DoneView(fxmlLoader, topController); }

    /** Creates an instance of {@link ChooseWordView} and injects a reference to a {@link TopController}.
     *
     * @param topController
     * @return
     */
    public static ChooseWordView createChooseWordView(TopController topController) { return new ChooseWordView(fxmlLoader, topController); }

    /** Creates an instance of every GameScreen and injects a reference to a {@link TopController} into them.
     *
     * @param topController
     * @return
     */
    public static GuessCountdownView createGuessCountdownView(TopController topController) { return new GuessCountdownView(fxmlLoader, topController); }

    public static List<GameScreen> createAllViews(TopController topController) {
        final List<GameScreen> gameScreens = new ArrayList<>();

        gameScreens.add(createMainMenuView(topController));
        gameScreens.add(createGameSetupView(topController));
        gameScreens.add(createWordRevealView(topController));
        gameScreens.add(createPaintingView(topController));
        gameScreens.add(createGuessingView(topController));
        gameScreens.add(createDoneView(topController));
        gameScreens.add(createChooseWordView(topController));
        gameScreens.add(createGuessCountdownView(topController));

        return gameScreens;
    }
}
