package ViewObjects;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class TileSlot extends AnchorPane {

    @FXML Button tileButton;

    private Character tileLetter;
    private String filePath = "/fxml/tileSlot.fxml";
    public TileSlot(Character tileLetter){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filePath));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        this.tileLetter = tileLetter;
        tileButton.setText(tileLetter.toString());

    }
    public Button getTileButton() {
        return tileButton;
    }

}
