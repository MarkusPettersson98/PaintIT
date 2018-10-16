package Util;

import Model.Game.GameSession;
import Views.*;

/**
 * Mimicing the FactoryPattern this class assigns buttons an ID which is relevant for identifying and switching between {@link Views.GameScreen} via {@link GameSession}.
 */
public abstract class ButtonFactory {

    public static String createPaintingViewBtnId() {
        return PaintingView.class.getSimpleName();
    }

    public static String createGameSetupViewBtnId() {
        return GameSetupView.class.getSimpleName();
    }

    public static String createGuessingViewBtnId() {
        return GuessingView.class.getSimpleName();
    }

    public static String createMainMenuViewBtnId() { return MainMenuView.class.getSimpleName(); }

    public static String createWordRevealViewBtnId() {
        return WordRevealView.class.getSimpleName();
    }

    public static String createDoneViewBtnId() { return DoneView.class.getSimpleName(); }

    public static String createChooseWordViewBtnId() { return ChooseWordView.class.getSimpleName(); }
}
