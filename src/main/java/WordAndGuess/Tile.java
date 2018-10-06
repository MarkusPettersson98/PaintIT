package WordAndGuess;

import lombok.Getter;
import lombok.Setter;

public class Tile {
   private char letter;
   private int posAvailable;

   private Status status;
    public enum Status{
       Available,
        Used,
        NotInitialized;
    }
    public Tile(char letter, int posAvailable){
        this.letter = letter;
        this.posAvailable = posAvailable;
        status= Status.Available;
    }
    public Tile(){
        status = Status.NotInitialized;
        letter = '0';
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
}
