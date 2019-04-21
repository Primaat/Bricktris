package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Random;

public class DigitalLetterDialogBox  {
    static HBox text;
    static VBox layout;
    static Scene scene;
    static Stage dialogWindow;
    static DigitalFontFX digitalFont;
    static TextField t;

    public static int answer = 2;

    public static int display(String title, String message, Color color) {
        digitalFont = new DigitalFontFX();
        dialogWindow = new Stage();
        dialogWindow.initModality(Modality.APPLICATION_MODAL);
        dialogWindow.setTitle(title);
        dialogWindow.setMinWidth(1000);
        dialogWindow.setMinHeight(400);
        text = digitalFont.createText("", color);
        text.setAlignment(Pos.CENTER);

        t = new TextField();
        t.textProperty().addListener((observable, oldValue, newValue ) -> {
            System.out.println("Textfile changed from " + oldValue + " to " + newValue);
            changeDigitalText(newValue);
        });

        Button titleScreen = new Button("Title Screen");
        Button exitGame = new Button("Exit Game");
        Button back = new Button("Back");

        titleScreen.setOnAction(e-> {
            answer = 0;
            dialogWindow.close();
        });
        exitGame.setOnAction(e-> {
            answer = 1;
            dialogWindow.close();
        });
        back.setOnAction(e-> {
            answer = 2;
            dialogWindow.close();
        });

        layout = new VBox(10);

        layout.getChildren().addAll(text, t, titleScreen, exitGame, back);
        layout.setAlignment(Pos.CENTER);
        scene = new Scene(layout);
        dialogWindow.setScene(scene);
        dialogWindow.showAndWait();

        return answer;
    }

    public static Color randomColor(){
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return Color.rgb(r,g,b);
    }

    public static void changeDigitalText(String newValue){
        text = digitalFont.createText(newValue, randomColor());
        layout.getChildren().remove(0);
        layout.getChildren().add(0, text);
    }

}
