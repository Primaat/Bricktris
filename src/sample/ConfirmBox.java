package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ConfirmBox implements StandardFX{
    /**
     * The pop up box containing the choices to stop the program, go back to the title screen
     * or back to the game.
     */

    protected static int answer = 2;

    public static int display(String title, String message) {
        Stage dialogWindow = new Stage();
        dialogWindow.initModality(Modality.APPLICATION_MODAL);
        dialogWindow.setTitle(title);
        dialogWindow.setMinWidth(250);
        dialogWindow.setMinHeight(200);
        Label label = new Label();
        label.setText(message);

        // create two buttons
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

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,titleScreen, exitGame, back);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        dialogWindow.setScene(scene);
        dialogWindow.showAndWait();

        return answer;
    }
}
