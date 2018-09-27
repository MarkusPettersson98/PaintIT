package Views;

import Game.GameSession;
import Game.Player;
import Game.Team;
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

    public GameSetupView (FXMLLoader fxmlLoader){

        fxmlLoader.setLocation(getClass().getResource("/fxml/GameSetupView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        startDrawing.setId(ButtonFactory.createPaintingViewBtnId());
        startDrawing.setOnAction(e -> {
            setNames();
            TopController.show(startDrawing.getId());
        });

    }

    private void setNames () {

        String player1 = player1TextField.getText();
        String player2 = player2TextField.getText();

        GameSession.getInstance().addTeam(new Team(player1,
                                                   player2,
                                          player1 + " and " + player2));

    }


}
