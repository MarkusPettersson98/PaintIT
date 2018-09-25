package com.PaintIT.app;

import MainMenu.GameSetupView;
import MainMenu.MainMenuView;
import MainMenu.WordRevealView;
import javafx.scene.layout.Pane;
import lombok.Getter;

import java.util.HashMap;

public class TopController {

    private static HashMap<String, Pane> applicationPanes = new HashMap<>();

    @Getter private static Pane currentView = new Pane();


    public TopController() {
        loadPane(PaintingView.class.getSimpleName(), new PaintingView());
        loadPane(MainMenuView.class.getSimpleName(), new MainMenuView());
        loadPane(GameSetupView.class.getSimpleName(), new GameSetupView());
        loadPane(WordRevealView.class.getSimpleName(), new WordRevealView());

        show(MainMenuView.class.getSimpleName());
    }

    private void loadPane(String paneName, Pane pane) {
        applicationPanes.put(paneName, pane);
    }

    public static void show(String url) {
        currentView.getChildren().clear();
        currentView.getChildren().add(applicationPanes.get(url));
    }


}
