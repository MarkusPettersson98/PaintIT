package WordAndGuess;

public class Tile {
   private char letter;
   private int pos;
   private Status status;



    public enum Status{
       Available,
        Used;
    }
    public Tile(char letter, int pos){
        this.letter = letter;
        this.pos = pos;
        status= Status.Available;
    }
    public void setLetter(char letter) {
        this.letter = letter;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public char getLetter() {

        return letter;
    }

    public int getPos() {
        return pos;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {

        return status;
    }
}
