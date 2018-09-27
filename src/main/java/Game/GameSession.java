package Game;


import Canvas.CanvasModel;
import Canvas.CanvasView;
import javafx.scene.paint.Color;

public class GameSession {

    private static GameSession instance;

    private Team team;

    private GameLogic gameLogic;

    private GameSession() { this.gameLogic = new GameLogic(); }

    public static GameSession getInstance() {
        if(instance == null) { instance = new GameSession(); }
        return instance;
    }

    public void addTeam(Team team) {
        if(this.team == null) { this.team = team; }
    }

    public String getTeamName() throws AssertionError {
        if(team != null)
            return team.getTeamName();
        else return "There's no team!";
    }

    public CanvasModel getCanvas() { return gameLogic.getCurrentPainting(); }

    public void setCanvasModel(CanvasModel canvasModel) { gameLogic.setCurrentPainting(canvasModel);}
}
