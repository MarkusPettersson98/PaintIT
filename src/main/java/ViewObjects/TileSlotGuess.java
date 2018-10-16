package ViewObjects;

import Model.WordAndGuess.Tile;
/**
 *Represents a Tile in the players Guess visually.
 */
public class TileSlotGuess extends TileSlot {


    public TileSlotGuess(){
        super();
    }
    public TileSlotGuess(Tile tile){
        super(tile);
    }
    @Override
    public void update(){
        tileButton.getStyleClass().clear();
        if(getTile() == null){
            tileButton.setDisable(true);
            tileButton.getStyleClass().add("emptyButton");
        }else{
            tileButton.getStyleClass().add("visibleButton");
            tileButton.setText(Character.toString(getTile().getLetter()));
            tileButton.setDisable(false);
        }
    }
}
