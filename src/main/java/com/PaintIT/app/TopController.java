package com.PaintIT.app;

import MainMenu.GameSetupView;
import MainMenu.MainMenuView;
import MainMenu.WordRevealView;
import ViewObjects.TileBoardView;
import WordAndGuess.GuessLogic;
import javafx.scene.layout.Pane;
import lombok.Getter;

public class TopController {

    private PaintingView paintingView;
    private MainMenuView mainMenuView;
    private GameSetupView gameSetupView;
    private WordRevealView wordRevealView;
    private TileBoardView tileBoardView; //Remove this once we´ve implemented GuessingView

    @Getter private Pane currentView;

    public TopController() {
        paintingView = new PaintingView();
        mainMenuView = new MainMenuView();
        gameSetupView = new GameSetupView();
        wordRevealView = new WordRevealView();
//TODO FIX SO THAT TILEBOARD HAS A GUESSLOGIC
        tileBoardView = new TileBoardView(new GuessLogic());
        currentView = tileBoardView;
    }





}
