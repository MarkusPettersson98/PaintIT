package Views.Components;

import Controller.TopController;
import Model.Game.Team;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class TileBoardViewTest extends ApplicationTest {

    TileBoardView tileBoardView;
    TopController topController;

    @Before
    public void setUp (){
        topController = new TopController();
        Team team = new Team ("Test1", "Test2");
        topController.addTeam(team);
        topController.setCurrentWord(topController.getPossibleWords().get(0));
        tileBoardView = new TileBoardView(topController);
    }


    @Test
    public void timerFinishedTest (){
        tileBoardView.handleTimerFinished();
        assert (topController.getGameOver());
    }

}
