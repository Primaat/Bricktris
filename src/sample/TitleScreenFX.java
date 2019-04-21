package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class TitleScreenFX implements StandardFX{
    /**
     * The title screen of the game
     */
    BorderPane borderPane;
    Scene scene;

    protected TitleScreenFX(){

        borderPane = new BorderPane();
        Image titleBackground = new Image("file:\\JavaFXTesting\\resources\\Backgrounds\\art27.png");
        BackgroundImage titleBackGround = new BackgroundImage(titleBackground, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT );
        borderPane.setBackground(new Background(titleBackGround));

        ImageView titleImage = new ImageView("file:\\JavaFXTesting\\resources\\Images\\BrickTrisLogo.png");
        titleImage.setFitWidth(800);
        titleImage.setPreserveRatio(true);
        titleImage.setSmooth(true);
        titleImage.setCache(true);

        // the four main buttons on the title screen
        Button startMain = new ButtonSetterFX().setButtonStates("Normal");
        startMain.setOnAction(e -> {
            mode.setModeScreen("Normal");
            buttonEvent(mode.getScene());
        });

        Button startTime = new ButtonSetterFX().setButtonStates("Time");
        startTime.setOnAction(e -> {
            mode.setModeScreen("Time");
            buttonEvent(mode.getScene());
        });

        Button startScore = new ButtonSetterFX().setButtonStates("Score");
        startScore.setOnAction(e -> {
            mode.setModeScreen("Score");
            buttonEvent(mode.getScene());
        });

        Button credits = new ButtonSetterFX().setButtonStates("Credits");
        credits.setOnAction(e ->
            buttonEvent(creditsScene)
        );

        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setPrefWrapLength((butWidth * 2) + 20);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.getChildren().addAll(startMain, startTime, startScore, credits);


        borderPane.setTop(titleImage);
        BorderPane.setAlignment(titleImage, Pos.TOP_CENTER);
        BorderPane.setMargin(titleImage, new Insets(160,0,0,0));

        borderPane.setCenter(flowPane);
        BorderPane.setAlignment(flowPane, Pos.CENTER);
        BorderPane.setMargin(flowPane, new Insets(80,0,80,0));

        scene = new Scene(borderPane, WIDTH, HEIGHT);

        sounds.playTitleMusic();
    }

    public Scene getScene(){return scene;}

    public void buttonEvent(Scene scene){
        sounds.playButtonClip();
        window.setScene(scene);
    }

}