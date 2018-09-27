package Util;

import Views.GameSetupView;
import Views.GuessingView;
import Views.WordRevealView;
import Views.PaintingView;

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

    public static String createWordRevealViewBtnId() {
        return (WordRevealView.class.getSimpleName());
    }
}
