package com.PaintIT.app;

import MainMenu.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;


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

        //MainMenuView mainMenuView = new MainMenuView();
        //GameSetupView gameSetupView = new GameSetupView();
        WordRevealView wordRevealView = new WordRevealView();
        primaryStage.setTitle("PainIT");
        Group root = new Group();
        //root.getChildren().add(mainMenuView);
        //root.getChildren().add(gameSetupView);
        root.getChildren().add(wordRevealView);
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

