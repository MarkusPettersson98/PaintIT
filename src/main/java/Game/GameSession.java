package Game;

import Canvas.CanvasController;
import Canvas.CanvasModel;
import Canvas.CanvasView;
import Tools.CountDownTimer;
import Tools.Observer;
import Util.ViewFactory;
import Views.GameScreen;
import WordAndGuess.Tile;
import WordAndGuess.Word;
import com.PaintIT.app.TopController;
import javafx.scene.layout.Pane;
import WordAndGuess.GuessLogic;
import lombok.Getter;

import java.util.List;
import java.util.WeakHashMap;

/**
 * Main class, this is where most parts of the application is connected.
 */

public class GameSession {

    private Team team;

    private final GameLogic gameLogic;
    private final TopController topController;
    @Getter private final CountDownTimer countDownTimer;

    public GameSession() {
        gameLogic = new GameLogic();
        List<GameScreen> gameScreens = ViewFactory.createAllViews(this);
        topController = new TopController(gameScreens);
        countDownTimer = new CountDownTimer();
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

    public void newTurn() {
        // Turn over, generate, switch guesser/drawer..
       // gameLogic.updateGameWord();
        team.changeDrawer();
    }
}
