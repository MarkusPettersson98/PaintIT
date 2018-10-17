package Model.Game;

import Views.Components.CanvasView;
import Util.CountDownTimer;
import Util.CountDownUser;
import Util.Observer;
import Util.ViewFactory;
import Views.GameScreen;
import Model.WordAndGuess.Tile;
import Model.WordAndGuess.Word;
import Controller.TopController;
import Views.HighScoreList;
import javafx.scene.layout.Pane;
import Model.WordAndGuess.GuessLogic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class, this is where most parts of the application is connected.
 */

public class GameSession {

    private Team team;
    private String highScoreUrl = "/persistent_data/highscores.txt";

    private final GameLogic gameLogic;
    private final TopController topController;
    private final CountDownTimer countDownTimer;

    /**
     * A boolean for if the game is over or if the players can keep playing.
     */
    private Boolean gameOver;

    public GameSession() {
        gameLogic = new GameLogic();
        List<GameScreen> gameScreens = ViewFactory.createAllViews(this);
        topController = new TopController(gameScreens);
        countDownTimer = new CountDownTimer();
        getHighScores();
        gameOver = false;
    }

    public void resetTimer() {
        countDownTimer.resetTimer();
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


    public void startCountDown(int seconds, CountDownUser caller){
        countDownTimer.startCountDown(seconds,caller);
    }
    /**
     * If there's no prior instance of {@link Team}, add passed reference to {@link GameSession#team}.
     * @param team
     */

    public void addTeam(Team team) {
        if(this.team == null) { this.team = team; }
    }

    /**
     * @return Name of {@link GameSession#team}.
     */
    public String getTeamName() {
        if(team != null)
            return team.getTeamName();
        else return "There's no team!";
    }
    public GuessLogic getGuessLogic(){
        return gameLogic.getGuessLogic();
    }

    /**
     *
     * @return Name of players in {@link GameSession#team} as a pair of Strings.
     */
    public String getGuesserName() {
        return team.getGuesserName();
    }

    public String getDrawerName (){
        return team.getDrawerName();
    }

    public int getTeamStreak() { return team.getStreak(); }

    public void incrementTeamStreak() { team.incrementStreak();}

    public void resetTeamStreak() {
        team.resetStreak();
    }

    public CanvasView getCanvas() { return gameLogic.getCurrentPainting(); }

    public void setCurrentPainting(CanvasView canvasView) { gameLogic.setCurrentPainting(canvasView); }

    public Word getCurrentWord() {
        return gameLogic.getCurrentWord();
    }

    public void setCurrentWord(Word word){
        gameLogic.setCurrentWord(word);
    }

    public Tile[] getAvailableTiles(){return getGuessLogic().getAvailableTiles();}

    public void addGuessLogicObservers(Observer observer){getGuessLogic().addObserver(observer);}

    public Tile[] getGuessWord(){return getGuessLogic().getGuessWord();}

    public boolean guessCurrentWord(){return getGuessLogic().guessCurrentWord();}

    public List<Word> getPossibleWords(){
        return gameLogic.getPossibleWords();
    }

    public Boolean getGameOver (){
        return gameOver;
    }

    /**
     * Sets {@link GameSession#gameOver} to true, which means that they have lost or quit the game session.
     */
    public void setToGameOver(){
        gameOver = true;
    }

    public void gameOver() {
        // Game over, save team's streak if necessary,
    }

    public void newTurn() {
        // Turn over, generate, switch guesser/drawer..
        team.changeDrawer();
    }

    public List<Score> getHighScores() {
        // Open file from backend
        // Need to use InputStream, as the JAR executable will have trouble to read from file otherwise
        List<Score> highScores = new ArrayList<>();
        // Prepare to read from backend
        try {
            String highScorePath = getClass().getResource(highScoreUrl).getFile();
            Scanner sc = new Scanner(new File(highScorePath));
            System.out.println(sc.hasNextLine());
            while(sc.hasNextLine()) {
                // A line is formatted as "teamName:streak"
                String currentWord = sc.nextLine();

                String[] score = currentWord.split(":");
                // Team name is the string before ':' in the parsed line
                String teamName = score[0];
                // Streak is the string after ':' in the parsed line
                int streak = Integer.valueOf(score[1]);
                // Got our information, create a Score and add it to high score list!
                Score tmpScore = new Score(teamName, streak);

                highScores.add(tmpScore);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Looped over all lines, return what we got back!
        return highScores;
    }

    public void saveScore() {
        // Get current streak
        Score currentScore = new Score(team.getTeamName(), team.getStreak());

        HighScoreList highScoreList = new HighScoreList(getHighScores());
        // Try to add new streak to high score list. add() always returns an updated list
        HighScoreList newHighScores = highScoreList.add(currentScore);

        String highScoreListString = newHighScores.getFormattedString();
        // String formattedScore = currentScore.getFormattedScore();
        try {
            // Write the updates list to backend!
            String highScorePath = getClass().getResource(highScoreUrl).getFile();
            Files.write(Paths.get(highScorePath), (highScoreListString).getBytes(), StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
