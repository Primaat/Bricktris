package sample;

public class ModeValuesFX implements StandardFX{
    /**
     * Keeps track of used mode values used during game play
     */

    // delay in millis of downward movement of a shape
    double downDelay;

    // delay in millis of time before s shape locks when on another shape of bottom of the board
    double lockDelay;

    // starting score
    int score;

    // starting time of the timer
    int timerTime;

    // is a level box necessary? 0 is no, 1 is yes
    int levelBox;

    // does the timer count down? 0 is no, 1 is yes
    int countDown;

    // does the score add or subtract? 0 is subtract, 1 is add
    int scoreAddition;


    public ModeValuesFX() {
    }

    public void setNormalMode(double speed){
        /**
         * (re)sets the normal mode values
         */
        downDelay = downSpeed - (speed * 60.0); // a delay value for a down movement timer
        lockDelay = lockSpeed - (speed * 20.0); // a delay value for a lock shape timer
        score = 0;
        scoring.setTotalScore(score);
        timerTime = 0;
        levelBox = 1;
        countDown = 0;
        scoreAddition = 1;
        scoring.setAddition(scoreAddition);
    }

    public void setTimeAttackMode(int minutes){
        /**
         * (re)sets the time attack mode values
         */
        downDelay = 200;
        lockDelay = 800;
        score = 0;
        scoring.setTotalScore(score);
        timerTime = minutes;
        levelBox = 0;
        countDown = 1;
        scoreAddition = 1;
        scoring.setAddition(scoreAddition);
    }

    public void setScoreAttackMode(int points){
        /**
         * (re)sets the Score attack mode values
         */
        downDelay = 200;
        lockDelay = 800;
        timerTime = 0;
        score = points;
        scoring.setTotalScore(score);
        levelBox = 0;
        countDown = 0;
        scoreAddition = 0;
        scoring.setAddition(scoreAddition);
    }

    public double getDownDelay() {
        return downDelay;
    }

    public double getLockDelay() {
        return lockDelay;
    }

    public int getScore() {
        return score;
    }

    public int getTimerTime() {
        return timerTime;
    }

    public int hasLevelBox() {
        return levelBox;
    }

    public int isCountDown() {
        return countDown;
    }

    public int isScoreAddition() {
        return scoreAddition;
    }

    @Override
    public String toString() {
        return "downDelay" + downDelay + "\n" +
        "lockDelay" + lockDelay + "\n" +
        "timerTime" + timerTime + "\n" +
        "score" + score + "\n" +
        "levelBox" + levelBox + "\n" +
        "countDown" + countDown + "\n" +
        "scoreAddition" + scoreAddition;
    }
}
