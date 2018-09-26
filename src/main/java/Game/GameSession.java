package Game;

public class GameSession {

    private static GameSession instance;

    private Team team;

    private GameSession() {}

    public static GameSession getInstance() {
        if(instance == null) { instance = new GameSession(); }
        return instance;
    }

    public void addTeam(Team team) {
        if(this.team == null) { this.team = team; }
    }
}
