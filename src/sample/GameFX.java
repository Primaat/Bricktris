package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class GameFX implements StandardFX{
    // the game effects that are stacked on top of the game area
    private GameEffectsFX gameEffectsFX = new GameEffectsFX();

    // a box called when one wants to quit or fgo back to the title screen
    private ConfirmBox confirmBox = new ConfirmBox();

    // a timeline for the drop down movement of a shape
    protected Timeline downTimeLine = new Timeline();

    // a timeline for locking a shape into place after a certain amount of time
    protected Timeline lockTimeLine = new Timeline();

    // one Vbox containing the game board
    private VBox gameBoard;

    // VBox containing hboxes that contain the next shapes to be added to the gameBoard
    private VBox previewBox;

    // instance of the SwapBox class
    private SwapBox swapBox = new SwapBox();

    // a box containing a swapped shape
    private VBox swapBoxVBox;

    // Has the current shape been swapped already?
    private boolean swappable = true;

    // Does the swapBox have a shape inside?
    private boolean hasShape = false;

    // a pane containing aligned controls on the left side of the gameBoard
    private AnchorPane anchorLeft;

    // a pane containing aligned controls on the right side of the gameBoard
    private AnchorPane anchorRight;

    // a list containing a 'grid' of the board
    private List<int[]> board = new ArrayList<>();

    // the border pane containing everything visible on screen
    private BorderPane mainPane;

    // the main scene of the game containing all panes and the subscene
    private Scene mainScene;

    // generic rectangle
    protected Rectangle rect;
    final static int rectangleSize = 45; // rectangle size is 10% of the width of the game area
    final static int gamePaneWidth = 450; // the width of the game area
    final static int gamePaneHeight = 900; // the height of the game area

    // initialised random shape creator
    private RandomShapeCreator randomShapes = new RandomShapeCreator(); // class that creates shapes

    // a list containing the 4 possible shapes/ matrices of the current shape on the board
    private List<int[][]> shapeList;

    private EventHandler<KeyEvent> eventHandler;

    // the current shape on the game board
    private Bricktrisminoes currentShape;

    // the color of the current shape
    private Color currentShapeColor;

    // The current matrix of the current shape
    private int[][] currentShapeMatrix;

    // the number of the shape from the shape list
    private int shapeNum = 0;

    // starting position of the shape matrix on the board on the x axis
    private int shapePosX = 3;

    // starting position of the shape matrix on the board on the y axis
    private int shapePosY = 0;

    // -1 is out of bounds on the left, 1 is on the right. Used to shift when rotating when partially out of bounds
    private int outOfBoundCols = 0;

    // 1 is one out of bound row. Used to shift when rotating when partially out of bounds
    private int outOfBoundRows = 0;

    // the amount of cols to shift to the right to be able to rotate the shape.
    private int rotateCorrectionRight = 0;

    // the amount of cols to shift to the left to be able to rotate the shape.
    private int rotateCorrectionLeft = 0;

    // the amount of rows to shift on the y axis to be able to rotate the shape.
    private int rotateCorrectionDown = 0;

    // the length of the shape matrix array
    private int currentShapeMatrixLength;

    // rows on the board
    private final int ROWS = 20;

    // columns on the board
    private final int COLS = 10;

    // is the game paused?
    private boolean pausedGame = false;

    // is a lockTimeline running?
    private boolean lockTimeLineRunning = false;

    // counts how many lines have been cleared in a row
    private int comBoCounter = 0;

    // shows the total score
    private Text score;

    // shows the amount of lines cleared and to clear amount of lines
    private Text lines;

    // shows the current level
    private Text level;

    // the current level
    private int currentLevel;

    // the stack that contains the game and all the effects
    private StackPane mainStack;

    private BorderPane borderPane;

    // the scene where the gameplay takes place
    private SubScene subScene;

    // generic timer
    private TimerFX timer;

    // a timeline to check a victory condition
    private Timeline victoryChecker;

    // random number for a shape style selection
    private Random rand = new Random();
    private int randNum = rand.nextInt(2);


    public GameFX() {
        // stop the title music first
        sounds.stopMusic();
        mainPane = new BorderPane();
        mainStack = new StackPane();
        mainStack.setStyle("-fx-background-color: transparent;");

        // empty area above the game board
        Label topFeed = new Label();
        topFeed.setPrefHeight(100);
        mainPane.setTop(topFeed);

        // empty area below the game board

        Label bottomFeed = new Label();
        bottomFeed.setPrefHeight(100);
        mainPane.setBottom(bottomFeed);

        anchorLeft = new AnchorPane();
        anchorLeft.setStyle("-fx-background-color: transparent ;");
        anchorLeft.setPrefWidth((WIDTH - gamePaneWidth) / 2);

        // text label above the swapbox
        Label swapText = new Label("Swap");
        swapText.setStyle(minorLabelStyle);

        anchorLeft.getChildren().add(swapText);
        anchorLeft.setTopAnchor(swapText, 0.0);
        anchorLeft.setRightAnchor(swapText, 50.0);

        swapBoxVBox = swapBox.getEmptySwapBox();
        anchorLeft.getChildren().add(swapBoxVBox);
        anchorLeft.setTopAnchor(swapBoxVBox, 30.0);
        anchorLeft.setRightAnchor(swapBoxVBox,10.0);

        VBox linesLevelBox = setLinesLevelBox();
        anchorLeft.getChildren().addAll(linesLevelBox);
        anchorLeft.setBottomAnchor(linesLevelBox, 10.0);
        anchorLeft.setRightAnchor(linesLevelBox, 10.0);

        anchorRight = new AnchorPane();
        anchorRight.setStyle("-fx-background-color: transparent ;");
        anchorRight.setPrefWidth((WIDTH - gamePaneWidth) / 2);

        // text label above the next shapes box
        Label nextText = new Label("Next");
        nextText.setLineSpacing(10);
        nextText.setStyle(minorLabelStyle);

        anchorRight.getChildren().add(nextText);
        anchorRight.setTopAnchor(nextText, 0.0);
        anchorRight.setLeftAnchor(nextText, 25.0);

        previewBox = randomShapes.getPreviewBox();
        anchorRight.getChildren().add(previewBox);
        anchorRight.setTopAnchor(previewBox, 0.0);
        anchorRight.setLeftAnchor(previewBox, 10.0);

        VBox timerScoreBox = setTimerScoreBox();
        anchorRight.getChildren().add(timerScoreBox);
        anchorRight.setBottomAnchor(timerScoreBox, 10.0);
        anchorRight.setLeftAnchor(timerScoreBox, 10.0);

        // sets the game board
        setGameBoard();

        mainStack.getChildren().add(gameEffectsFX.getStackPane());
        subScene = new SubScene(mainStack, GAMEPANEWIDTH, GAMEPANEHEIGHT);

        mainPane.setLeft(anchorLeft);
        mainPane.setCenter(subScene);
        mainPane.setRight(anchorRight);
        mainPane.setBackground(new Background(backGrounds.getRandomBackground()));

        mainScene = new Scene(mainPane, WIDTH,HEIGHT);
        timer.pauseTimer();
        onKeyEvents();
        ready();
    }

    public void ready(){
        /**
         * let the game sleep for 2.2 seconds and play the 'ready' sound and effect after which the game starts
         */

        try{
            TimeUnit.MILLISECONDS.sleep(2200);
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
        gameEffectsFX.changeTop(-1, "ready");
        sounds.playReadyClip();
        sounds.playRandomMusic();

        // starts the timer
        timer.startTimer();

        // creates the first shape on the game board
        createShape( true, false);

        // if time attck mode was selected, start the victory checker timeline
        if(mode.modeType.equals("Time")) {
            victoryChecker.play();
        }
    }

    public VBox setTimerScoreBox(){
        /**
         * Returns a VBox containing a timer and the total score
         */
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        timer = new TimerFX(modeValues.isCountDown());
        timer.setStyle(minorTextStyle1);

        if(mode.modeType.equals("Time")){
            // Check if victory conditions are met every second.
            victoryChecker = new Timeline();
            victoryChecker.getKeyFrames().add(new KeyFrame(Duration.minutes(mode.modeTypeSelection), event -> {
                isVictory();
                //victoryChecker.stop();
            }));
            victoryChecker.setCycleCount(Animation.INDEFINITE);
        }
        // time text label
        Label timeTxt = new Label("TIME"); // text
        timeTxt.setStyle(minorLabelStyle);

        // score text label
        Label scoreTxt = new Label("SCORE");
        scoreTxt.setStyle(minorLabelStyle);

        score = new Text(scoring.getTotalScore() + ""); // shows the current score
        score.setStyle(minorTextStyle1);

        vbox.getChildren().addAll(timeTxt, timer, scoreTxt, score);
        return vbox;
    }

    public VBox setLinesLevelBox(){
        /**
         * Returns a VBox containing current and cleared lines and the current level
         */
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        Label linesTxt = new Label("LINES");
        linesTxt.setStyle(minorLabelStyle);

        // shows the remaining lines for next level or current amount of cleared lines
        lines = new Text();
        lines.setStyle(minorTextStyle1);

        //checks if the current mode has need of a level box
        if(modeValues.hasLevelBox() == 1){
            lines.setText(scoring.getCurrentLevelClearedLines() + " / " + scoring.getLinesToClear());
            Label levelTxt = new Label("Level");
            levelTxt.setStyle(minorLabelStyle);

            level = new Text();
            level.setStyle(minorTextStyle1);
            level.setText("    " + scoring.getCurrentLevel());
            vbox.getChildren().addAll(linesTxt, lines, levelTxt, level);
        }
        else{
            lines.setText(scoring.getTotalLinesCleared() + "");
            vbox.getChildren().addAll(linesTxt, lines);
        }
        return vbox;
    }


    public void setGameBoard(){
        /**
         * Sets the game board in the middle of a border pane
         */
        borderPane =  new BorderPane();
        for(int i = 0; i < 20;i++){
            board.add(new int[10]);
        }
        // create one gameBoard which will hold 20 Hboxes
        gameBoard = new VBox();
        for(int i = 0; i < ROWS; i++){
            gameBoard.getChildren().add(createHBox());
        }
        String borderPaneStyle =
                "-fx-border-color: yellow;\n" +
                "-fx-background-color: transparent;\n" +
                "-fx-border-width: 3;\n";

        borderPane.getChildren().add(gameBoard);
        borderPane.setPrefWidth(gamePaneWidth);
        borderPane.setPrefHeight(gamePaneHeight);

        borderPane.setStyle(borderPaneStyle);
        borderPane.setFocusTraversable(true);
        mainStack.getChildren().add(borderPane);
    }

    public void onKeyEvents(){
        /**
         * this method sets key events on the main scene
          */
        mainScene.setOnKeyPressed(e -> {
            borderPane.requestFocus();
            e.consume();
            if(e.getCode() == KeyCode.LEFT){
                moveLeft();
            }
            else if(e.getCode() == KeyCode.RIGHT){
                moveRight();
            }
            else if(e.getCode() == KeyCode.DOWN){
                moveDown(1);
            }
            else if(e.getCode() == KeyCode.W){
                swapShapes();
            }
        });

        eventHandler = new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                borderPane.requestFocus();
                event.consume();

                if(event.getCode() == KeyCode.A){
                    rotateCounterClockWise();
                }
                else if(event.getCode() == KeyCode.S){
                    rotateClockWise();
                }
                else if(event.getCode() == KeyCode.SPACE){
                    lockTimeLine.stop();
                    lockTimeLineRunning = false;
                    moveDown(20);
                }
                else if(event.getCode() == KeyCode.ESCAPE || event.getCode() == KeyCode.ENTER){
                    pauseGame();
                }
            }
        };
        mainScene.setOnKeyReleased(eventHandler);

    }

    public Scene getMainScene() {
        return mainScene;
    }

    public void swapShapes(){
        /**
         * This method swaps the current shape on the board with the shape in the swapBox if it has a shape,
         * otherwise it takes the shape from the board and creates a new one
         */
        if(swappable){
            // removes the shape from the board
            removeShape();

            // removes the swapbox from the board
            anchorLeft.getChildren().remove(swapBoxVBox);

            // get a new shape
            Bricktrisminoes shape = swapBox.getShape();

            // get a new swap box with the current shape in it
            swapBox = new SwapBox(currentShape);

            // put the swapbox in the vbox
            swapBoxVBox = swapBox.getSwapBox();

            // if old swapbox already had a shape, replace the current shape with the shape from the
            // swapbox else create a new shape on the board
            if( hasShape){
                currentShape = shape;
                createShape(true,true);
                swappable = false;
            }
            else{
                hasShape = true;
                createShape(true, false);
                swappable = false;
            }
            anchorLeft.getChildren().add(swapBoxVBox);
            anchorLeft.setTopAnchor(swapBoxVBox, 30.0);
            anchorLeft.setRightAnchor(swapBoxVBox,10.0);
        }
    }

    public HBox createHBox(){
        /**
         * This method creates a single HBox with COLS amount of Rectangles
         */
        HBox hbox = new HBox(); //
        // put invisible rectangles in each cell of the hbox
        for(int j = 0; j < COLS; j++){
            rect = new Rectangle(rectangleSize,rectangleSize);
            rect.setFill(Color.WHITE);
            rect.setVisible(false);
            hbox.getChildren().add(rect);
        }
        return hbox;
    }

    public void isNewShape(boolean isSwapped){
        /**
         * This method resets all relevant variables to prepare for the new shape on the board
         */
        // if the shape was not swapped the current shape is a new shape and it is swappable
        if(!isSwapped){
            currentShape = randomShapes.getShape(); // get a new shape
            swappable = true;
        }
        currentShapeColor = currentShape.getColor();

        // get the list of shapes from the current shape
        shapeList = currentShape.getShapeMatrix();

        // reset the shapeNum to 0 which is the starting shape number in the list when it arrives on the board
        shapeNum = 0;

        // reset the starting position of the shape on the X axis on the board to 3
        shapePosX = 3;

        if(shapeList.get(shapeNum).length == 4){
            shapePosY = 0;
        }
        else{
            // reset the starting position of the shape on the Y axis on the board to 0
            shapePosY = 0;
        }

        // reset the out of bound rows and cols
        outOfBoundRows = 0;
        outOfBoundCols = 0;

        // reset the rotation correction
        rotateCorrectionDown = 0;
        rotateCorrectionRight = 0;
        rotateCorrectionLeft = 0;

        // resets the starting points of a new shape on the board
        scoring.resetStartingShapePoints();

        anchorRight.getChildren().remove(previewBox);
        previewBox = randomShapes.getPreviewBox();
        anchorRight.getChildren().add(previewBox);
        anchorRight.setTopAnchor(previewBox, 30.0);
        anchorRight.setLeftAnchor(previewBox, 10.0);
    }

    public void createShape(boolean newShape, boolean isSwapped){
        /**
         * this method creates shapes. New when at the start of the game or a previous shape has been locked
         * into place or when a shape is swapped from and to the board.
         * Otherwise it re-creates a shape based on the movement or rotation of the current shape.
         */

        if(newShape){
            isNewShape(isSwapped);
            if(isGameOver()){
                stopGame();
            }
            else{
                moveDownTimer();
            }
        }
        else{
            downTimeLine.play();
        }

        lockTimeLineRunning = false;
        lockTimeLine.stop();
        currentShapeMatrix = shapeList.get(shapeNum); //
        currentShapeMatrixLength = currentShapeMatrix.length;

        // an effect for one of the styles
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0f);
        Lighting l = new Lighting();
        l.setLight(light);
        l.setSurfaceScale(5.0f);

        for(int i = 0; i < currentShapeMatrix.length; i++ ){
            HBox h = new HBox();
            if(i + shapePosY < 20){
                h = (HBox) gameBoard.getChildren().get(i + shapePosY);
            }
            for(int j = 0; j < currentShapeMatrix[0].length; j++ ){
                if(currentShapeMatrix[i][j] == 1  ){
                    if(j + shapePosX >= 0 && j + shapePosX < 10) {
                        Reflection reflection = new Reflection(adjustReflectionTopOffset(),1.0,0.20,0.20);
                        Rectangle r = (Rectangle) h.getChildren().get(j + shapePosX);
                        r.setFill(currentShapeColor);
                        if(randNum == 1){
                            r.setStroke(currentShapeColor);
                            r.setStrokeWidth(5);
                            r.setStrokeType(StrokeType.INSIDE);
                            r.setFill(Color.TRANSPARENT);
                        }
                        else if(randNum == 2){
                            reflection.setInput(l);
                            r.setFill(currentShapeColor);
                        }
                        r.setEffect(reflection);
                        r.setArcHeight(15);
                        r.setArcWidth(15);
                        r.setVisible(true);
                    }
                }
            }
        }
    }


    public double adjustReflectionTopOffset(){
        /**
         * This method compares the lowest visible rectangles in the shape matrix with the highest visible rectangles on the board
         * within the same columns and returns a double containing the Reflection Top Offset used for the reflection of
         * shapes by using the shortest distance between a shape rectangle and board rectangle.
         */
        double reflectionOffset;

        // shortest distance starts at the size of the board
        int shortestDistance = 20;

        int lowestShapeRect = 0;

        // starting column to check is shifted by outbound columns on the left of the board
        int startCol = 0;
        if(outOfBoundCols < 0){
            startCol += Math.abs(outOfBoundCols);
        }

        // ending column to check is shifted by outbound columns on the right of the board
        int endCol = currentShapeMatrixLength;
        if(outOfBoundCols > 0){
            endCol = currentShapeMatrixLength - outOfBoundCols;
        }

        // list of x coordinates of lowest visible shape rectangles
        List<Integer> shapeRectCols = new ArrayList<>();

        // list of x coordinates of highest visible board rectangles
        List<Integer> boardRectCols = new ArrayList<>();

        // find the lowest visible shape rectangle positions and put in the list
        for(int i = startCol; i < endCol; i++){
            shapeRectCols.add(getLowestShapeRect(i));
        }

        // find the highest visible board rectangles positions and put them in the list
        for(int i = 0; i < shapeRectCols.size(); i++){
            boardRectCols.add(getHighestBoardRect(i));
        }

        // flag to check if the board is empty
        boolean empty = true;

        for(int i = 0; i < shapeRectCols.size(); i++){
            if(boardRectCols.get(i) != 0 ){
                empty = false;
            }
            if(((boardRectCols.get(i) - shapeRectCols.get(i)) < shortestDistance) && boardRectCols.get(i) != 0 && shapeRectCols.get(i) != 0){
                shortestDistance = boardRectCols.get(i) - shapeRectCols.get(i);
            }
            else if(boardRectCols.get(i) == 0 && shapeRectCols.get(i) > lowestShapeRect){
                lowestShapeRect = shapeRectCols.get(i);
                shortestDistance = board.size() - lowestShapeRect;
            }
        }
        // if the boardRectCols list is empty, a reflection is calculated by the distance between the lowest row
        // on the board minus the lowest shape rectangle row. Else the reflection distance is the shortest distance.
        if(empty){
            reflectionOffset = (board.size() - lowestShapeRect - 2) * rectangleSize;
        }
        else{
            reflectionOffset = (shortestDistance - 2) * rectangleSize;
        }
        return reflectionOffset;
    }


    public int getLowestShapeRect(int checkCol){
        /**
         * Helper method to the adjustReflectionTopOffset method.
         * checks what the (next) lowest visible rectangle is in the shape matrix
         * and returns the x coordinate of that rectangle
         */
        int col = 0;
        for(int i = currentShapeMatrixLength-1; i >= 0; i--){
            for(int j = checkCol; j <= checkCol; j++){
                if(currentShapeMatrix[i][j] == 1){
                    col = shapePosY + i;
                    return col;
                }
            }
        }
        return col;
    }

    public int getHighestBoardRect(int checkCol){
        /**
         * Helper method to the adjustReflectionTopOffset method.
         * checks what the (next) highest visible rectangle is on the board
         * and returns the x coordinate of that rectangle
         */
        int correctPosX = 0;
        if(shapePosX > 0){
            correctPosX = shapePosX;
        }
        int colToCheck = checkCol + correctPosX ;
        int col = 20;
        for(int i = 0; i < board.size(); i++){
            for(int j = colToCheck; j <= colToCheck; j++){
                if(board.get(i)[j] == 1){
                    col = i;
                    return col;
                }
            }
        }
        return col;
    }

    public void pauseGame(){
        /**
         * This method checks if the game is paused / un-paused and sets the game state to the opposite state.
         */
        if(!pausedGame){
            // if the game is not paused, pause it
            downTimeLine.pause();
            timer.pauseTimer();
            sounds.pauseMusic();
            pausedGame = true;

            int answer = ConfirmBox.display("Pause Menu", "Game Paused");
            if(answer == 1){
                // close the game
                window.close();
            }
            else if(answer == 0){
                // back to the title screen
                window.setScene(titleScene);
            }
            else{
                // resume game
                downTimeLine.play();
                timer.startTimer();
                sounds.gameMusicPlayer.play();
                pausedGame = false;
            }
        }
        else{
            // if the game is paused, un-pause it
            downTimeLine.play();
            timer.startTimer();
            sounds.gameMusicPlayer.play();
            pausedGame = false;
        }
    }

    public void moveDownTimer(){
        /**
         * this method applies a downTimeLine of down movements to the active shape on the board
         */
        downTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(modeValues.getDownDelay()), ae -> moveDown(1)));
        downTimeLine.play();
    }

    public void lockShapeTimer(){
        /**
         * This method locks the shape in place after a certain time interval when it has been
         * idle at the bottom bounds of the board or other locked shapes .
         */
        downTimeLine.pause();
        lockTimeLineRunning = true;
        lockTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(modeValues.getLockDelay()), (e) -> {
            lockShape();
            lockTimeLineRunning = false;
        }));
        lockTimeLine.play();
    }

    public void lockShape(){
        /**
         * This method locks the shape into place and checks if a line / lines are to be cleared
         * after which a new shape will be created
         */
        for(int i = shapePosY; i < shapePosY + currentShapeMatrixLength; i++){
            for(int j = shapePosX; j < shapePosX + currentShapeMatrixLength; j++) {
                if ( j >= 0 && j < 10 && i < 20 && currentShapeMatrix[i-shapePosY ][j-shapePosX] == 1) {
                    board.get(i)[j] = 1;
                }
            }
        }
        sounds.playDropClip();
        gameEffectsFX.changeTop(-1, "bam");
        clearLines();
        mainScene.setOnKeyReleased(eventHandler);
        createShape(true, false);
    }


    public void clearLines(){
        /**
         * This method clears all lines where all 10 cells in a row include a '1' in the board list
         */
        // stop the locktimeline as the shape is already at the lowest it can get
        lockTimeLine.stop();

        // is cells == 110 the line will be removed
        int cells = 0;

        // how many lines are removed at the same time
        int linesCleared = 0;

        // check if there are lines to remove
        for(int i = board.size()-1; i > 0 ; i--){
            for(int j = 0; j < board.get(0).length; j++){
                if(board.get(i)[j] == 1){
                    cells++;
                }
            }
            if(cells == 10){
                linesCleared++;
                board.remove(i);
                gameBoard.getChildren(). remove(i); // the remove the row from the gameBoard
                board.add(0, new int[10]);
                gameBoard.getChildren().add(0, createHBox()); // // add a new row (hbox) to the gameBoard
                i++;
            }
            cells = 0;
        }

        // calculate and set the scoring
        currentLevel = scoring.getCurrentLevel();
        scoring.addDropPointToTotal();
        scoring.setCurrentLevelClearedLines(linesCleared);
        scoring.setTotalLinesCleared(linesCleared);
        setCombo(linesCleared);
        scoring.calculateLineScore(linesCleared);
        scoring.levelUp();

        // make sure all methods to be triggered by a level up, do so
        if(currentLevel < scoring.getCurrentLevel()){
            currentLevel = scoring.getCurrentLevel();
            randNum = rand.nextInt(3);
            sounds.playLevelupClip();
            gameEffectsFX.changeTop(-1, "levelup");
            mainPane.setBackground(new Background(backGrounds.getRandomBackground()));
        }

        // update the Text boxes with the new data
        score.setText(scoring.getTotalScore() + "");

        // if the mode has a level box set the relevant text controls
        if(modeValues.hasLevelBox() == 1){
            level.setText("    " + scoring.getCurrentLevel());
            lines.setText(scoring.getCurrentLevelClearedLines() + " / " + scoring.getLinesToClear());
        }
        else{
            lines.setText(scoring.getTotalLinesCleared() + "");
        }

        // check if the victory condition of 0 points has been met in score attack mode
        if(mode.modeType.equals("Score")){
            isVictory();
        }

    }

    public void setCombo(int linesCleared){
        /**
         * This method sets the current combo, plays a sound clip and shows the correct special effect
         * when a combo is being made or broken.
         */
        if(linesCleared == 0 && comBoCounter > 0){
            if(comBoCounter > 2){
                sounds.playComboClips(0);
                gameEffectsFX.changeTop(0, null);
                scoring.calculateComboScore(comBoCounter);
            }
            comBoCounter = 0;
        }
        else if(linesCleared > 0) {
            comBoCounter++;
            sounds.playComboClips(comBoCounter);
            gameEffectsFX.changeTop(comBoCounter, null);
        }
    }


    public void removeShape(){
        /**
         * This method 'removes' a shape (makes the current shape of rectangles invisible)
         * after every and any movement on the board
         */
        // stops the downTimeLine because a new shape is being created which needs a new downTimeLine
        downTimeLine.stop();

        // stops the lockTimeLine because the shape is being removed because of sidewards movement or rotation
        lockTimeLine.stop();
        for(int i = 0; i < currentShapeMatrix.length; i++ ){
            HBox h = new HBox();
            if(i + shapePosY < 20){
                h = (HBox) gameBoard.getChildren().get(i + shapePosY);
            }
            for(int j = 0; j < currentShapeMatrix[0].length; j++ ){
                if(currentShapeMatrix[i][j] == 1){
                    if(j + shapePosX >= 0 && j + shapePosX < 10) {
                        Rectangle r = (Rectangle) h.getChildren().get(j + shapePosX);
                        r.setFill(Color.WHITE);
                        r.setStroke(null);
                        r.setVisible(false);
                    }
                }
            }
        }
    }

    public void moveDown(int repeat){
        /**
         * This method checks if downwards movement is possible, if so it moves the shape down a row times "repeat" .
         */
        // how many times is down movement repeated
        int linesDropped = repeat;

        if(repeat == 1){
            scoring.adjustStartingShapePoints();
        }

        if(lockTimeLineRunning){
            lockTimeLine.stop();
            lockTimeLineRunning = false;
            lockShape();
        }
        else{
            while(linesDropped > 0 && canMove( 0, 1)){
                if(shapePosY < 19){
                    removeShape();
                    shapePosY++;
                    if((shapePosY + currentShapeMatrixLength - 20) >= 0 ){
                        outOfBoundRows = shapePosY + currentShapeMatrixLength - 20;
                    }
                    else{
                        outOfBoundRows = 0;
                    }
                    adjustReflectionTopOffset();
                    createShape(false, false);
                }
                linesDropped--;
            }
        }
        if(repeat > 1){
            lockShape();
            lockTimeLineRunning = false;
        }
        else if(!canMove(0,1)){
            lockShapeTimer();
        }
    }

    public void moveRight(){
        /**
         * This method checks if movement to the right is possible,
         * if so it moves the shape to the right one column.
         */
        if( canMove(1, 0)){
            if(shapePosX < 8){
                removeShape();
                shapePosX++;
                if((shapePosX + currentShapeMatrixLength - 10) >= 0){
                    outOfBoundCols = shapePosX + currentShapeMatrixLength - 10;
                }
                else{
                    outOfBoundCols = 0;
                }
                createShape(false, false);
            }
        }
    }

    public void moveLeft(){
        /**
         * This method checks if movement to the left is possible,
         * if so it moves the shape to the left one column.
         */
        if( canMove(-1, 0)){
            if(shapePosX > -2){
                removeShape();
                shapePosX--;
                if(shapePosX < 0 ){
                    outOfBoundCols = shapePosX;
                }
                else if(shapePosX < 6){
                    outOfBoundCols = 0;
                }
                createShape(false, false);
            }
        }
    }

    public boolean canMove(int directionX, int directionY){
        /**
         * This method checks if a move is possible in the proposed direction.
         * If so, true will be returned so the program can continue the move on the original matrix.
         * @param int directionX the proposed direction of the shape matrix on the x axis (left/right)
         * @param int directionY the proposed direction of the shape matrix on the y axis (down)
         */

        int proposedPosY = shapePosY + directionY; //
        int proposedPosX = shapePosX; //
        if(directionX < 0){
            proposedPosX--;
        }
        else if(directionX > 0){
            proposedPosX++;
        }
        // a copy of the to be moved shape matrix
        int[][] shapeMatrix = shapeList.get(shapeNum);

        // does the movement cause an intersection with another shape? if yes return false(can't move)
        if(intersectsShapes(shapeMatrix, proposedPosX, proposedPosY)){
            return false;
        }

        // does the movement cause an intersection with the grid bounds? if yes return false(can't move)
        if(intersectsGridBounds(shapeMatrix, proposedPosX, proposedPosY)){
            return false;
        }
        return true;
    }

    public void rotateClockWise(){
        /**
         *This method rotates a shape matrix clock wise if possible
         */

        if( canRotate("CW")){
            removeShape();
            if(shapeNum == 3 ){
                shapeNum -= 4;
            }
            shapeNum++;
            correctRotateShapePosition();
            correctOutBoundShapePosition();
            createShape(false, false);
        }
    }

    public void rotateCounterClockWise(){
        /**
         *This method rotates a matrix counter clock wise if possible
         */
        if( canRotate("CCW")){
            removeShape();
            if(shapeNum == 0){
                shapeNum += 4;
            }
            shapeNum--;
            correctRotateShapePosition();
            correctOutBoundShapePosition();
            createShape(false, false);
        }
    }

    public void correctOutBoundShapePosition(){
        /**
         * This method checks if any outbound rows or columns currently exist while rotating. if so, the current shape matrix
         * is shifted back into the visible board area by resetting the outOfBoundCols and outOfBoundRows
         */
        if(outOfBoundCols < 0){
            shapePosX = 0;
        }
        else if(outOfBoundCols > 0){
            if(currentShapeMatrixLength == 4){
                shapePosX = 6;
            }
            else{
                shapePosX = 7;
            }
        }
        if(outOfBoundRows > 0){
            if(currentShapeMatrixLength == 4){
                shapePosY = 16;
            }
            else{
                shapePosY = 17;
            }
        }
        outOfBoundRows = 0;
        outOfBoundCols = 0;
    }

    public void correctRotateShapePosition(){
        /**
         * This method shifts the shape matrix left, right or up when its trying
         * to rotate against a locked shape in the opposite direction
         */

        if(rotateCorrectionRight > rotateCorrectionLeft){
            shapePosX += rotateCorrectionRight;
        }
        else{
            shapePosX -= rotateCorrectionLeft;
        }

        shapePosY -= rotateCorrectionDown;
        rotateCorrectionLeft = 0;
        rotateCorrectionRight = 0;
        rotateCorrectionDown = 0;
    }

    public boolean canRotate(String rotateDirection){
        /**
         * This method checks if a rotation is possible in the proposed direction.
         * If so, true will be returned so the program can continue to rotate the original matrix.
         * @String rotateDirection, the direction which to rotate to. CW = clockwise / 90 degrees
         * CWW = counter clockwise / -90 degrees
         */
        int proposedPosY = shapePosY; //
        int proposedPosX = shapePosX; //
        if(outOfBoundCols < 0){
            proposedPosX = 0;
        }
        else if(outOfBoundCols > 0){
            if(currentShapeMatrixLength == 4){
                proposedPosX = 6;
            }
            else{
                proposedPosX = 7;
            }
        }
        if(outOfBoundRows > 0){
            if(currentShapeMatrixLength == 4){
                proposedPosY = 16;
            }
            else{
                proposedPosY = 17;
            }
        }

        // a copy of the shapes current form
        int shapeNumCopy = shapeNum;
        int[][] shapeMatrix;

        if(rotateDirection.equals("CW")){
            if(shapeNumCopy == 3){
                shapeNumCopy -= 4;
            }
            shapeNumCopy++;
            shapeMatrix = shapeList.get(shapeNumCopy);
        }
        else{
            if(shapeNumCopy == 0){
                shapeNumCopy += 4;
            }
            shapeNumCopy--;
            shapeMatrix = shapeList.get(shapeNumCopy);
        }
        if(intersectsShapes(shapeMatrix, proposedPosX, proposedPosY)){
            if(outOfBoundCols > 0 || outOfBoundCols < 0 || outOfBoundRows > 0){
                return false;
            }
            else if(!intersectsShapes(shapeMatrix, proposedPosX - 1, proposedPosY)){
                rotateCorrectionLeft++;
                return true;
            }
            else if(!intersectsShapes(shapeMatrix, proposedPosX + 1, proposedPosY)){
                rotateCorrectionRight++;
                return true;
            }
            else if(!intersectsShapes(shapeMatrix, proposedPosX, proposedPosY-1)){
                rotateCorrectionDown++;
                return true;
            }
            else if(!intersectsShapes(shapeMatrix, proposedPosX + 1, proposedPosY-1)){
                rotateCorrectionDown++;
                rotateCorrectionRight++;
                return true;
            }
            else if(!intersectsShapes(shapeMatrix, proposedPosX - 1, proposedPosY-1)){
                rotateCorrectionDown++;
                rotateCorrectionLeft++;
                return true;
            }
            return false;
        }
        if(intersectsGridBounds(shapeMatrix, proposedPosX, proposedPosY)){
            return false;
        }
        return true;
    }

        public boolean intersectsShapes(int[][] matrix, int proposedPosX, int proposedPosY){
            /**
             * This method first checks if an active shape intersects the board bounds, if so it returns 'true'
             * and movement to the proposed direction wont be possible. If 'false', the method continues to check
             * if the active shape intersects with any locked shape on the board. If true, movement to the
             * proposed direction wont be possible. Returns 'False' if no intersection takes place.
             * @int[][] matrix, the copy of the original shape matrix to test with if its about to go out of bounds
             * @int boardPosX the proposed X position of the shape matrix on the board matrix
             * @int boardPosY the proposed Y position of the shape matrix on the board matrix
             */

            for(int i = proposedPosY; i < proposedPosY + currentShapeMatrixLength; i++){
                for(int j = proposedPosX; j < proposedPosX + currentShapeMatrixLength; j++){
                    if(j >= 0 && j < 10 && i < 20 && board.get(i)[j] == 1 && matrix[i-proposedPosY][j-proposedPosX] == 1 ){
                        return true;
                    }
                }
            }
            return false;
        }
    public boolean intersectsGridBounds(int[][] matrix, int proposedPosX, int proposedPosY) {
        /**
         * This method checks if the shape is about to go out of bounds of the Board Matrix
         * ( not the grid as we have to realize the shape matrix could have empty columns/rows on any side )
         * @int[][] matrix, the copy of the original shape matrix to test with if its about to go out of bounds
         * @int boardPosX the proposed X position of the shape matrix on the board matrix
         * @int boardPosY the proposed Y position of the shape matrix on the board matrix
         */

        boolean intersects = false;
        if ( currentShapeMatrixLength == 4 && (proposedPosX < -2 || proposedPosX > 8 || proposedPosY > 18)) {
            return true;
        }
        else if ( currentShapeMatrixLength == 3 && (proposedPosX < -1 || proposedPosX > 8 || proposedPosY > 18)) {
            return true;
        }

        if(currentShapeMatrixLength == 4 && (proposedPosX < 0 || proposedPosX > 6 || proposedPosY > 16)) {
            if ( (proposedPosX == -2 || proposedPosX == 8) && (proposedPosY == 17 || proposedPosY == 18)) {
                intersects = checkIntersectGridBounds(matrix, 0,4,0,4);
            }
            else if ( (proposedPosX == -1 || proposedPosX == 7) && (proposedPosY == 17 || proposedPosY == 18)){
                intersects = checkIntersectGridBounds(matrix, 0,4,0,4);
            }
            else if ( proposedPosX == -2) {
                intersects = checkIntersectGridBounds(matrix, 0,4,0,2);
            }
            else if ( proposedPosX == -1) {
                intersects = checkIntersectGridBounds(matrix, 0,4,0,1);
            }
            else if (proposedPosY == 18) {
                intersects = checkIntersectGridBounds(matrix, 2,3,0,4);
            }
            else if ( proposedPosY == 17) {
                intersects = checkIntersectGridBounds(matrix, 3,4,0,4);
            }
            else if ( proposedPosX == 8) {
                intersects = checkIntersectGridBounds(matrix, 0,4,2,3);
            }
            else{
                intersects = checkIntersectGridBounds(matrix, 0,4,3,4);
            }
        }
        else if(currentShapeMatrixLength == 3 && (proposedPosX == -1 || proposedPosX == 8 || proposedPosY == 18)){
            if ( proposedPosX == -1) {
                intersects = checkIntersectGridBounds(matrix, 0,3,0,1);
            }
            if(proposedPosX == 8) {
                intersects = checkIntersectGridBounds(matrix, 0,3,2,3);
            }
            if (proposedPosY == 18) {
                intersects = checkIntersectGridBounds(matrix, 2,3,0,3);
            }
        }
        return intersects;

    }
    public boolean checkIntersectGridBounds(int[][] matrix, int startRow, int endRow, int startCol, int endCol){
        /**
         * Helper method to the intersectsGridBounds method.
         * Checks if the cols and rows of the shape matrix intersect the grid bounds.
         * @int[][] matrix the matrix of the shape to check the intersection with
         * @int startRow the first row to check
         * @int endRow the last row to check
         * @int startCol the first col to check
         * @int endCol the last col to check
         */
        for(int i = startRow; i < endRow; i++){
            for(int j = startCol; j < endCol; j++){
                if(matrix[i][j] == 1){
                    return true;
                }
            }
        }
        return false;
    }

    public void isVictory(){
        /**
         * This method checks if a victory condition has been met, if so it plays the victory sound and registers the high score
         */
        if(mode.modeType.equals("Time") && timer.minutes == 0 && timer.seconds == 0){
            sounds.playVictoryClip();
            gameEffectsFX.changeTop(-1, "victory");
            highScoresFX.setHighScore(scoring.getTotalScore());
            victoryChecker.stop();
            stopGame();
        }
        else if(mode.modeType.equals("Score") && scoring.getTotalScore() <= 0){
            sounds.playVictoryClip();
            gameEffectsFX.changeTop(-1, "victory");
            String playerTime = String.format("%02d:%02d", timer.getMinutes(), timer.getSeconds());
            gameOver.setPlayerTime(playerTime);
            highScoresFX.setScoreModeHighScore(playerTime);
            stopGame();
        }
        else if(mode.modeType.equals("Normal")){
            sounds.playGameOverClip();
            gameEffectsFX.changeTop(-1, "gameOver");
            highScoresFX.setHighScore(scoring.getTotalScore());
            stopGame();
        }
        gameOver.switchScoreUI();
    }

    public boolean isGameOver(){
        /**
         * This method checks if a shape arrives stuck at the top center of the game board.
         * If so it returns true to show the game is over.
         */
        int gameOverRows = 1;
        int gameOverColumns = 6;
        for(int i = 0; i <= gameOverRows; i++){
            for(int j = 3; j <= gameOverColumns; j++){
                if(board.get(i)[j] == 1){
                    mainScene.setOnKeyReleased(null);
                    mainScene.setOnKeyPressed(null);
                    isVictory();
                    gameEffectsFX.ftOut.stop();
                    sounds.playGameOverClip();
                    downTimeLine.stop();
                    return true;
                }
            }
        }
        return false;
    }


    public void stopGame(){
        /**
         * Stops the game completely when a game over or victory condition has been met
         */
        mainScene.setOnKeyReleased(null);
        mainScene.setOnKeyPressed(null);
        downTimeLine.stop();
        lockTimeLine.stop();
        timer.stopTimer();
        sounds.stopMusic();
        lockTimeLineRunning = false;
        mode.sliderValue = 0;
        scoring.resetValues();
    }

    public void goToGameOverScene(){
        // trigger the gameover scene
        window.setScene(gameOver.getScene());
    }
    public void end(){}
}
