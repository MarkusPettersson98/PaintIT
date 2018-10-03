package ViewObjects;

import Game.GameSession;
import Tools.Observer;
import WordAndGuess.GuessLogic;
import WordAndGuess.Tile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TileBoardView extends VBox implements Observer{


    @FXML HBox hBoxBottom;
    @FXML HBox hBoxTop;
    @FXML VBox vBoxRoot;


    private Button testButton;
    private ArrayList<TileSlot> availableTileSlotList;
    private GuessLogic guessLogic;
    private TileBoardController tileBoardController;
    private TileSlot[] guessTileSlotList;


    public ArrayList<TileSlot> getAvailableTileSlotList() {
        return availableTileSlotList;
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
        availableTileSlotList =  new ArrayList<>();
        guessTileSlotList = new TileSlot[guessLogic.getCurrentWord().length()];
        createAvailableTileSlots(guessLogic.getAvailableTiles());
        createEmptyTileSlots();

        tileBoardController = new TileBoardController(guessLogic);
        setActionListeners();
        guessLogic.addObserver(this);
        update();
    }

    private void createEmptyTileSlots(){
        for(int i = 0; i < guessLogic.getCurrentWord().length(); i++){
            guessTileSlotList[i] =(new TileSlot(new Tile('0',i)));
        }
        for(TileSlot tileSlot: guessTileSlotList){
            hBoxTop.getChildren().add(tileSlot);
        }
    }

    private void setActionListeners(){
        setTilesActionListeners();
    }


    private void setTilesActionListeners(){ //eventListeners
        for(TileSlot t: availableTileSlotList){
            t.getTileButton().setOnAction(e-> tileBoardController.addTileToGuess(t.getTile()));
        }
        for(TileSlot t: guessTileSlotList){
            t.getTileButton().setOnAction(e-> tileBoardController.removeTileFromGuess(t.getTile()));
        }
    }

    private void createAvailableTileSlots(ArrayList<Tile> availableTiles){
        for(Tile tile: availableTiles){
            TileSlot temp = new TileSlot(tile);
            availableTileSlotList.add(temp);
            hBoxBottom.getChildren().add(temp);
        }
    }

    @Override
    public void update() {
        updateAvailableTileSlots();
        updateGuessTileSlots();


    }
    private void updateAvailableTileSlots(){
        for(TileSlot t: availableTileSlotList){
            t.update();
        }

    }
    private void updateGuessTileSlots(){
        for(int i =0; i<guessLogic.getGuessWord().length; i++){
            if(guessLogic.getGuessWord()[i] != null &&guessLogic.getGuessWord()[i].getPosGuess() != -1){
                guessTileSlotList[i].setTile(guessLogic.getGuessWord()[i]);
                guessTileSlotList[i].getTileButton().setVisible(true);
                guessTileSlotList[i].getTileButton().setText(Character.toString(guessTileSlotList[i].getTile().getLetter()));
            }else{
                guessTileSlotList[i].getTileButton().setVisible(false);
            }
        }
    }
}
