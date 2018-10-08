package ViewObjects;

import WordAndGuess.Tile;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lombok.Setter;

/** This class represents a Tile Visually.
 */
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


    /** Loads it´s fxml file and adds a stylesheet "/Css/TileBoardView.css".
     * @param tile loads the data from the tile to represent it visually
     *
     */
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
    /** Loads it´s fxml file and adds a stylesheet "/Css/TileBoardView.css".
     * This doesn´t load a tile from the backend and therefore is a blank tileSlot initially.
     *
     */
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
    /**Changes the Css for TileButton
     */
    public void addCorrectGuessCss(){
        tileButton.getStyleClass().clear();
        tileButton.getStyleClass().add("correctButton");
    }
    /**Changes the Css for TileButton
     */
    public void addIncorrectGuessCss(){
        tileButton.getStyleClass().clear();
        tileButton.getStyleClass().add("incorrectButton");
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }



    public Button getTileButton() {
        return tileButton;
    }
    /** Updates the the tileslot to represent a tile or blank TileSlot.
     */
    public void update(){
    }

}
