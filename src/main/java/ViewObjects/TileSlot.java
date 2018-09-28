package ViewObjects;

import WordAndGuess.Tile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class TileSlot extends AnchorPane {

    @FXML Button tileButton;


    private Tile tile;

    public Tile getTileLetter() {
        return tile;
    }

    private String filePath = "/fxml/tileSlot.fxml";

    public Tile getTile() {
        return tile;
    }



    public TileSlot(Tile tile){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filePath));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch(Exception e) {

            System.out.println(e.getMessage());
        }
        this.tile = tile;
        tileButton.setText(Character.toString(tile.getLetter()));

    }
    public Button getTileButton() {
        return tileButton;
    }
    public void update(){
        if(tile.getStatus() == Tile.Status.Used){
            tileButton.setVisible(false);
        }else{
            tileButton.setVisible(true);
        }
    }

}
