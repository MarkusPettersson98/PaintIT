package ViewObjects;

import Game.GameSession;
import Tools.Observer;
import WordAndGuess.GuessLogic;
import WordAndGuess.Tile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
        for(TileSlot tileSlot: guessTileSlotArray){
            hBoxTop.getChildren().add(tileSlot);
        }
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
