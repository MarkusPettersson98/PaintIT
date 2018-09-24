package ViewObjects;

import Util.GeneralUtil;
import WordAndGuess.GuessLogic;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TileBoard extends VBox {

    @FXML HBox hBoxTop;
    @FXML HBox hBoxBottom;
    @FXML VBox vBoxRoot;
    private TextField guessTxtf;
    private Button testButton;
    private ArrayList<TileSlot> tileSlotList;
    private GuessLogic guessLogic;

    public TextField getGuessTxtf() {
        return guessTxtf;
    }

    public ArrayList<TileSlot> getTileSlotList() {
        return tileSlotList;
    }

    String filePath = "/fxml/tileBoard.fxml";


    public TileBoard(GuessLogic guessLogic, ArrayList<Character> availableTiles) {
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
        createTileSlots(availableTiles);

        guessTxtf = new TextField();
        hBoxBottom.getChildren().add(guessTxtf);



    }
    private void createTileSlots(ArrayList<Character> availableTiles){
        for(Character c: availableTiles){
            TileSlot temp = new TileSlot(c);
            tileSlotList.add(temp);
            hBoxTop.getChildren().add(temp);
        }
    }
    public void updateView(){
    guessTxtf.setText(GeneralUtil.CharArrayListToString(guessLogic.getGuessWord()));

    }

}
