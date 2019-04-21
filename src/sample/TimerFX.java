package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TimerFX extends Text implements StandardFX {
    /**
     * A timer for the game
     */
    // generic timer
    Timeline timer;
    int minutes;
    int seconds = 0;


    public TimerFX(int isCountDownTimer) {
        /**
         *selector sets the ta up or down timer
         */
        this.setText(String.format("%02d :%02d", minutes, seconds));
        if(isCountDownTimer == 1){
            countDownTimer();
        }
        else{
            countUpTimer();
        }
    }

    public void countUpTimer(){
        /**
         * Creates a timer that counts up
         */
        this.minutes = modeValues.getTimerTime();
        timer = new Timeline();
        timer.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            if(seconds == 59){
                seconds = 0;
                minutes++;
            }
            else{
                seconds++;
            }
            this.setText(String.format("%02d :%02d", minutes, seconds));
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    public void countDownTimer(){
        /**
         * Creates a timer that counts down
         */
        this.minutes = modeValues.getTimerTime();
        timer = new Timeline();
        timer.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            if(seconds == 0){
                seconds = 59;
                minutes--;
            }
            else{
                seconds--;
            }
            this.setText(String.format("%02d :%02d", minutes, seconds));
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }
    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void stopTimer(){
        timer.stop();
    }

    public void startTimer(){
        timer.play();
    }

    public void pauseTimer(){
        timer.pause();
    }
}
