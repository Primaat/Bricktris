package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Map;

class GameOverScreenFX implements StandardFX{
    /**
     * Creates the Game over screen and calls the relevant high scores list
     */
    BorderPane borderPane;
    VBox vbox;
    HBox hbox;
    Scene scene;
    // textfield for high scores
    TextField score;

    // textfield for names
    TextField name;

    // a textfield containing the players current high score name
    TextField player = new TextField();

    // the player's name
    String playerName;

    // the player's score
    int playerScore;

    // the best time of the player
    String playerTime;

    public GameOverScreenFX(){
        borderPane = new BorderPane();
        Image image = new Image("file:\\JavaFXTesting\\resources\\Backgrounds\\art72.jpg");
        BackgroundImage gameOverBackground = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT );
        borderPane.setBackground(new Background(gameOverBackground));

        ImageView gameOverImage = new ImageView("file:\\JavaFXTesting\\resources\\Images\\GameOverScreenLogo.png");
        borderPane.setTop(gameOverImage);
        BorderPane.setAlignment(gameOverImage, Pos.TOP_CENTER);
        BorderPane.setMargin(gameOverImage, new Insets(40,0,0,0));

        // a button that gets the player back to the title screen
        Button title = buttons.setButtonStates("Title Screen");
        title.setPrefSize(WIDTH * 0.09,HEIGHT * 0.05);
        title.setOnAction(e -> {
            buttonEvent(titleScene);
        });

        // a retry button
        Button retry = buttons.setButtonStates("Retry");
        retry.setMinSize(WIDTH * 0.09,HEIGHT * 0.05);
        retry.setOnAction(e -> {
            // set the values of the selected mode in the game scene
            if(mode.getModeType().equals("Normal")){
                modeValues.setNormalMode(mode.getSliderValue());
            }
            else if(mode.getModeType().equals("Time")){
                modeValues.setTimeAttackMode(mode.getModeTypeSelection());
            }
            else{
                modeValues.setScoreAttackMode(mode.getModeTypeSelection());
            }
            buttonEvent(new GameFX().getMainScene());
        });

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(title, retry);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.TOP_CENTER);
        borderPane.setBottom(buttonBox);
        BorderPane.setMargin(buttonBox, new Insets(0,0,40,0));

        scene = new Scene(borderPane, WIDTH, HEIGHT);
    }

    public Scene getScene(){
        return scene;
    }

    public void switchScoreUI(){
        /**
         * A method that switches the high score mode bases on the chosen mode type
         */
        if(mode.modeType.equals("Score")){
            setScoreModeHighScoreUI();
        }
        else{
            setHighScoresUI();
        }
    }

    public void setHighScoresUI(){
        /**
         * Creates the visible list of high score.
         * Calls the high scores for the normal or time attack modes and adds a new high score if applicable
         */
        vbox = new VBox();
        vbox.setMaxHeight(500);
        vbox.setAlignment(Pos.CENTER);

        ImageView hallOfFame = new ImageView("file:\\JavaFXTesting\\resources\\Images\\HALLOFFAME.png");
        vbox.getChildren().add(hallOfFame);

        Label emptyLabel = new Label();
        emptyLabel.setPrefHeight(30);
        vbox.getChildren().add(emptyLabel);

        HBox timePlayerBox = new HBox();
        timePlayerBox.setAlignment(Pos.CENTER);
        timePlayerBox.setSpacing(35);

        Label bestTimes = new Label("Score");
        bestTimes.setStyle(labelStyle);
        Label playerName = new Label("Player");
        playerName.setStyle(labelStyle);

        timePlayerBox.getChildren().addAll(bestTimes,playerName);
        vbox.getChildren().add(timePlayerBox);


        int count = 10;
        int highScoresLength = highScoresFX.getHighScores().size();

        if(highScoresLength > 0){
            for(Map.Entry<Integer, String> entry : highScoresFX.getHighScores().entrySet()){
                hbox = new HBox();

                score = new TextField(entry.getKey() + "");
                score.setAlignment(Pos.BASELINE_RIGHT);
                score.setEditable(false);
                score.setStyle(minorTextStyle2);

                if((entry.getValue().equals("???") && !score.getText().equals("0"))){
                    player.setAlignment(Pos.CENTER_LEFT);
                    player.setEditable(true);
                    player.setStyle(minorTextStyle2);
                    playerScore = Integer.parseInt(score.getText());
                    hbox.getChildren().addAll(score, player);
                }
                else{
                    name = new TextField(entry.getValue());
                    name.setAlignment(Pos.CENTER_LEFT);
                    name.setEditable(false);
                    name.setStyle(minorTextStyle2);
                    hbox.getChildren().addAll(score, name);
                }
                hbox.setSpacing(35);
                hbox.setAlignment(Pos.CENTER);
                vbox.getChildren().add(hbox);
                count++;
            }
        }
        for(int i = count; i < 25; i++){
            hbox = new HBox();

            score = new TextField("0");
            score.setAlignment(Pos.BASELINE_RIGHT);
            score.setEditable(false);
            score.setStyle(minorTextStyle2);

            name = new TextField("???");
            name.setAlignment(Pos.CENTER_LEFT);
            name.setStyle(minorTextStyle2);
            name.setEditable(false);

            hbox.getChildren().addAll(score, name);
            hbox.setSpacing(35);
            hbox.setAlignment(Pos.CENTER);
            vbox.getChildren().add(hbox);
        }
        borderPane.setCenter(vbox);
        BorderPane.setAlignment(vbox, Pos.CENTER);
    }

    public void setScoreModeHighScoreUI(){
        /**
         * Creates the visible list of best times.
         * Calls the best times for the score attack mode and adds a new best time if applicable
         */
        vbox = new VBox();
        vbox.setMaxHeight(500);
        vbox.setAlignment(Pos.CENTER);

        ImageView hallOfFame = new ImageView("file:\\JavaFXTesting\\resources\\Images\\HALLOFFAME.png");
        vbox.getChildren().add(hallOfFame);

        Label emptyLabel = new Label();
        emptyLabel.setPrefHeight(30);
        vbox.getChildren().add(emptyLabel);

        HBox timePlayerBox = new HBox();
        timePlayerBox.setAlignment(Pos.CENTER);
        timePlayerBox.setSpacing(35);

        Label bestTimes = new Label("Time");
        bestTimes.setStyle(labelStyle);
        Label playerName = new Label("Player");
        playerName.setStyle(labelStyle);

        timePlayerBox.getChildren().addAll(bestTimes,playerName);
        vbox.getChildren().add(timePlayerBox);

        int count = 10;
        int highScoresLength = highScoresFX.getScoreModeHighScores().size();

        if(highScoresLength > 0){
            for(Map.Entry<String, String> entry : highScoresFX.getScoreModeHighScores().entrySet()){
                hbox = new HBox();

                score = new TextField(entry.getKey() + "");
                score.setAlignment(Pos.BASELINE_RIGHT);
                score.setEditable(false);
                score.setStyle(minorTextStyle2);

                if((entry.getValue().equals("???") && !score.getText().equals("00:00"))){
                    player.setAlignment(Pos.CENTER_LEFT);
                    player.setEditable(true);
                    player.setStyle(minorTextStyle2);
                    hbox.getChildren().addAll(score, player);
                }
                else{
                    name = new TextField(entry.getValue());
                    name.setAlignment(Pos.CENTER_LEFT);
                    name.setEditable(false);
                    name.setStyle(minorTextStyle2);
                    hbox.getChildren().addAll(score, name);
                }
                hbox.setSpacing(20);
                hbox.setAlignment(Pos.CENTER);
                vbox.getChildren().add(hbox);
                count++;
            }
        }
        for(int i = count; i < 25; i++){
            hbox = new HBox();

            score = new TextField("00:00");
            score.setAlignment(Pos.BASELINE_RIGHT);
            score.setEditable(false);
            score.setStyle(minorTextStyle2);

            name = new TextField("???");
            name.setAlignment(Pos.CENTER_LEFT);
            name.setStyle(minorTextStyle2);
            name.setEditable(false);

            hbox.getChildren().addAll(score, name);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER);
            vbox.getChildren().add(hbox);
        }
        borderPane.setCenter(vbox);
        BorderPane.setAlignment(vbox, Pos.CENTER);
    }

    public void setPlayerTime(String playerTime){
        this.playerTime = playerTime;
    }

    public void buttonEvent(Scene scene){
        /**
         * when a button has been triggered, play the button sounds, and set the high score and names
         * after which the applicable scene is set.
         */
        sounds.playButtonClip();
        playerName = player.getText();
        if(mode.modeType.equals("Score")){
            highScoresFX.setScoreModeHighScoreName(playerTime, playerName);
        }
        else{
            highScoresFX.setHighScoreName(playerScore, playerName);
        }
        window.setScene(scene);
    }
}
