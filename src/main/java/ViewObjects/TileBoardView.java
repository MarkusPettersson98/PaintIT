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
    private ArrayList<TileSlot> guessTileSlotList;


    public ArrayList<TileSlot> getAvailableTileSlotList() {
        return availableTileSlotList;
    }
    String filePath = "/fxml/tileBoard.fxml";

    public TileBoardView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filePath));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        this.guessLogic = GameSession.getInstance().getGuessLogic();
        try {
            fxmlLoader.load();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        availableTileSlotList =  new ArrayList<>();
        guessTileSlotList = new ArrayList<>();
        createTileSlots(guessLogic.getAvailableTiles());
        createEmptyTileSlots();



        tileBoardController = new TileBoardController(guessLogic);
        setActionListeners();
        guessLogic.addObserver(this);

    }

    private void createEmptyTileSlots(){
        for(int i = 0; i < guessLogic.getCurrentWord().length(); i++){
            guessTileSlotList.add(new TileSlot(new Tile('0',i)));
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
    }

    private void createTileSlots(ArrayList<Tile> availableTiles){
        for(Tile tile: availableTiles){
            TileSlot temp = new TileSlot(tile);
            availableTileSlotList.add(temp);
            hBoxBottom.getChildren().add(temp);
        }
    }

    @Override
    public void update() {
        for(TileSlot t: availableTileSlotList){
           t.update();
        }
        if(guessLogic.getCorrectGuessMade()){
            System.out.println("CORRECT GUESS");
        }
    }
}
