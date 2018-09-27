package Views;

import Game.Player;
import Util.ButtonFactory;
import com.PaintIT.app.TopController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GameSetupView extends AnchorPane {

    @FXML private TextField player1TextField;
    @FXML private TextField player2TextField;
    @FXML private Button startDrawing;
    @FXML private Button backButton;
    @FXML private ImageView backButtonImageView;

    Player player1 = new Player();
    Player player2 = new Player();

    public GameSetupView (FXMLLoader fxmlLoader){

        fxmlLoader.setLocation(getClass().getResource("/fxml/GameSetupView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        startDrawing.setId(ButtonFactory.createWordRevealViewBtnId());
        startDrawing.setOnAction(e -> {
            TopController.show(startDrawing.getId());
        });

        backButtonImageView.setId(ButtonFactory.createMainMenuViewBtnId());
        backButtonImageView.setOnMouseClicked(e -> {
            TopController.show(backButtonImageView.getId());
            String path = "images/icon_back.png";
            backButtonImageView.setImage((new Image(getClass().getClassLoader().getResourceAsStream((path)))));
        });

    }

    @FXML
    private void backButtonImageViewEntered (){
        String path = "images/icon_back_hover.png";
        backButtonImageView.setImage((new Image(getClass().getClassLoader().getResourceAsStream((path)))));
    }

    @FXML
    private void backButtonImageViewExited (){
        String path = "images/icon_back.png";
        backButtonImageView.setImage((new Image(getClass().getClassLoader().getResourceAsStream((path)))));
    }

    private void setNames (){
        String player1Name = player1TextField.getText();
        player1.setName(player1Name);
        String player2Name = player2TextField.getText();
        player2.setName(player2Name);
    }


}
