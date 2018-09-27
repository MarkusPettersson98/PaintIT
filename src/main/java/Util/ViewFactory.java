package Util;

import Views.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public abstract class ViewFactory {

    private static FXMLLoader fxmlLoader = new FXMLLoader();

    public static List<Pair<String, Pane>> createAllViews() {
        List<Pair<String, Pane>> allViews = new ArrayList<>();

        allViews.add(new Pair<>(PaintingView.class.getSimpleName(), new PaintingView(fxmlLoader)));
        allViews.add(new Pair<>(MainMenuView.class.getSimpleName(), new MainMenuView(fxmlLoader)));
        allViews.add(new Pair<>(GameSetupView.class.getSimpleName(), new GameSetupView(fxmlLoader)));
        allViews.add(new Pair<>(WordRevealView.class.getSimpleName(), new WordRevealView(fxmlLoader)));
        allViews.add(new Pair<>(GuessingView.class.getSimpleName(), new GuessingView(fxmlLoader)));

        return allViews;
    }

    public static MainMenuView createMainMenuView() { return new MainMenuView(fxmlLoader); }
    public static GameSetupView createGameSetupView() { return new GameSetupView(fxmlLoader); }
    public static WordRevealView createWordRevealView() { return new WordRevealView(fxmlLoader); }
    public static PaintingView createPaintingView() { return new PaintingView(fxmlLoader); }
    public static GuessingView createGuessingView() { return new GuessingView(fxmlLoader); }

}
