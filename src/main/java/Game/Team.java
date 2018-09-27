package Game;

import lombok.Getter;

public class Team {

    private Player playerOne;
    private Player playerTwo;
    @Getter private final String teamName;
    @Getter private int streak;

    public Team(Player playerOne, Player playerTwo, String teamName) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.teamName = teamName;
    }

    public Team(String playerOne, String playerTwo, String teamName) {
        this.playerOne = new Player(playerOne);
        this.playerTwo = new Player(playerTwo);
        this.teamName = teamName;
    }


}
