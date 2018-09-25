package Util;

import MainMenu.GameSetupView;
import MainMenu.WordRevealView;
import com.PaintIT.app.PaintingView;

public abstract class ButtonFactory {

    public static String createPaintingViewBtnId() {
        return (PaintingView.class.getSimpleName());
    }

    public static String createGameSetupViewBtnId() {
        return (GameSetupView.class.getSimpleName());
    }

    public static String createWordRevealViewBtnId() {
        return (WordRevealView.class.getSimpleName());
    }
}
