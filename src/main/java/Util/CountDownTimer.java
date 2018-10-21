package Util;

import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;

/**Wrapper class for java.util.Timer. It functionality is to countdown and notfify an observer for every second that has passed.
 *
 */
public class CountDownTimer {
    private Timer timer;
    private TimerTask secondTask;
    private TimerTask countDownFinishedTask;
    private int secondsLeft;
    private CountDownUser countDownUser;

    /**
     * Instantiates the TimerTasks for the Timer.
     */
    public CountDownTimer(){
        //initTimerTask();
        //initCountDownFinishedTask();
    }

    private void initCountDownFinishedTask() {
        countDownFinishedTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(CountDownTimer.this::countDownFinished);
            }
        };
    }

    private void initTimerTask() {
        secondTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(CountDownTimer.this::secondHasPassed);
            }
        };
    }

    /**Starts a timer that counts down.
     *@param sec the amount of seconds to count down from
     *@param countDownUser the observer that gets notified when a second has passed.
     */
    public void startCountDown(int sec,CountDownUser countDownUser){
        initTimerTask();
        initCountDownFinishedTask();
        this.countDownUser = countDownUser;
        timer = new Timer();
        secondsLeft = sec;
        timer.scheduleAtFixedRate(secondTask,1000,1000);
        timer.schedule(countDownFinishedTask,sec*1000);
    }

    public void resetTimer() {
        timer.cancel();
    }
    /** Notifies observer when the timer has finished
     *
     */

    private void countDownFinished(){
        countDownUser.handleTimerFinished();
        timer.cancel();
    }

    /** Notifies observer when a second has passed
     *
     */
    private void secondHasPassed(){
        if(secondsLeft >0){
            secondsLeft--;
        }
       countDownUser.handleSecondPassed(secondsLeft);
    }
}
