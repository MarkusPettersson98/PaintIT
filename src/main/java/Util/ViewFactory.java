package Util;

import Views.*;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public abstract class ViewFactory {

    public static List<Pair<String, Pane>> createAllViews() {
        List<Pair<String, Pane>> allViews = new ArrayList<>();
        allViews.add(new Pair<>(PaintingView.class.getSimpleName(), new PaintingView()));
        allViews.add(new Pair<>(MainMenuView.class.getSimpleName(), new MainMenuView()));
        allViews.add(new Pair<>(GameSetupView.class.getSimpleName(), new GameSetupView()));
        allViews.add(new Pair<>(WordRevealView.class.getSimpleName(), new WordRevealView()));

        return allViews;
    }

}
