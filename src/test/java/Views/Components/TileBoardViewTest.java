package Views.Components;

import Controller.TopController;
import Model.Game.Team;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class TileBoardViewTest extends ApplicationTest {

    TileBoardView tileBoardView;
    TopController topController;
    Label countDownLbl;

    @Before
    public void setUp (){
        topController = new TopController();
        Team team = new Team ("Test1", "Test2");
        topController.addTeam(team);
        topController.setCurrentWord(topController.getPossibleWords().get(0));
        tileBoardView = new TileBoardView(topController);
        countDownLbl = tileBoardView.countDownLbl;
        topController.resetTimer();
    }


    @Test
    public void timerFinishedTest (){
        tileBoardView.handleTimerFinished();
        assert (topController.getGameOver());
    }

    @Test
    public void labelTimeLeft (){
        tileBoardView.handleSecondPassed(20);
        String timeLeftText = countDownLbl.getText();
        String expectedText = " Time left: 20";
        assert (timeLeftText.equals(expectedText));
    }

    @Test
    public void labelRedTimeLeft (){
        tileBoardView.handleSecondPassed(5);
        assert (countDownLbl.getTextFill().equals(Paint.valueOf("red")));
    }

}
