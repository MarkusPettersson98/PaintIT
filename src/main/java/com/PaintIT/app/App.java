
package com.PaintIT.app;

import MainMenu.*;
import ViewObjects.TileBoard;
import ViewObjects.TileSlot;
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

    /*@Override
    public void start (Stage primaryStage) throws Exception{
        primaryStage.setTitle("PainIT");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenuView.fxml"));

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    */
    @Override
    public void start (Stage primaryStage) throws Exception{
        primaryStage.setTitle("PainIT");
        TileBoard tileBoard = new TileBoard();
        TileSlot tileSlot = new TileSlot();
        
        Scene scene = new Scene(tileSlot);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}


