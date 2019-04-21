package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


class CreditsFX implements StandardFX{
    /**
     *     this class contains the Credits menu which can be chosen from the title screen
     */

    BorderPane borderPane;
    Scene scene;

    String credits = "All rights reserved. Design and production by Sebastiaan van den Berg, The Netherlands.\n" +
    "This program is meant for testing and portfolio purposes only.\n" +
    "This program as a whole or any media within may not be sold or used commercially in any way, shape or form.\n" +
    "No permission has been asked for any material that is not created by me, however,\n" +
    "I would like to thank everyone who has shared their creations online to be used freely.\n" +
    "I can't rule out that certain background images or sounds are not rights free,\n" +
    "If you find you have the rights to these images or sounds and want these removed\n" +
    "from my program, please let me know and I will remove them.\n";


    public CreditsFX() {

        borderPane = new BorderPane();

        Image image = new Image("file:\\JavaFXTesting\\resources\\Backgrounds\\art16.jpg");
        BackgroundImage optionsBackground = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT );
        borderPane.setBackground(new Background(optionsBackground));

        ImageView creditsImage = new ImageView("file:\\JavaFXTesting\\resources\\Images\\credits.png");
        creditsImage.setFitWidth(500);
        creditsImage.setPreserveRatio(true);
        creditsImage.setSmooth(true);
        creditsImage.setCache(true);
        borderPane.setTop(creditsImage);
        BorderPane.setAlignment(creditsImage, Pos.TOP_CENTER);
        BorderPane.setMargin(creditsImage, new Insets(60,0,0,0));

        Button back = buttons.setButtonStates("Back");
        back.setPrefSize(WIDTH * 0.09,HEIGHT * 0.05);
        back.setOnAction(e -> buttonEvent(titleScene));

        borderPane.setBottom(back);
        BorderPane.setAlignment(back, Pos.BOTTOM_CENTER);

        createAlbumHBox();
        scene = new Scene(borderPane, WIDTH, HEIGHT);
    }

    public Scene getScene(){
        return scene;
    }


    public void createAlbumHBox(){
        /**
         * Creates the HBOX containing art and names for the used music in the game
         */
        String[] imagelocations = {
                "file:\\JavaFXTesting\\resources\\albums\\The_Intangible\\Planet-Energy-Art-small.jpg",
                "file:\\JavaFXTesting\\resources\\albums\\Globular_Entangled_Everything\\00 - Globular - Entangled Everything.jpg",
                "file:\\JavaFXTesting\\resources\\albums\\Cybernetika_Colossus\\00 - Cybernetika - Colossus - Image 1.png"
        };
        String[] imageCaptions = {
                "The Intangible",
                "Globular_Entangled_Everything",
                "Cybernetika - colossus"
        };
        String[] urls = {
                "https://soundcloud.com/reptilefortune",
                "https://ektoplazm.com/free-music/globular-entangled-everything",
                "https://ektoplazm.com/free-music/cybernetika-colossus"
        };
        String[] urlText = {
                "Get The Intangible",
                "Get Globular_Entangled_Everything",
                "Get Cybernetika-colossus"
        };
        VBox creditsBox = new VBox();
        creditsBox.setAlignment(Pos.CENTER);
        creditsBox.setSpacing(40);
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        VBox vbox;

        for(int i = 0; i < urls.length; i++){
            ImageView image = new ImageView(imagelocations[i]);
            image.setFitHeight(200);
            image.setFitWidth(200);
            image.setSmooth(true);
            image.setCache(true);

            final String url = urls[i];

            Hyperlink imageLink = new Hyperlink();
            imageLink.setGraphic(image);
            imageLink.setOnAction(e-> onActionEvent(url));

            Hyperlink textLink = new Hyperlink(urlText[i]);
            textLink.setStyle(minorTextStyle2);
            textLink.setOnAction(e-> onActionEvent(url));

            vbox = new VBox();
            vbox.setAlignment(Pos.CENTER);
            vbox.setPadding(new Insets(10,10,10,10));
            vbox.getChildren().add(imageLink);
            vbox.getChildren().add(textLink);
            hbox.getChildren().add(vbox);
        }
        Text creditsField = new Text();
        creditsField.setText(credits);
        creditsField.setStyle(minorTextStyle2);
        creditsField.setTextAlignment(TextAlignment.CENTER);

        creditsBox.getChildren().addAll(hbox,creditsField);

        borderPane.setCenter(creditsBox);
        BorderPane.setAlignment(creditsBox, Pos.CENTER);

    }

    public void onActionEvent(String url){
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException io) {
            io.printStackTrace();
        } catch (URISyntaxException ue) {
            ue.printStackTrace();
        }
    }

    public void buttonEvent(Scene scene){
        sounds.playButtonClip();
        window.setScene(scene);
    }

}

