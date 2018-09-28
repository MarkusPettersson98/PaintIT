package Game;


import Canvas.CanvasModel;
import Canvas.CanvasView;
import WordAndGuess.GuessLogic;
import javafx.scene.paint.Color;
import lombok.Getter;

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
    public GuessLogic getGuessLogic(){
        return gameLogic.getGuessLogic();
    }

    public CanvasModel getCanvas() { return gameLogic.getCurrentPainting(); }

    public void setCanvasModel(CanvasModel canvasModel) { gameLogic.setCurrentPainting(canvasModel);}
}
