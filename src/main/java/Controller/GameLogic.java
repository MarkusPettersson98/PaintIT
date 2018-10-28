package Controller;

import Model.Canvas.CanvasModel;
import Model.Game.HighScoreList;
import Model.Game.Score;
import Model.WordAndGuess.GuessLogic;
import Model.WordAndGuess.Word;
import Model.WordAndGuess.Word.Difficulty;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

class GameLogic {

    private String highScoreUrl = "src/main/resources/highscores.txt";

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
        final HighScoreList highScores = new HighScoreList();
        // Prepare to read from backend
        try {
            final String highScorePath = highScoreUrl;
            final Scanner sc = new Scanner(new File(highScorePath));

            while(sc.hasNextLine()) {
                // A line is formatted as "teamName:streak"
                final String currentWord = sc.nextLine();
                final String[] score = currentWord.split(":");
                if(score.length != 2) {
                    // String was not parsed correctly, do nothing
                } else {
                    // Team name is the string before ':' in the parsed line
                    final String teamName = score[0];
                    // Streak is the string after ':' in the parsed line
                    final int streak = Integer.parseInt(score[1]);
                    // Got our information, create a Score and add it to high score list!
                    final Score tmpScore = new Score(teamName, streak);
                    highScores.add(tmpScore);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Looped over all lines, return what we got back!
        return highScores.getHighScores();
    }

    public int getPoints(Difficulty difficulty) {
        final int easyPoints = 1;
        final int mediumPoints = 2;
        final int hardPoints = 3;

        switch(difficulty) {
            case EASY: return easyPoints;
            case MEDIUM: return mediumPoints;
            case HARD: return hardPoints;
            default: return 0;
        }
    }

    public void saveScore(String teamName, int teamStreak) {
        // only save if there is a team registered!

            // Get current streak
            final Score currentScore = new Score(teamName, teamStreak);

            final HighScoreList highScoreList = new HighScoreList(getHighScores());
            // Try to add new streak to high score list. add() always returns an updated list
            final HighScoreList newHighScores = highScoreList.add(currentScore);

            final String highScoreListString = newHighScores.getFormattedString();
            try {
                final String highScorePath = highScoreUrl;
                // Write the updates list to backend!
                Files.write(Paths.get(highScorePath), (highScoreListString).getBytes(), StandardOpenOption.WRITE);
            } catch (IOException e) {
                e.printStackTrace();
            }


    }

    public void setupHighScores() {
        // Check if highscores.txt exists or not. If not, create it!
        final File file = new File(highScoreUrl);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
