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
import javafx.scene.input.MouseEvent;
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
        CanvasController canvasController = new CanvasController(canvas);
        PaintingView paintingView = new PaintingView(canvasController);

        root.getChildren().add(paintingView);


        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();
    }
}

