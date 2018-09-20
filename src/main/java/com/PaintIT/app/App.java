package com.PaintIT.app;

import Canvas.*;
import Tools.Brush;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
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

public class App extends Application
{
    public static void main( String[] args ) {
        launch(args);

    }

    @Override
    public void start (Stage primaryStage) throws Exception{

        CanvasView canvas = new CanvasView();
        CanvasController canvasController = new CanvasController(canvas);
        PaintingView paintingView = new PaintingView(canvasController);

        primaryStage.setTitle("PainIT");

        Group root = new Group();

        root.getChildren().add(paintingView);

        paintingView.setRadius(10);
        paintingView.setColor(Color.BLACK);

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}