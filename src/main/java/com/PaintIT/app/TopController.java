package com.PaintIT.app;


import Util.ViewFactory;


import Views.GameScreen;
import javafx.scene.layout.Pane;
import javafx.util.Pair;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

public class TopController {

    private static HashMap<String, GameScreen> applicationPanes = new HashMap<>();

    @Getter private Pane currentView = new Pane();

    @Getter private GameScreen nextScreen;

    public TopController(List<GameScreen> gameScreens) {
        for(GameScreen gameScreen : gameScreens) {

            System.out.println(gameScreen.getClass().getSimpleName());

            applicationPanes.put(gameScreen.getClass().getSimpleName(), gameScreen);
        }
    }

    public void show() {
        currentView.getChildren().clear();
        currentView.getChildren().add(nextScreen.getPane());
    }

    public void prepareNextView(String url) {
        nextScreen = applicationPanes.get(url);
    }
}
