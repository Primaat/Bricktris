package sample;

import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GameEffectsFX implements StandardFX {
    /**
     * Class containing all game effects that are used during gameplay.
     */
    StackPane stackPane = new StackPane();
    Node topNode;
    Node newNode;

    // the effects on the stack
    ObservableList<Node> childs;

    FadeTransition ftOut;
    int fadeDuration;

    // the angle the bam effects get when fired
    double[] degrees = {-10.0,-10.0,0.0,10.0,10.0};

    // path of the loaction where the images reside
    String url = "file:\\JavaFXTesting\\resources\\Images\\";
    List<ImageView> imageViewList = new ArrayList<>();

    // BE AWARE!!! all effects are in the same order as the sound arrays
    String[] comboEffects = {"C-C-C-COMBOBREAKER.png","TripleCombo.png","SuperCombo.png","Awesomecombo1.png","BlasterCombo1.png",
            "BrutalCombo1.png","HyperCombo1.png","KingCombo1.png","MasterCombo.png",
            "MonsterCombo.png","KillerCombo.png","UltraCombo3.png" };
    String[] comboIDs = {"breaker","triple","super","awesome","blaster","brutal","hyper","king","master","monster"
                ,"killer","ultra"};

    String[] perfectEffects = {"Perfect1.png","Perfect2.png","Perfect3.png","Perfect4.png","Perfect5.png"};
    String[] perfectIDs = {"perfect1","perfect2","perfect3","perfect4","perfect5"};

    String[] bamEffects = {"BAM.png","BAM1.png","BAM2.png"};
    String[] bamIDs = {"bam","bam1","bam2"};

    String[] otherEffects = {"READY.png","SupremeVictory.png","GameOverBig.png","LEVELUP.png"};
    String[] otherIDs = {"ready","victory","gameOver","levelup"};



    public GameEffectsFX() {
        // gets all the available css properties for, in this case, buttons
        //new Button().getCssMetaData().stream().map(CssMetaData::getProperty).sorted().forEach(System.out::println);
        stackPane.setPrefWidth(GAMEPANEWIDTH);
        stackPane.setPrefWidth(GAMEPANEHEIGHT);
        stackPane.setStyle("-fx-background-color: transparent");

        createGameFXStackPane(comboEffects, comboIDs, 350);
        createGameFXStackPane(perfectEffects, perfectIDs, 350);
        createGameFXStackPane(bamEffects, bamIDs, 200);
        createGameFXStackPane(otherEffects, otherIDs, 320);
    }

    public void createGameFXStackPane(String[] files, String[] ids, int width){
        /**
         * Creates the actual stack pane containing all the effects
         * @String[] files contains the images of the effects
         * @String[] ids contains the ids of the the effects
         * @int width of the image
         */
        VBox vbox;
        Button continueButton;
        for(int i = 0; i < files.length; i++){
            ImageView image = new ImageView(url + files[i]);
            image.setId(ids[i]);
            image.setOpacity(0);
            image.setFitWidth(width);
            image.setPreserveRatio(true);
            image.setSmooth(true);
            image.setCache(true);
            if(image.getId().equals("gameOver") || image.getId().equals("victory")){
                vbox = new VBox();
                vbox.setSpacing(100);
                vbox.setOpacity(0);
                vbox.setAlignment(Pos.CENTER);
                vbox.setId(image.getId());

                // the continue button that accompanies the gameover and victory effects
                continueButton = buttons.setButtonStates("Continue");
                continueButton.setOnAction(e-> window.setScene(gameOver.getScene()));
                continueButton.setStyle("-fx-background-color: transparent;\n" +
                        "-fx-font-family: battlestar;\n" +
                        "-fx-text-fill: White;\n" +
                        "-fx-font-size: 20;\n" +
                        "-fx-alignment: center;\n" +
                        "-fx-padding: 4 4 4 4;\n" +
                        "-fx-pref-height: " + butHeight + ";\n" +
                        "-fx-pref-width: " + butWidth + ";\n");
                continueButton.setOpacity(1);

                image.setOpacity(1);
                vbox.getChildren().add(image);
                vbox.getChildren().add(continueButton);

                stackPane.getChildren().add(vbox);
                StackPane.setAlignment(vbox, Pos.CENTER);
            }
            else{
                imageViewList.add(image);
                stackPane.getChildren().add(image);
                StackPane.setAlignment(image, Pos.CENTER);
            }
        }
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public void changeTop(int comboCounter, String effect) {
        /**
         * On command triggers the the top(visible) effect.
         * @int comboCounter if checks if the conter is high enough to show combo effects
         * @String effect the name of the effect that needs to be triggered
         */
        if(ftOut != null ){
            // stop the fade out effect
            ftOut.stop();
        }

        childs = stackPane.getChildren();

        if (childs.size() > 1) {
            topNode = childs.get(childs.size()-1);
            topNode.setOpacity(0);
            if(effect == null){
                newNode = findComboNode(comboCounter);
            }
            else if(comboCounter == -1){
                newNode = findEffectNode(effect);
            }

            if(newNode == topNode){
                topNode.setOpacity(1);
                fadeOut();
            }
            else if(!newNode.getId().equals("victory") && !newNode.getId().equals("gameOver")){
                if(newNode.getId().equals("bam") || newNode.getId().equals("bam1") || newNode.getId().equals("bam2")){
                    double randNum = degrees[new Random().nextInt(degrees.length)];
                    newNode.setRotate(randNum);
                }
                newNode.toFront();
                newNode.setOpacity(1);
                topNode.setOpacity(0);
                fadeOut();
            }
            else{
                newNode.toFront();
                newNode.setOpacity(1);
            }
        }
    }

    private Node findComboNode(int comboCounter){
        /**
         * Helper method to the changeTop method.
         * Returns the correct combo effect base on @int comboCounter.
         */
        String id = null;
        if(comboCounter == 0){
            id = comboIDs[0];
        }
        else if(comboCounter == 1 || comboCounter == 2){
            id = perfectIDs[ThreadLocalRandom.current().nextInt(perfectEffects.length-1)];
        }
        else if(comboCounter > 2){
            id = comboIDs[comboCounter - 2];
        }

        fadeDuration = 1500;

        for(Node node: childs){
            if(node.getId().equals(id)){
                return node;
            }
        }
        return null;
    }

    private Node findEffectNode(String effect){
        /**
         * Helper method to the changeTop method.
         * returns the correct effect other than the combo effects based on the @String effect.
         */
        String id = null;
        if(effect.equals("bam")){
            id = bamIDs[ThreadLocalRandom.current().nextInt(bamEffects.length-1)];
            fadeDuration = 1000;
        }

        else if(effect.equals("ready") || effect.equals("victory") || effect.equals("gameOver") || effect.equals("levelup")){
            id = effect;
            fadeDuration = 2000;
        }
        for(Node node: childs){
            if(node.getId().equals(id)){
                return node;
            }
        }
        return null;
    }

    public void fadeOut(){
        ftOut = new FadeTransition(Duration.millis(fadeDuration), stackPane);
        ftOut.setFromValue(1);
        ftOut.setToValue(0);
        ftOut.setCycleCount(1);
        ftOut.play();
    }

    public void setObservableList(){
        ObservableList<Node> childs = stackPane.getChildren();
    }


}
