package Util;

import Views.GameSetupView;
import Views.GuessingView;
import Views.MainMenuView;
import Views.WordRevealView;
import Views.PaintingView;

/**
 * Mimicing the FactoryPattern this class assigns buttons an ID which is relevant for identifying and switching between {@link Views.GameScreen} via {@link Game.GameSession}.
 */
public abstract class ButtonFactory {

    public static String createPaintingViewBtnId() {
        return (PaintingView.class.getSimpleName());
    }

    public static String createGameSetupViewBtnId() {
        return (GameSetupView.class.getSimpleName());
    }

    public static String createGuessingViewBtnId() {
        return (GuessingView.class.getSimpleName());
    }

    public static String createMainMenuViewBtnId() { return (MainMenuView.class.getSimpleName()); }

    public static String createWordRevealViewBtnId() {
        return (WordRevealView.class.getSimpleName());
    }
}
