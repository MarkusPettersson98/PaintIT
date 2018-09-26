package Game;

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
}
