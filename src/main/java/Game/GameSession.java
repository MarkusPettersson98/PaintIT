package Game;


import Canvas.CanvasModel;
import Util.ViewFactory;
import Views.*;
import com.PaintIT.app.TopController;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.List;

/**
 * Main class, this is where most parts of the application is connected.
 */

public class GameSession {

    private Team team;

    private GameLogic gameLogic;

    private final TopController topController;

    public GameSession() {
        gameLogic = new GameLogic();
        List<GameScreen> gameScreens = ViewFactory.createAllViews(this);
        topController = new TopController(gameScreens);
    }

    public Pane getCurrentPane() { return topController.getCurrentView(); }

    /**
     * Method for invoking {@link GameScreen#init()} of view to be shown and switching current view
     * in {@link TopController}.
     * @param url
     */
    public void show(String url) {
        // Get next view to be shown
        topController.prepareNextView(url);
        // Call init method on next view
        GameScreen gameScreenTmp = topController.getNextScreen();
        gameScreenTmp.init();
        // Show next view
        topController.show();
    }

    /**
     * If there's no prior instance of {@link Team}, add passed reference to {@link GameSession#team}
     * @param team
     */

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
/*
    public void startWordRevealCountdown() {
        wordRevealView.setTimer(6);
        wordRevealView.startTimer();
        wordRevealView.setPlayerNameLabels(team.getPlayerNames());
    }
    */
}
