package sample;

import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public interface StandardFX {

    // width of the stage/ window
    double WIDTH = 1920;

    // height of the stage/ window
    double HEIGHT = 1080;

    // width of the game scene
    double GAMEPANEWIDTH = 450;

    // height of the game scene
    double GAMEPANEHEIGHT = 900;

    // width of all buttons
    double butWidth = WIDTH * 0.11;

    // height of all buttons
    double butHeight = HEIGHT *0.09;

    // initial speed for the downtimeline
    int downSpeed = 800;

    // initial speed for the locktimeline
    int lockSpeed = 1000;

    // custom fonts
    Font leftOverFont = Font.loadFont("file:\\JavaFXTesting\\resources\\Font_files\\LEFTOVER.TTF",20);
    Font battleStarFont = Font.loadFont("file:\\JavaFXTesting\\resources\\Font_files\\battlestar.ttf",70);


    // label, text and button styles
    String labelStyle =
            "-fx-text-fill: yellow;\n" +
            "-fx-font-family: battlestar;\n" +
            "-fx-font-size: 30;\n";

    String majorLabelStyle =
            "-fx-text-fill: yellow;\n" +
            "-fx-font-family: battlestar;\n" +
            "-fx-font-size: 45;\n" +
            "-fx-background-color: transparent;";

    String minorLabelStyle =
            "-fx-text-fill: white;\n" +
            "-fx-font-family: battlestar;\n" +
            "-fx-font-size: 15;\n" +
            "-fx-background-color: transparent;\n";

    String minorTextStyle1 =
            "-fx-fill: white;\n" +
            "-fx-text-fill: white;\n" +
            "-fx-font-family: battlestar;\n" +
            "-fx-font-size: 15;\n" +
            "-fx-background-color: transparent;\n";

    String minorTextStyle2 =
            "-fx-fill: yellow;\n" +
            "-fx-text-fill: yellow;\n" +
            "-fx-font-family: battlestar;\n" +
            "-fx-font-size: 15;\n" +
            "-fx-background-color: transparent;\n";

    String buttonStyle =
            "-fx-background-color: transparent;\n" +
            "-fx-font-family: battlestar;\n" +
            "-fx-text-fill: yellow;\n" +
            "-fx-font-size: 20;\n" +
            "-fx-alignment: center;\n" +
            "-fx-padding: 4 4 4 4;\n" +
            "-fx-pref-height: " + butHeight + ";\n" +
            "-fx-pref-width: " + butWidth + ";\n";

    String buttonHoverStyle =
            "-fx-border-color: transparent, yellow;\n" +
            "-fx-border-width: 1, 1;\n" +
            "-fx-border-style: solid;\n" +
            "-fx-border-radius: 0, 0;\n" +
            "-fx-border-insets: 1 1 1 1, 0;\n" +
            "-fx-background-color: transparent;\n" +
            "-fx-font-family: battlestar;\n" +
            "-fx-text-fill: rgb(" + 192 + "," + 168 + "," + 1 + ");\n" +
            "-fx-font-size: 20;\n" +
            "-fx-alignment: center;\n" +
            "-fx-padding: 4 4 4 4;\n" +
            "-fx-pref-height: " + butHeight + ";\n" +
            "-fx-pref-width: " + butWidth + ";\n";

    String buttonPressedStyle =
            "-fx-border-color: transparent, white;\n" +
                    "-fx-border-width: 1, 1;\n" +
                    "-fx-border-style: solid;\n" +
                    "-fx-border-radius: 0, 0;\n" +
                    "-fx-border-insets: 1 1 1 1, 0;\n" +
            "-fx-background-color: black;\n" +
            "-fx-text-fill: white;\n" +
            "-fx-background-color: transparent;\n" +
            "-fx-font-family: battlestar;\n" +
            "-fx-font-size: 20;\n" +
            "-fx-alignment: center;\n" +
            "-fx-padding: 4 4 4 4;\n" +
            "-fx-pref-height: " + butHeight + ";\n" +
            "-fx-pref-width: " + butWidth + ";\n";

    // initialises all necessary Classes
    ReadWriteHighScoreFileFX rw = new ReadWriteHighScoreFileFX();
    BackGroundsFX backGrounds = new BackGroundsFX();
    ButtonSetterFX buttons = new ButtonSetterFX();
    ScoringFX scoring = new ScoringFX();
    HighScoresFX highScoresFX = new HighScoresFX();
    SoundsFX sounds = new SoundsFX();
    Scene titleScene = new TitleScreenFX().getScene();
    Scene creditsScene = new CreditsFX().getScene();
    GameOverScreenFX gameOver = new GameOverScreenFX();
    ModeSettingsFX mode = new ModeSettingsFX();
    ModeValuesFX modeValues = new ModeValuesFX();
    DigitalLetterDialogBox digitalDialog = new DigitalLetterDialogBox();

    Stage window = new Stage();
}
