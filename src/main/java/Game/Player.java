package Game;

public class Player {

    private String name;
    private Boolean drawer;

    Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDrawer(Boolean drawer) {
        this.drawer = drawer;
    }

    public Boolean isDrawer() {
        return drawer;
    }
}
