package ViewObjects;

import Tools.Observer;
import Util.GeneralUtil;
import WordAndGuess.GuessLogic;
import WordAndGuess.Tile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TileBoardView extends VBox implements Observer{

    @FXML HBox hBoxTop;
    @FXML HBox hBoxBottom;
    @FXML VBox vBoxRoot;
    private TextField guessTxtf;
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

        guessTxtf = new TextField();
        hBoxBottom.getChildren().add(guessTxtf);
        tileBoardController = new TileBoardController(guessLogic);
        setTilesActionListeners();
        guessLogic.addObserver(this);
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
            hBoxTop.getChildren().add(temp);
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
