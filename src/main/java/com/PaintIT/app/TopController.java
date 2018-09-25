package com.PaintIT.app;

import MainMenu.GameSetupView;
import MainMenu.MainMenuView;
import MainMenu.WordRevealView;
import javafx.scene.layout.Pane;
import lombok.Getter;

public class TopController {

    private PaintingView paintingView;
    private MainMenuView mainMenuView;
    private GameSetupView gameSetupView;
    private WordRevealView wordRevealView;

    @Getter private Pane currentView;

    public TopController() {
        paintingView = new PaintingView();
        mainMenuView = new MainMenuView();
        gameSetupView = new GameSetupView();
        wordRevealView = new WordRevealView();

        currentView = wordRevealView;
    }





}
