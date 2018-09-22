package ViewObjects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class TileBoard extends VBox {

    @FXML
    HBox testHBox;


    ArrayList<Character> availableTiles;
    String filePath = "/fxml/tileBoard.fxml";
    public TileBoard() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filePath));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);


        try {
            fxmlLoader.load();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public TileBoard(ArrayList<Character> availableTiles) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filePath));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        for(Character c: availableTiles){
            TileSlot temp = new TileSlot(c);
            testHBox.getChildren().add(temp);
        }
    }
}
