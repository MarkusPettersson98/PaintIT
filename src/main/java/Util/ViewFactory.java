package Util;

import Model.Game.GameSession;
import Views.*;
import javafx.fxml.FXMLLoader;
import java.util.ArrayList;
import java.util.List;

public abstract class ViewFactory {

    private static FXMLLoader fxmlLoader = new FXMLLoader();

    /** Creates an instance of {@link MainMenuView} and injects a reference to a {@link GameSession}.
     *
     * @param gameSession
     * @return
     */

    public static MainMenuView createMainMenuView(GameSession gameSession) { return new MainMenuView(fxmlLoader, gameSession); }

    /** Creates an instance of {@link GameSetupView} and injects a reference to a {@link GameSession}.
     *
     * @param gameSession
     * @return
     */
    public static GameSetupView createGameSetupView(GameSession gameSession) { return new GameSetupView(fxmlLoader, gameSession); }

    /** Creates an instance of {@link WordRevealView} and injects a reference to a {@link GameSession}.
     *
     * @param gameSession
     * @return
     */
    public static WordRevealView createWordRevealView(GameSession gameSession) { return new WordRevealView(fxmlLoader, gameSession); }

    /** Creates an instance of {@link PaintingView} and injects a reference to a {@link GameSession}.
     *
     * @param gameSession
     * @return
     */
    public static PaintingView createPaintingView(GameSession gameSession) { return new PaintingView(fxmlLoader, gameSession); }

    /** Creates an instance of {@link GuessingView} and injects a reference to a {@link GameSession}.
     *
     * @param gameSession
     * @return
     */
    public static GuessingView createGuessingView(GameSession gameSession) { return new GuessingView(fxmlLoader, gameSession); }

    /** Creates an instance of {@link DoneView} and injects a reference to a {@link GameSession}.
     *
     * @param gameSession
     * @return
     */
    public static DoneView createDoneView(GameSession gameSession) { return new DoneView(fxmlLoader, gameSession); }

    /** Creates an instance of {@link ChooseWordView} and injects a reference to a {@link GameSession}.
     *
     * @param gameSession
     * @return
     */
    public static ChooseWordView createChooseWordView(GameSession gameSession) { return new ChooseWordView(fxmlLoader, gameSession); }

    /** Creates an instance of every GameScreen and injects a reference to a {@link GameSession} into them.
     *
     * @param gameSession
     * @return
     */
    public static List<GameScreen> createAllViews(GameSession gameSession) {
        List<GameScreen> gameScreens = new ArrayList<>();

        gameScreens.add(createMainMenuView(gameSession));
        gameScreens.add(createGameSetupView(gameSession));
        gameScreens.add(createWordRevealView(gameSession));
        gameScreens.add(createPaintingView(gameSession));
        gameScreens.add(createGuessingView(gameSession));
        gameScreens.add(createDoneView(gameSession));
        gameScreens.add(createChooseWordView(gameSession));

        return gameScreens;
    }
}
