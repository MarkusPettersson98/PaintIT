package ViewObjects;

import WordAndGuess.Tile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lombok.Setter;


public abstract class TileSlot extends AnchorPane {

    @FXML Button tileButton;


    @Setter private Tile tile;

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
        tileButton.getStylesheets().add("/Css/TileBoardView.css");
        update();

    }
    public void addCorrectGuessCss(){
        tileButton.getStyleClass().clear();
        tileButton.getStyleClass().add("correctButton");
    }
    public void addIncorrectGuessCss(){
        tileButton.getStyleClass().clear();
        tileButton.getStyleClass().add("incorrectButton");
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public TileSlot(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filePath));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        tileButton.getStylesheets().add("/Css/TileBoardView.css");
        update();
    }

    public Button getTileButton() {
        return tileButton;
    }
    public void update(){

    }

}
