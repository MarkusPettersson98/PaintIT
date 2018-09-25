package com.PaintIT.app;

import ViewObjects.TileBoard;
import ViewObjects.TileBoardController;
import WordAndGuess.GuessLogic;
import WordAndGuess.Tile;
import WordAndGuess.WordHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;


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
        TopController topController = new TopController();

        GuessLogic guessLogic = new GuessLogic();

        ArrayList<Tile> tempTiles = guessLogic.getAvailableTiles();
        TileBoard tileBoard = new TileBoard(guessLogic, tempTiles);
        TileBoardController tileBoardController = new TileBoardController(guessLogic,tileBoard);


        Scene scene = new Scene(topController.getCurrentView());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}