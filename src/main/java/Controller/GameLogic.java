package Controller;

import Model.CanvasModel;
import Model.Game.HighScoreList;
import Model.Game.Score;
import Views.Components.CanvasView;
import Model.WordAndGuess.GuessLogic;
import Model.WordAndGuess.Word;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class GameLogic {

    private String highScoreUrl = "highscores.txt";

    @Setter @Getter private CanvasModel currentPainting;

    @Getter private  GuessLogic guessLogic;

    public GameLogic(){
        guessLogic = new GuessLogic();
    }

    public Word getCurrentWord() {
        return guessLogic.getCurrentWord();
    }

    public void setCurrentWord(Word word){
        guessLogic.setCurrentWord(word);

    }
    public List<Word> getPossibleWords(){return guessLogic.getPossibleWords();}

    public void newGame() {
        guessLogic = new GuessLogic();
    }

    public List<Score> getHighScores() {
        // Open file from backend
        // Need to use InputStream, as the JAR executable will have trouble to read from file otherwise
        List<Score> highScores = new ArrayList<>();
        // Prepare to read from backend
        try {
            String highScorePath = highScoreUrl;
            Scanner sc = new Scanner(new File(highScorePath));

            while(sc.hasNextLine()) {
                // A line is formatted as "teamName:streak"
                String currentWord = sc.nextLine();

                String[] score = currentWord.split(":");
                // Team name is the string before ':' in the parsed line
                String teamName = score[0];
                // Streak is the string after ':' in the parsed line
                System.out.println(teamName);
                System.out.println(score[1]);
                int streak = Integer.valueOf(score[1]);
                // Got our information, create a Score and add it to high score list!
                Score tmpScore = new Score(teamName, streak);

                highScores.add(tmpScore);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Looped over all lines, return what we got back!
        return highScores;
    }

    public void saveScore(String teamName, int teamStreak) {
        // only save if there is a team registered!

            // Get current streak
            Score currentScore = new Score(teamName, teamStreak);

            HighScoreList highScoreList = new HighScoreList(getHighScores());
            // Try to add new streak to high score list. add() always returns an updated list
            HighScoreList newHighScores = highScoreList.add(currentScore);

            String highScoreListString = newHighScores.getFormattedString();
            try {
                String highScorePath = highScoreUrl;
                // Write the updates list to backend!
                Files.write(Paths.get(highScorePath), (highScoreListString).getBytes(), StandardOpenOption.WRITE);
            } catch (IOException e) {
                e.printStackTrace();
            }


    }

    public void setupHighScores() {
        // Check if highscores.txt exists or not. If not, create it!
        File file = new File(highScoreUrl);
        try {
            if (file.createNewFile()) {
                System.out.println("Created new high score file!");
            } else {
                System.out.println("Did not create new high score file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
