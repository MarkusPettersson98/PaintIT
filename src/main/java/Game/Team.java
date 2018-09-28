package Game;

import javafx.util.Pair;
import lombok.Getter;

/**
 * Represents a team consisting of two {@link Player}s.
 */

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

    /** Returns the teams players.
     *
     * @return returns a {@link Pair} of {@link Team#playerOne} and {@link Team#playerTwo}
     */
    public Pair<String, String> getPlayerNames() {
        return new Pair<>(playerOne.getName(), playerTwo.getName());
    }


}
