package Game;

/**
 * Represents a Player.
 */
public class Player {

    private String name;

    public Player(){
        name = "No Name";
    }

    public Player(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
