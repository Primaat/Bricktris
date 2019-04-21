package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


class ModeSettingsFX implements StandardFX{
    private BorderPane borderPane;
    private ImageView modeImage;
    private Scene scene;

    // stores the mode that was selected by pressing the corresponding button on the title screen
    protected String modeType;

    // lowest value of the slider is 0
    protected int minSpeedSlider = 0;

    // stores the max slider value depending on mode
    protected int maxSpeedSlider = 10;

    private Label sliderLabel;
    private Slider slider;

    // the value of the slider when starting the game
    double sliderValue;

    // which mode of the selected mode is selected
    int modeTypeSelection;

    // the style of music selected
    String musicStyleSelection = "Calm";

    protected ToggleButton toggleButton;

    // the label above the toggle group
    protected Label toggleLabel;

    // the vbox containing all mode controls
    protected VBox modeControlBox;

    public ModeSettingsFX() {}

    public void setModeScreen(String modeType){
        /**
         * Creates the actual mode screen
         */
        //new Slider().getCssMetaData().stream().map(CssMetaData::getProperty).sorted().forEach(System.out::println);

        // sets the mode type
        this.modeType = modeType;

        borderPane = new BorderPane();

        Image modeBackGroundImage = new Image("file:\\JavaFXTesting\\resources\\Backgrounds\\art43.jpg");
        BackgroundImage modeBackGround = new BackgroundImage(modeBackGroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT );
        borderPane.setBackground(new Background(modeBackGround));

        //Set the Mode name image
        if(this.modeType.equals("Normal")){
            modeImage = new ImageView("file:\\JavaFXTesting\\resources\\Images\\Normal.png");
        }
        else if(this.modeType.equals("Score")){
            modeImage = new ImageView("file:\\JavaFXTesting\\resources\\Images\\ScoreAttack.png");
        }
        else{
            modeImage = new ImageView("file:\\JavaFXTesting\\resources\\Images\\TimeAttack.png");

        }
        modeImage.setFitWidth(500);
        modeImage.setPreserveRatio(true);
        modeImage.setSmooth(true);
        modeImage.setCache(true);
        borderPane.setTop(modeImage);
        BorderPane.setAlignment(modeImage, Pos.TOP_CENTER);
        BorderPane.setMargin(modeImage, new Insets(60,0,0,0));

        setModeControls();

        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        Button startBut = new ButtonSetterFX().setButtonStates("Start");
        startBut.setOnAction(e -> {
            // set the values of the selected mode in the game scene
            if(modeType.equals("Normal")){
                modeValues.setNormalMode(sliderValue);
            }
            else if(modeType.equals("Time")){
                modeValues.setTimeAttackMode(modeTypeSelection);
            }
            else{
                modeValues.setScoreAttackMode(modeTypeSelection);
            }
            buttonEvent(new GameFX().getMainScene());
        });

        Button backBut = new ButtonSetterFX().setButtonStates("Back");
        backBut.setOnAction(e ->
            buttonEvent(titleScene)
        );

        buttonBox.getChildren().addAll(startBut, backBut);
        borderPane.setBottom(buttonBox);
        BorderPane.setMargin(buttonBox, new Insets(0,0,60,0));

        borderPane.setBottom(buttonBox);
        scene = new Scene(borderPane, WIDTH, HEIGHT);
    }

    public Scene getScene() {
        return scene;
    }

    public String getModeType() {
        return modeType;   }


    public void setModeType(String modeType) {
        this.modeType = modeType;
    }

    public double getSliderValue() {
        return sliderValue;
    }

    public int getModeTypeSelection() {
        return modeTypeSelection;
    }

    public void setModeTypeSelection(int modeTypeSelection) {
        this.modeTypeSelection = modeTypeSelection;
    }

    public void setModeControls(){
        /**
         * Sets the mode controls based on the mode type
         */
        int[] time = {2, 5, 10};
        int[] score = {10_000, 50_000, 100_000};
        modeControlBox = new VBox();
        modeControlBox.setSpacing(15);
        modeControlBox.setAlignment(Pos.CENTER);

        switch(modeType){
            case "Normal":
                sliderLabel = new Label("Select Speed"); // label for the slider
                sliderLabel.setStyle(labelStyle);
                modeControlBox.getChildren().add(sliderLabel);

                slider = new Slider(minSpeedSlider, maxSpeedSlider,0); // creates a slider for level selection
                slider.valueProperty().addListener(event -> {
                    sliderValue = slider.getValue();
                });
                slider.setShowTickMarks(true); // shows ticks on the slider
                slider.setStyle("-fx-control-inner-background: yellow;\n");
                slider.showTickMarksProperty();
                slider.setShowTickLabels(true); // shows labels of the tick marks
                slider.setMajorTickUnit(1); // sets major tick marks
                slider.setMinorTickCount(0);
                slider.setSnapToTicks(true);

                slider.setMaxSize(400,30);
                modeControlBox.getChildren().add(slider);

                VBox mainBox = new VBox();
                mainBox.setAlignment(Pos.CENTER);
                mainBox.setSpacing(20);
                mainBox.getChildren().addAll(
                        modeControlBox,
                        createMusicTogglebox()
                ) ;

                borderPane.setCenter(mainBox);
                BorderPane.setAlignment(mainBox, Pos.CENTER);
                break;

            case "Time":
                VBox mainBox1 = new VBox();
                mainBox1.setAlignment(Pos.CENTER);
                mainBox1.setSpacing(20);
                mainBox1.getChildren().addAll(
                        createSettingToggleBox(time, "Select Time Target"),
                        createMusicTogglebox()
                        ) ;
                borderPane.setCenter(mainBox1);
                BorderPane.setAlignment(mainBox1, Pos.CENTER);
                break;

            case "Score":
                VBox mainBox2 = new VBox();
                mainBox2.setAlignment(Pos.CENTER);
                mainBox2.setSpacing(20);
                mainBox2.getChildren().addAll(
                        createSettingToggleBox(score, "Select Score Target"),
                        createMusicTogglebox()
                ) ;
                borderPane.setCenter(mainBox2);
                BorderPane.setAlignment(mainBox2, Pos.CENTER);
                break;

            default:
                break;
        }
    }

    public VBox createSettingToggleBox(int[] array, String labelText){
        /**
         * Returns a vbox containing a Hbox with a toggle group of modetype toggle buttons
         */
        VBox toggleVBox = new VBox();
        toggleVBox.setSpacing(15);
        toggleVBox.setAlignment(Pos.CENTER);

        HBox toggleHBox = new HBox();
        toggleHBox.setSpacing(10);
        toggleHBox.setAlignment(Pos.CENTER);
        ToggleGroup toggleGroup = new ToggleGroup();

        for(int i = 0; i < 3; i++){
            int num = i;
            ToggleButton tb;
            if(modeType.equals("Time")){
                tb = new ButtonSetterFX().setToggleButtonStates(array[i] + " mins." );
            }
            else{
                tb = new ButtonSetterFX().setToggleButtonStates(array[i] + "" );
            }
            if(i == 0){
                tb.setSelected(true);
                modeTypeSelection = array[num];
            }
            tb.setOnAction(event -> {
                modeTypeSelection = array[num];
                System.out.println(tb.getId() + " Button selected");
            });
            tb.setToggleGroup(toggleGroup);
            toggleHBox.getChildren().add(tb);
        }
        toggleLabel = new Label(labelText);
        toggleLabel.setStyle(labelStyle);
        toggleVBox.getChildren().addAll(toggleLabel, toggleHBox);

        return toggleVBox;
    }

    public VBox createMusicTogglebox(){
        /**
         * Returns a vbox containing a Hbox with a toggle group of music type toggle buttons
         */
        VBox toggleVBox = new VBox();
        String[] musicTypes = {"Calm", "Energetic", "Explosive"};
        toggleVBox = new VBox();
        toggleVBox.setSpacing(15);
        toggleVBox.setAlignment(Pos.CENTER);

        HBox toggleHBox = new HBox();
        toggleHBox.setSpacing(10);
        toggleHBox.setAlignment(Pos.CENTER);
        ToggleGroup toggleGroup = new ToggleGroup();

        for(int i = 0; i < 3; i++){
            int num = i;
            ToggleButton tb;
            tb = new ButtonSetterFX().setToggleButtonStates(musicTypes[i]);
            if(i == 0){
                tb.setSelected(true);
            }

            final String musicType = musicTypes[i];
            tb.setOnAction(event -> {
                musicStyleSelection = musicType;
                mode.musicStyleSelection = musicStyleSelection;
                System.out.println(tb.getId() + " Button selected");
            });
            tb.setToggleGroup(toggleGroup);
            toggleHBox.getChildren().add(tb);
        }
        toggleLabel = new Label("Choose your music style");
        toggleLabel.setStyle(labelStyle);
        toggleVBox.getChildren().addAll(toggleLabel, toggleHBox);

        return toggleVBox;
    }

    public void buttonEvent(Scene scene){
        sounds.playButtonClip();
        highScoresFX.loadModeHighScores();
        window.setScene(scene);
    }


}
