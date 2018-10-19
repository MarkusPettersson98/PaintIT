package Views.Components;

import Controller.TileBoardController;
import Controller.TopController;
import Util.CountDownUser;
import Util.Observer;
import Util.ButtonFactory;
import Model.WordAndGuess.Tile;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.util.Timer;
import java.util.TimerTask;

/** Represents the backend Guess-components to the user with a window filled with Tiles (TileSlot), that are clickable
 *
 *
 */

public class TileBoardView extends VBox implements Observer, CountDownUser{


    @FXML HBox hBoxBottom;
    @FXML HBox hBoxTop;
    @FXML VBox vBoxRoot;
    @FXML
    Label countDownLbl;

    private TileSlot[] availableTileSlotArray;
    private final TopController topController;
    private TileBoardController tileBoardController;
    private TileSlot[] guessTileSlotArray;
    private static final int guessTime = 30;

    String filePath = "/fxml/tileBoard.fxml";

    /** Loads itself from it´s fxml file, and instansiates the tiles that visualises the guess from the backend.
     * Further, allows for player to use keyboard to guess.
     */
    public TileBoardView(final TopController topController) {
        this.topController = topController;
        initFXML();
        initTiles();
        this.addEventHandler(javafx.scene.input.KeyEvent.KEY_PRESSED, m -> {
            tileBoardController.handleKeyCode(m.getCode().toString());
        });
        initCountDown();
    }
    private void initCountDown(){
        topController.startCountDown(guessTime,this);
        countDownLbl.setText(" Time left: " + guessTime);
    }
    private void initFXML(){
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filePath));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch(Exception e) {
            //ouch
        }
    }

    private void initTiles(){
        availableTileSlotArray =  new TileSlot[topController.getAvailableTiles().length];
        guessTileSlotArray = new TileSlot[topController.getCurrentWord().getWord().length()];
        createAvailableTileSlots(topController.getAvailableTiles());

        createEmptyTileSlots();

        tileBoardController = new TileBoardController(topController.getGuessLogic());
        setActionListeners();
        topController.addGuessLogicObservers(this);
        update();

    }

    private void createEmptyTileSlots(){
        for(int i = 0; i < topController.getCurrentWord().getWord().length(); i++){
            guessTileSlotArray[i] = new TileSlotGuess();
        }
        hBoxTop.getChildren().add(getGuessTileOffset());
        for(final TileSlot tileSlot: guessTileSlotArray){
            hBoxTop.getChildren().add(tileSlot);
        }

    }

    private void createAvailableTileSlots(final Tile... availableTiles){
        hBoxBottom.getChildren().add(getAvailableTileOffset());
        for(final Tile tile: availableTiles){
            final TileSlot temp = new TileSlotAvailable(tile);
            availableTileSlotArray[tile.getPosAvailable()] = temp;
            hBoxBottom.getChildren().add(temp);
        }
    }

    private AnchorPane getGuessTileOffset(){
        final AnchorPane buffer = new AnchorPane();
        buffer.setPrefSize((double)(50+(8-guessTileSlotArray.length)*50),100);
        return buffer;
    }

    private AnchorPane getAvailableTileOffset(){
        final AnchorPane buffer = new AnchorPane();
        buffer.setPrefSize(200,100);
        return buffer;

    }

    private void setActionListeners(){
        setTilesActionListeners();
    }

    private void setTilesActionListeners(){ //eventListeners

        for(final TileSlot t: availableTileSlotArray){
            t.getTileButton().setOnAction(e-> tileBoardController.addTileToGuess(t.getTile()));
        }
        for(final TileSlot t: guessTileSlotArray){
            t.getTileButton().setOnAction(e-> tileBoardController.removeTileFromGuess(t.getTile()));
        }
    }


    /** This method is called (through the observer pattern) whenever the backend is changed.
            * It loops updates the TileSlots so that they represent the backend and if a correct guess has been made
     * then it Links to handleCorrectGuess.
     */
    @Override
    public void update() {
        updateAvailableTileSlots();
        updateGuessTileSlots();

        if(isGuessComplete()){
            checkIfCorrectGuess();
        }
    }
    private boolean isGuessComplete(){
        for(final Tile t: topController.getGuessWord()){
            if(t == null){
                return false;
            }
        }
        return true;
    }

    private void checkIfCorrectGuess(){
        if(topController.guessCurrentWord()){
            handleCorrectGuess();
        }else{
            handleIncorrectGuess();
        }
    }

    /** This method handles a correct Guess - makes all the Tiles Green
     */
    private void handleCorrectGuess(){
        for(final TileSlot t: guessTileSlotArray){
           t.addCorrectGuessCss();
        }
        topController.resetTimer();
        startTimer();
        topController.incrementTeamStreak();
    }
    /** This method handles a correct Guess - makes all the Tiles Red
     */
    private void handleIncorrectGuess(){
        for(final TileSlot t: guessTileSlotArray){
          t.addIncorrectGuessCss();
        }
    }
    private void updateAvailableTileSlots(){
        for(final TileSlot t: availableTileSlotArray){
            t.update();
        }
    }
    
    private void updateGuessTileSlots(){
        int count = 0;
        for(final Tile t: topController.getGuessWord()){
            guessTileSlotArray[count].setTile(t);
            guessTileSlotArray[count].update();
            count++;
        }
    }

    public void startTimer() {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Platform.runLater(() -> {timer.cancel(); changeToDoneView();});
            }
        },1000,1);
    }

    /**
     * Changes the view to doneView.
     */
    public void changeToDoneView(){
        topController.show(ButtonFactory.createDoneViewBtnId());
    }

    /**
     * Updates the {@link TileBoardView#countDownLbl} when a second has passed. It the changes the colour of the
     * label when there is only 10 seconds left.
     * @param secondsLeft The amount of seconds left
     */
    @Override
    public void handleSecondPassed(int secondsLeft) {
        countDownLbl.setText(" Time left: " + Integer.toString(secondsLeft));
        if (secondsLeft == 10){
            countDownLbl.setTextFill(Paint.valueOf("red"));
        }
    }

    /**
     * Sets {@link TopController#gameOver to true} and changes the view to doneView when the timer is finished.
     */
    @Override
    public void handleTimerFinished() {
        topController.setToGameOver(true);
        changeToDoneView();
    }
}
