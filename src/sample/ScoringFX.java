package sample;

public class ScoringFX implements StandardFX{
    /**
     * Class handles the scoring during gameplay
     */
    // the points the shape starts with
    static int startingShapePoints;

    // total score
    int totalScore;

    // current level bonus
    int levelBonus;

    // lines to be cleared
    int linesToClear;

    // the score gained for the cleared lines
    int linesClearedScore;

    int currentLevel;

    // the cleared lines this level
    int currentLevelClearedLines;

    // total amount of cleared lines this game
    int totalLinesCleared;

    // the total scored points during a combo
    int comboScore;

    // bonus points gained from a combo
    int comboBonus;

    // does the game add or subtract points
    int addition; // 1 is addition, 0 is subtraction

    public ScoringFX() {
        resetValues();
    }

    public void levelUp(){
        /**
         * Levels up when the conditions are met
         */
        if(currentLevelClearedLines >= linesToClear){
            currentLevel++;
            if(currentLevel == 10){
                sounds.playVictoryClip();
            }
            linesToClear += 5;
            levelBonus += 25;
            currentLevelClearedLines = 0;
        }
    }

    public void calculateLineScore(int linesCleared){
        /**
         * This method keeps track of the scores in the game
         */
        int scoredPoints = 0;
        switch(linesCleared){
            case 1:
                scoredPoints = 100;
                break;
            case 2:
                scoredPoints = 250;
                break;
            case 3:
                scoredPoints = 500;
                break;
            case 4:
                scoredPoints = 1000;
                break;
        }
        if(addition == 1){
            linesClearedScore = (scoredPoints * levelBonus) / 100;
            totalScore += (scoredPoints * levelBonus) / 100;
        }
        else{
            linesClearedScore = (scoredPoints * levelBonus) / 100;
            totalScore -= (scoredPoints * levelBonus) / 100;
        }
        comboScore += (scoredPoints * levelBonus) / 100;
    }

    public void addDropPointToTotal(){
        if(addition == 1){
            totalScore += (getStartingShapePoints() * levelBonus) / 100;
        }
        else{
            totalScore -= (getStartingShapePoints() * levelBonus) / 100;
        }

        System.out.println("TotalScore: " + totalScore );
    }

    public void calculateComboScore(int combo){
        /**
         * Calculates the combo score based on the @int combo
         */
        for(int i = 0; i < combo; i++){
            comboBonus = (comboBonus * 110)/100;
        }
        if(addition == 1){
            totalScore -= comboScore;
            comboScore = (comboScore * comboBonus) / 100;
            totalScore += comboScore;
        }
        else{
            totalScore += comboScore;
            comboScore = (comboScore * comboBonus) / 100;
            totalScore -= comboScore;
        }
        comboScore = 0;
        comboBonus = 100;
    }

    public int getComboScore(){
        return comboScore;
    }


    public int getStartingShapePoints() {
        return startingShapePoints;
    }

    public void adjustStartingShapePoints(){
        // subtracts 2 point for every downward step op the shape
        startingShapePoints = startingShapePoints - 2;
    }

    public void resetStartingShapePoints() {
        this.startingShapePoints = 40;
    }

    public int getTotalScore() {
        if(totalScore < 0){
            totalScore = 0;
        }
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getLinesToClear() {
        return linesToClear;
    }

    public int getLinesClearedScore(){ return linesClearedScore;}

    public int getCurrentLevel() {
        return currentLevel;    }

    public int getCurrentLevelClearedLines() {
        return currentLevelClearedLines;
    }

    public void setCurrentLevelClearedLines(int currentLevelClearedLines) {
        this.currentLevelClearedLines += currentLevelClearedLines;
    }

    public int getTotalLinesCleared() {
        return totalLinesCleared;
    }

    public void setTotalLinesCleared(int linesCleared) {
        this.totalLinesCleared += linesCleared;
    }

    public void setAddition(int plusMinus){
        addition = plusMinus;
    }

    public void resetValues(){
        /**
         * resets all values at the start of the game
         */
        startingShapePoints = 40;
        totalScore = 0;
        levelBonus = 100;
        linesToClear = 10;
        linesClearedScore = 0;
        currentLevel = 0;
        currentLevelClearedLines = 0;
        totalLinesCleared = 0;
        comboScore = 0;
        comboBonus = 100;
        addition = 1; // 1 is addition, 0 is subtraction
    }
}
