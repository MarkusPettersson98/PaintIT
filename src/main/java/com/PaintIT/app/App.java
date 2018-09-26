package com.PaintIT.app;

import Game.GameSession;
import Game.Player;
import Game.Team;
import Views.MainMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Hello world!
 *
 */
public class App extends Application
{
    public static void main( String[] args )
    {
        launch(args);

    }

    @Override
    public void start (Stage primaryStage) throws Exception{
        primaryStage.setTitle("PainIT");
        TopController.getInstance();
        TopController.show(MainMenuView.class.getSimpleName());

        GameSession gameSession = GameSession.getInstance();
        gameSession.addTeam(new Team( new Player("Markus"),
                                    new Player("Robert"),
                                            "Besegrade"));

        Scene scene = new Scene(TopController.getCurrentView());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}