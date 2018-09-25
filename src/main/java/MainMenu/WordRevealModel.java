package MainMenu;

public class WordRevealModel {

    int timer = 5;

    public void startTimer() throws InterruptedException{
        Thread thread = new Thread();

        for (int i = 0; i < 5; i++) {
            thread.sleep(1000);
        }
    }

    public void setTimer(int timer){
        this.timer = timer;
    }

    public int getTimer(){
       return timer;
    }
}
