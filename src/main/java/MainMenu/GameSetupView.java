package MainMenu;

import Game.Player;
import Util.ButtonFactory;
import com.PaintIT.app.TopController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GameSetupView extends AnchorPane {

    @FXML private TextField player1TextField;
    @FXML private TextField player2TextField;
    @FXML private Button startDrawing;

    Player player1 = new Player();
    Player player2 = new Player();

    public GameSetupView (){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/GameSetupView.fxml"));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        startDrawing.setId(ButtonFactory.createPaintingViewBtnId());
        startDrawing.setOnAction(e -> {
            TopController.show(startDrawing.getId());
        });

    }

    private void setNames (){
        String player1Name = player1TextField.getText();
        player1.setName(player1Name);
        String player2Name = player2TextField.getText();
        player2.setName(player2Name);
    }


}
