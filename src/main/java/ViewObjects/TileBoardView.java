package ViewObjects;

import Game.GameSession;
import Tools.Observer;
import WordAndGuess.GuessLogic;
import WordAndGuess.Tile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class TileBoardView extends VBox implements Observer{


    @FXML HBox hBoxBottom;
    @FXML HBox hBoxTop;
    @FXML VBox vBoxRoot;
    private TileSlot[] availableTileSlotArray;
    private GuessLogic guessLogic;
    private TileBoardController tileBoardController;
    private TileSlot[] guessTileSlotArray;

    public TileSlot[] getAvailableTileSlotArray() {
        return availableTileSlotArray;
    }
    String filePath = "/fxml/tileBoard.fxml";

    public TileBoardView() {
        initFXML();
        initTiles();

    }
    private void initFXML(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filePath));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        this.guessLogic = GameSession.getInstance().getGuessLogic();
        try {
            fxmlLoader.load();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void initTiles(){
        availableTileSlotArray =  new TileSlot[guessLogic.getAvailableTiles().length];
        guessTileSlotArray = new TileSlot[guessLogic.getCurrentWord().length()];
        createAvailableTileSlots(guessLogic.getAvailableTiles());
        createEmptyTileSlots();

        tileBoardController = new TileBoardController(guessLogic);
        setActionListeners();
        guessLogic.addObserver(this);
        update();
    }

    private void createEmptyTileSlots(){
        for(int i = 0; i < guessLogic.getCurrentWord().length(); i++){
            guessTileSlotArray[i] = new TileSlotGuess();
        }

        hBoxTop.getChildren().add(getGuessTileOffset());
        for(TileSlot tileSlot: guessTileSlotArray){
            hBoxTop.getChildren().add(tileSlot);
        }
    }

    private AnchorPane getGuessTileOffset(){
        AnchorPane buffer = new AnchorPane();
        buffer.setPrefSize((double)(200+(8-guessTileSlotArray.length)*50),100);
        return  buffer;
    }
    private AnchorPane getAvailableTileOffset(){
        AnchorPane buffer = new AnchorPane();
        buffer.setPrefSize(200,100);
        return  buffer;
    }

    private void setActionListeners(){
        setTilesActionListeners();
    }


    private void setTilesActionListeners(){ //eventListeners
        for(TileSlot t: availableTileSlotArray){
            t.getTileButton().setOnAction(e-> tileBoardController.addTileToGuess(t.getTile()));
        }
        for(TileSlot t: guessTileSlotArray){
            t.getTileButton().setOnAction(e-> tileBoardController.removeTileFromGuess(t.getTile()));
        }
    }

    private void createAvailableTileSlots(Tile[] availableTiles){
        hBoxBottom.getChildren().add(getAvailableTileOffset());
        for(Tile tile: availableTiles){
            TileSlot temp = new TileSlotAvailable(tile);
            availableTileSlotArray[tile.getPosAvailable()] = temp;
            hBoxBottom.getChildren().add(temp);
        }
    }
    @Override
    public void update() {
        updateAvailableTileSlots();
        updateGuessTileSlots();

        if(isGuessComplete()){
            checkIfCorrectGuess();
        }
    }
    private boolean isGuessComplete(){
        for(Tile t: guessLogic.getGuessWord()){
            if(t == null){
                return false;
            }
        }
        return true;
    }
    private void checkIfCorrectGuess(){
        if(guessLogic.guessCurrentWord()){
            handleCorrectGuess();
        }else{
            handleIncorrectGuess();
        }
    }

    private void handleCorrectGuess(){
        for(TileSlot t: guessTileSlotArray){
            t.tileButton.getStyleClass().clear();
            t.tileButton.getStyleClass().add("correctButton");
        }
    }
    private void handleIncorrectGuess(){
        for(TileSlot t: guessTileSlotArray){
            t.tileButton.getStyleClass().clear();
            t.tileButton.getStyleClass().add("incorrectButton");
        }
    }
    private void updateAvailableTileSlots(){

        for(TileSlot t: availableTileSlotArray){
            t.update();
        }
    }
    private void updateGuessTileSlots(){
        int count = 0;
        for(Tile t: guessLogic.getGuessWord()){
            guessTileSlotArray[count].setTile(t);
            guessTileSlotArray[count].update();
            count++;
        }
    }
}
