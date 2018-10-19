package Controller;

import Model.CanvasModel;
import Model.Game.Score;
import Model.Game.Team;
import Util.CountDownTimer;
import Util.CountDownUser;
import Util.Observer;
import Util.ViewFactory;
import Views.GameScreen;
import Model.WordAndGuess.Tile;
import Model.WordAndGuess.Word;
import javafx.scene.layout.Pane;
import Model.WordAndGuess.GuessLogic;

import java.util.List;

/**
 * Main class, this is where most parts of the application is connected.
 */

public class TopController {

    private Team team;

    private final GameLogic gameLogic;
    private final ViewController viewController;
    private final CountDownTimer countDownTimer;

    /**
     * A boolean for if the game is over or if the players can keep playing.
     */
    private Boolean gameOver;

    public TopController() {
        gameLogic = new GameLogic();
        final List<GameScreen> gameScreens = ViewFactory.createAllViews(this);
        viewController = new ViewController(gameScreens);
        countDownTimer = new CountDownTimer();

        gameLogic.setupHighScores();
        gameOver = false;
    }


    public void resetTimer() {
        countDownTimer.resetTimer();
    }

    public Pane getCurrentPane() { return viewController.getCurrentView(); }

    /**
     * Method for invoking {@link GameScreen#init()} of view to be shown and switching current view
     * in {@link ViewController}.
     * @param url
     */
    public void show(String url) {
        // Get next view to be shown
        viewController.prepareNextView(url);
        // Call init method on next view
        final GameScreen gameScreenTmp = viewController.getNextScreen();
        gameScreenTmp.init();
        // Show next view
        viewController.show();
    }


    public void startCountDown(int seconds, CountDownUser caller){
        countDownTimer.startCountDown(seconds,caller);
    }
    /**
     * If there's no prior instance of {@link Team}, add passed reference to {@link TopController#team}.
     * @param team
     */

    public void addTeam(Team team) {
        if(this.team == null) { this.team = team; }
    }

    /**
     * @return Name of {@link TopController#team}.
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
     * @return Name of players in {@link TopController#team} as a pair of Strings.
     */
    public String getGuesserName() {
        return team.getGuesserName();
    }

    public String getDrawerName (){
        return team.getDrawerName();
    }

    public int getTeamStreak() { return team.getStreak(); }

    public void incrementTeamStreak() {
        Word currentWord = getCurrentWord();
        int points;
        switch(currentWord.getDifficulty_level()) {
            case EASY: points = 1; break;
            case MEDIUM: points = 2; break;
            case HARD: points = 3; break;
            default: points = 0; break;
        }
        team.incrementStreak(points);
    }

    public void resetTeamStreak() {
        team.resetStreak();
    }

    public CanvasModel getCanvas() { return gameLogic.getCurrentPainting(); }

    public void setCurrentPainting(CanvasModel canvasModel) { gameLogic.setCurrentPainting(canvasModel); }

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
     * Sets {@link TopController#gameOver} to true, which means that they have lost or quit the game session.
     */
    public void setToGameOver(boolean gameState){
        // Mark that current game session is over!
        gameOver = gameState;
    }

    public void gameOver() {
        // Game over, save team's streak if necessary
        saveScore();
        // Reset words
        gameLogic.newGame();
        // Remove current team
        team = null;
    }

    public void newTurn() {
        // Turn over, generate, switch guesser/drawer..
        team.changeDrawer();
    }

    public void saveScore() {
        if (team != null) {
            gameLogic.saveScore(team.getTeamName(), team.getStreak());
        }
    }

    public List<Score> getHighScores() {
        return gameLogic.getHighScores();
    }

}
