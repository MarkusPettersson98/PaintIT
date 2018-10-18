package com.PaintIT.app;


import Controller.TopController;
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

    TopController topController;

    @Override
    public void start (Stage primaryStage) throws Exception {
        primaryStage.setTitle("PainIT");

        this.topController = new TopController();

        Scene scene = new Scene(this.topController.getCurrentPane());
        this.topController.show(MainMenuView.class.getSimpleName());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        this.topController.saveScore();
        System.out.println(topController.getHighScores());
    }

}