package WordAndGuess;

public class Tile {
   private char letter;
    private int pos;

    public Tile(char letter, int pos){
        this.letter = letter;
        this.pos = pos;
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
}
