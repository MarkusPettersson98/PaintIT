package Tools;

import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class CountDownTimer {
    private Timer timer;
    private TimerTask secondTask;
    private TimerTask countDownFinishedTask;
    private int secondsLeft;
    private CountDownUser countDownUser;

    public CountDownTimer(){
        secondTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(CountDownTimer.this::secondHasPassed);
            }
        };
        countDownFinishedTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(CountDownTimer.this::countDownFinished);
            }
        };
    }

    public void startCountDown(int sec,CountDownUser countDownUser){
        this.countDownUser = countDownUser;
        timer = new Timer();
        secondsLeft = sec;
        timer.scheduleAtFixedRate(secondTask,1000,1000);
        timer.schedule(countDownFinishedTask,sec*1000);
    }
    private void countDownFinished(){
        countDownUser.handleTimerFinished();
        timer.cancel();
    }
    private void secondHasPassed(){
        if(secondsLeft >0){
            secondsLeft--;
        }
       countDownUser.handleSecondPassed(secondsLeft);
    }
}
