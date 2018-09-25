package ViewObjects;

import Tools.Observer;
import Util.GeneralUtil;
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

    @FXML AnchorPane ancTop;
    @FXML HBox hBoxBottom;
    @FXML Button removeTileBtn;
    @FXML VBox vBoxRoot;
    @FXML TextField guessTxtf;

    private Button testButton;
    private ArrayList<TileSlot> tileSlotList;
    private GuessLogic guessLogic;
    private TileBoardController tileBoardController;

    public TextField getGuessTxtf() {
        return guessTxtf;
    }

    public ArrayList<TileSlot> getTileSlotList() {
        return tileSlotList;
    }
    String filePath = "/fxml/tileBoard.fxml";

    public TileBoardView(GuessLogic guessLogic) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filePath));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        this.guessLogic = guessLogic;
        tileSlotList =  new ArrayList<>();
        createTileSlots(guessLogic.getAvailableTiles());

    
        tileBoardController = new TileBoardController(guessLogic);
        setActionListeners();
        guessLogic.addObserver(this);
    }

    private void setActionListeners(){
        removeTileBtn.setOnAction(e->tileBoardController.removeTileFromGuess());
        setTilesActionListeners();
    }
    private void setTilesActionListeners(){ //eventListeners
        for(TileSlot t: tileSlotList){
            t.getTileButton().setOnAction(e-> tileBoardController.addTileToGuess(t.getTile()));
        }

    }
    private void createTileSlots(ArrayList<Tile> availableTiles){
        for(Tile tile: availableTiles){
            TileSlot temp = new TileSlot(tile);
            tileSlotList.add(temp);
            hBoxBottom.getChildren().add(temp);
        }
    }

    @Override
    public void update() {
        guessTxtf.setText(guessLogic.getGuessString());
        for(TileSlot t: tileSlotList){
           t.update();
        }
    }
}
