package com.PaintIT.app;

import Canvas.*;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
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
        System.out.println( "Hello World!" );         

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox testVbox = new VBox();
        ViewObjects.TilePane tilePane = new ViewObjects.TilePane();

        primaryStage.setScene(new Scene(tilePane));
        primaryStage.setTitle("Arons Badboy test");

        primaryStage.setMaxWidth(1220);
        primaryStage.setMaxHeight(840);


        primaryStage.show();
        tilePane.getChildren().add(new Label("KINGEN"));
    }
}

