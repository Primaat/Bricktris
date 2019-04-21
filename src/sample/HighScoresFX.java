package sample;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class HighScoresFX implements StandardFX{
    /**
     * Class maneging all high scores and names
     */
    // the name of the current player
    private String playerName;

    // the current high score
    private int highScore;

    // the current best time
    private String scoreModeHighScore;

    // a Tree map for all modes except score attack mode containing the high scores
    private static Map<Integer, String> highScores = new TreeMap<>(new Comparator<Integer>() {
        public int compare(Integer score1, Integer score2) {
            return (score2.compareTo(score1));
        }
    });

    // a Tree map for the best times from the score attack mode
    private static Map<String, String> scoreModeHighScores = new TreeMap<>();

    public HighScoresFX() {
    }

    public Map<Integer, String> getHighScores() {
        return highScores;
    }

    public void setHighScoreName(int playerScore, String playerName){
        // if the player forgets to enter a name with their high score, the game sets the name to "JOHNDOE"
        if(playerName == null || playerName.equals("???") || playerName.equals("")){
            highScores.put(playerScore, "JOHNDOE");
        }
        else{
            highScores.put(playerScore, playerName);
        }
        rw.writeFile(highScores);
    }

    public void setHighScore(int highScore) {
        // sets the highscore with "???" as the player name
        this.highScore = highScore;
        highScores.put(highScore, "???");
    }

    public Map<String, String> getScoreModeHighScores() {
        return scoreModeHighScores;
    }

    public void setScoreModeHighScoreName(String playerTime, String playerName){
        // if the player forgets to enter a name with their high score, the game sets the name to "JOHNDOE"

        if(playerTime != null || !playerTime.equals("00:00")){
            System.out.println("PLAYERNAME IS: " + playerName + "\n" + "PlayerTime is: " + playerTime);
            scoreModeHighScores.put(playerTime, playerName);
        }
        else if(playerName == null || playerName.equals("???") || playerName.equals("")){
            scoreModeHighScores.put(playerTime, "JOHNDOE");
        }
        rw.writeFile(scoreModeHighScores);
    }

    public void setScoreModeHighScore(String bestTime) {
        this.scoreModeHighScore = bestTime;
        scoreModeHighScores.put(scoreModeHighScore, "???");
    }

    public void loadModeHighScores(){
        /**
         * loads the relevant score list for the selected mode
         */
        if(mode.modeType.equals("Score")){
            System.out.println("Loaded score mode highscores!");
            scoreModeHighScores.clear();
            rw.readFile();
            scoreModeHighScores = rw.getScoreModeScoreMap();
        }
        else{
            System.out.println("Loaded highscores!");
            highScores.clear();
            rw.readFile();
            highScores = rw.getScoreMap();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
