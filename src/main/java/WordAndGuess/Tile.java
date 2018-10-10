package WordAndGuess;

import lombok.Getter;
import lombok.Setter;

/**
 *Represents an available Tile in the game. A has a letter
 */
public class Tile {
   private char letter;
   private int posAvailable;

   private Status status;

    /**
     *An Enumerator that represents what status the tile has in the game.
     */
    public enum Status{
       Available,
        Used,
    }
    /**
     *Instansiates the tile with its data
     * @param letter the letter of the tile
     * @param posAvailable the index of the position it has of the available Tiles
     */
    public Tile(char letter, int posAvailable){
        this.letter = letter;
        this.posAvailable = posAvailable;
        status= Status.Available;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public void setPosAvailable(int posAvailable) {
        this.posAvailable = posAvailable;
    }

    public char getLetter() {

        return letter;
    }

    public int getPosAvailable() {
        return posAvailable;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
       return Character.toString(letter);
    }
}
