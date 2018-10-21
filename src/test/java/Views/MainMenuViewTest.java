package Views;

import Controller.TopController;
import Model.Game.Team;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class MainMenuViewTest extends ApplicationTest{

    TopController topController;
    Scene scene;
    AnchorPane howToPlayAnchor;
    AnchorPane mainmenuAnchor;
    AnchorPane lighboxAnchor;
    TextArea howToPlayText;

    public <T extends Node> T find(final String query) {
        return lookup(query).query();
    }

    @Override
    public void start(Stage stage) {
        topController = new TopController();
        topController.addTeam(new Team("testPlayer", "testPlayer2"));

        scene = new Scene(topController.getCurrentPane());
        topController.show(MainMenuView.class.getSimpleName());

        stage.setScene(scene);
        stage.show();

        howToPlayAnchor = (AnchorPane) find("#howToPlayAnchorPane");
        mainmenuAnchor = (AnchorPane) find("#mainMenuAnchorPane");
        lighboxAnchor = (AnchorPane) find("#lightBoxAnchorPane");
        howToPlayText = (TextArea) find ("#instructionsTextArea");
    }

    @Test
    public void playButtonTest(){
      clickOn(".button-play");
      assert (topController.getCurrentPane().getChildren().toString().contains("GameSetupView"));
    }

    @Test
    public void howToPlayTest (){
        clickOn(".button-mainMenu");
        assert (howToPlayAnchor.isVisible() && !mainmenuAnchor.isVisible());
    }

    @Test
    public void closeButtonTest (){
        clickOn(".button-mainMenu");
        clickOn("#closeButtonImageView");
        assert (mainmenuAnchor.isVisible());
    }

    @Test
    public void clickOnHowToPlayAnchor (){
        clickOn(".button-mainMenu");
        clickOn(howToPlayAnchor);
        assert (howToPlayAnchor.isVisible());
    }

    @Test
    public void textAreaEditeble (){
        String textInTextArea = howToPlayText.toString();
        clickOn(".button-mainMenu");
        clickOn(howToPlayText);
        write("Testing");
        assert (textInTextArea.equals(howToPlayText.toString()));
    }
}
