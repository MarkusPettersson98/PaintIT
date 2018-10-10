package Game;

import javafx.util.Pair;
import lombok.Getter;

import java.util.Random;

/**
 * Represents a team consisting of two {@link Player}s.
 */

public class Team {

    private final Player playerOne;
    private final Player playerTwo;
    @Getter private final String teamName;
    @Getter private int streak;

    private Player drawer;
    private Player guesser;

    Random rand = new Random();

    public Team(String playerOne, String playerTwo, String teamName) {
        this.playerOne = new Player(playerOne);
        this.playerTwo = new Player(playerTwo);
        this.teamName = teamName;
        setGuesserAndDrawer();
    }

    public void incrementStreak() { streak++; }

    public void resetStreak() {
        // Someone guessed the wrong word one too many times. What a pity..
        streak = 0;
    }

    private void setGuesserAndDrawer (){
        int randomNumber = rand.nextInt(2);
        if (randomNumber == 0){
            guesser = playerOne;
            drawer = playerTwo;
        }
        else {
            guesser = playerTwo;
            drawer = playerOne;
        }
    }

    public void changeDrawer (){
        Player temporary = drawer;
        drawer = guesser;
        guesser = temporary;
    }

    /** Returns the teams players.
     *
     * @return returns a {@link Pair} of {@link Team#drawer} and {@link Team#guesser}
     */
    public Pair<String, String> getPlayerNames() {
        return new Pair<>(drawer.getName(), guesser.getName());
    }

    public String getGuesserName (){
        return guesser.getName();
    }

    public String getDrawerName (){
        return drawer.getName();
    }

}
