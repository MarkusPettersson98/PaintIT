package com.PaintIT.app;

import Canvas.*;
import Tools.Brush;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {
    public static void main( String[] args ) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello World!");

        Group root = new Group();
        CanvasView canvas = new CanvasView();

        root.getChildren().add(canvas);

        Brush brush = new Brush(Color.CORAL);
        CanvasController canvasController = new CanvasController(canvas);

        // Brush settings n stuff
        brush.setRadius(40);
        brush.addObserver(canvasController);
        brush.apply(100,100);
        //System.out.println(canvasController.toString());


        primaryStage.setScene(new Scene(root, 300, 300));

        System.out.println(canvasController.toString());

        primaryStage.show();
    }
}

