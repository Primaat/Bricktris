package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class BricktrisFX extends Application implements StandardFX{
    /**
     * The main Class
     * @param stage the stage where the program takes place
     */

    @Override
    public void start(Stage stage) {
        /**
         * Starts the program and shows the Title screen
         */
        window.setTitle("Bricktris");
        window.setOnCloseRequest(e ->{
            // takes over the close request from the window to the closeGame method
            e.consume();
            closeGame();});
        window.setResizable(false);
        window.setScene(titleScene);
        window.show();
    }

    public void closeGame(){
        /**
         * triggers the confirm box while clicking the "x" on the window or when 'enter' or 'escape'
         * are pressed during gameplay.
         */

        int answer = ConfirmBox.display("Close window", "What do you want to do?");
        if(answer == 1){
            window.close();
        }
        else if(answer == 0){
            window.setScene(titleScene);
            sounds.playTitleMusic();
        }
    }

    public static void main(String[] args) {
        /**
         * launch the program
         */
        launch(args);
    }

}
