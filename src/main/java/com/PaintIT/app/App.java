
package com.PaintIT.app;

import MainMenu.*;
import ViewObjects.TileBoard;
import ViewObjects.TileBoardController;
import ViewObjects.TileSlot;
import WordAndGuess.WordHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
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

        WordHandler wordHandler = new WordHandler();
        wordHandler.createRandomWord();
        wordHandler.createRandomTiles(wordHandler.getCurrentWord());


        ArrayList<Character> tempTiles = wordHandler.getGuessLogic().getAvailableTiles();
        TileBoard tileBoard = new TileBoard(tempTiles);
        TileBoardController tileBoardController = new TileBoardController(wordHandler,tileBoard);


        Scene scene = new Scene(tileBoard);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}


