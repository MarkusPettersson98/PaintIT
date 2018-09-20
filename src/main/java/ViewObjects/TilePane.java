package ViewObjects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TilePane extends VBox {

    @FXML
    HBox testHBox;

    String filePath =  "../../resources/tilesPane.fxml";
    public TilePane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filePath));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);

        Label testLbl = new Label("TESTAR");

        try {
            fxmlLoader.load();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        this.setPrefSize(100,100);


    }
}
