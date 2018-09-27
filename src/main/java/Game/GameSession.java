package Game;


import Canvas.CanvasModel;
import Canvas.CanvasView;
import Util.ViewFactory;
import Views.*;
import com.PaintIT.app.TopController;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import javax.swing.text.View;
import java.util.ArrayList;
import java.util.List;

public class GameSession {

    private Team team;

    private GameLogic gameLogic;

    MainMenuView mainMenuView;
    GameSetupView gameSetupView;
    WordRevealView wordRevealView;
    PaintingView paintingView;
    GuessingView guessingView;

    private final TopController topController;

    public GameSession() {
        gameLogic = new GameLogic();
        List<Pane> panes = new ArrayList<>();
        // Create an object of every view in the application
        mainMenuView = ViewFactory.createMainMenuView(this); panes.add(mainMenuView);
        gameSetupView = ViewFactory.createGameSetupView(this); panes.add(gameSetupView);
        wordRevealView = ViewFactory.createWordRevealView(this); panes.add(wordRevealView);
        paintingView = ViewFactory.createPaintingView(this); panes.add(paintingView);
        guessingView = ViewFactory.createGuessingView(this); panes.add(guessingView);

        topController = new TopController(panes);

    }

    public Pane getCurrentPane() { return topController.getCurrentView(); }

    public void show(String url) { topController.show(url); }

    public void addTeam(Team team) {
        if(this.team == null) { this.team = team; }
    }

    public String getTeamName() throws AssertionError {
        if(team != null)
            return team.getTeamName();
        else return "There's no team!";
    }

    public Pair<String, String> getPlayerNames() {
        return team.getPlayerNames();
    }

    public CanvasModel getCanvas() { return gameLogic.getCurrentPainting(); }

    public void setCanvasModel(CanvasModel canvasModel) { gameLogic.setCurrentPainting(canvasModel);}

    public void startWordRevealCountdown() {
        wordRevealView.setTimer(6);
        wordRevealView.startTimer();
        wordRevealView.setPlayerNameLabels(team.getPlayerNames());
    }
}
