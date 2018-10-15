package Tools;

import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

public class CountDownTimer {
    private Timer timer;
    private TimerTask secondTask;
    private TimerTask countDownFinishedTask;
    private int timeLeft;

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

    public void startCountDown(int sec){
        timer = new Timer();
        timeLeft = sec;
        timer.scheduleAtFixedRate(secondTask,1000,1000);
        timer.schedule(countDownFinishedTask,sec*1000);
    }
    private void countDownFinished(){
        System.out.println("KLAR");
        timer.cancel();
    }
    private void secondHasPassed(){
        if(timeLeft>0){
            timeLeft--;
        }
        System.out.println(timeLeft);
    }
}
