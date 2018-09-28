package Util;

import Game.GameSession;
import Views.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public abstract class ViewFactory {

    private static FXMLLoader fxmlLoader = new FXMLLoader();
/*
    public static List<Pair<String, Pane>> createAllViews() {
        List<Pair<String, Pane>> allViews = new ArrayList<>();

        allViews.add(new Pair<>(PaintingView.class.getSimpleName(), new PaintingView(fxmlLoader)));
        allViews.add(new Pair<>(MainMenuView.class.getSimpleName(), new MainMenuView(fxmlLoader)));
        allViews.add(new Pair<>(GameSetupView.class.getSimpleName(), new GameSetupView(fxmlLoader)));
        allViews.add(new Pair<>(WordRevealView.class.getSimpleName(), new WordRevealView(fxmlLoader)));
        allViews.add(new Pair<>(GuessingView.class.getSimpleName(), new GuessingView(fxmlLoader)));

        return allViews;
    }
*/
    public static MainMenuView createMainMenuView(GameSession gameSession) { return new MainMenuView(fxmlLoader, gameSession); }
    public static GameSetupView createGameSetupView(GameSession gameSession) { return new GameSetupView(fxmlLoader, gameSession); }
    public static WordRevealView createWordRevealView(GameSession gameSession) { return new WordRevealView(fxmlLoader, gameSession); }
    public static PaintingView createPaintingView(GameSession gameSession) { return new PaintingView(fxmlLoader, gameSession); }
    public static GuessingView createGuessingView(GameSession gameSession) { return new GuessingView(fxmlLoader, gameSession); }

    public static List<GameScreen> createAllView(GameSession gameSession) {
        List<GameScreen> gameScreens = new ArrayList<>();

        gameScreens.add(createMainMenuView(gameSession));
        gameScreens.add(createGameSetupView(gameSession));
        gameScreens.add(createWordRevealView(gameSession));
        gameScreens.add(createPaintingView(gameSession));
        gameScreens.add(createGuessingView(gameSession));

        return gameScreens;
    }
}
