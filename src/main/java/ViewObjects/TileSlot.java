package ViewObjects;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class TileSlot extends AnchorPane {
    private String filePath = "/fxml/tileSlot.fxml";
    public TileSlot(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filePath));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    this.getChildren().add(new Button("TileSLOT WORKS"));
    }

}
