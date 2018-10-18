package com.PaintIT.app;


import Controller.GameSession;
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

    GameSession gameSession;

    @Override
    public void start (Stage primaryStage) throws Exception {
        primaryStage.setTitle("PainIT");

        this.gameSession = new GameSession();

        Scene scene = new Scene(this.gameSession.getCurrentPane());
        this.gameSession.show(MainMenuView.class.getSimpleName());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        this.gameSession.saveScore();
        System.out.println(gameSession.getHighScores());
    }

}