package com.PaintIT.app;

import MainMenu.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
        primaryStage.setTitle("PainIT");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenuView.fxml"));
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

