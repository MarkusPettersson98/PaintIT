package com.PaintIT.app;


import Game.GameSession;
import Views.MainMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Initilizes the entire program.
 *
 */
public class App extends Application {
    public static void main( String[] args ) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception{
        primaryStage.setTitle("PainIT");

        GameSession gameSession = new GameSession();

        Scene scene = new Scene(gameSession.getCurrentPane());
        gameSession.show(MainMenuView.class.getSimpleName());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}