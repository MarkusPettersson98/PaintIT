package Model.Game;

import javafx.util.Pair;
import lombok.Getter;

import java.util.Random;

/**
 * Represents a team consisting of two {@link Player}s.
 */

public class Team {

    /**
     * The first {@link Player}.
     */
    private final Player playerOne;

    /**
     * The second {@link Player}.
     */
    private final Player playerTwo;

    /**
     * The name of the team, ie a combination of the players' names.
     */
    @Getter private final String teamName;

    /**
     * The team's streak is the number of times the team has guessed a word correctly.
     */
    @Getter private int streak;


    /**
     * The {@link Player} that is drawing the painting.
     */
    private Player drawer;

    /**
     * The {@link Player} that is guessing what the drawer painted.
     */
    private Player guesser;

    private Random rand = new Random();

    public Team(String playerOne, String playerTwo) {
        this.playerOne = new Player(playerOne);
        this.playerTwo = new Player(playerTwo);
        this.teamName = playerOne + " and " + playerTwo;
        setGuesserAndDrawer();
    }

    /**
     * Increments the {@link Team#streak} with one.
     */
    public void incrementStreak() { streak++; }

    /**
     * Sets the {@link Team#streak} to zero.
     */
    public void resetStreak() {
        streak = 0;
    }

    /**
     * Randomly sets which {@link Player} who is the {@link Team#guesser} and who is the {@link Team#drawer}.
     */
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

    /**
     * Changes who is the guesser/drawer. The {@link Player} who was the {@link Team#drawer}
     * changes to the {@link Team#guesser} and vice versa.
     */
    public void changeDrawer (){
        Player temporary = drawer;
        drawer = guesser;
        guesser = temporary;
    }

    /** Returns the teams players.
     *
     * @return returns a {@link Pair} of {@link Team#playerOne} and {@link Team#playerTwo}
     */
    public Pair<String, String> getPlayerNames() {
        return new Pair<>(playerOne.getName(), playerTwo.getName());
    }

    /** Returns the guesser's name.
     *
     * @return returns the name of {@link Team#guesser}
     */
    public String getGuesserName (){
        return guesser.getName();
    }

    /**Returns the drawer's name.
     *
     * @return returns the name of {@link Team#drawer}
     */
    public String getDrawerName (){
        return drawer.getName();
    }

}
