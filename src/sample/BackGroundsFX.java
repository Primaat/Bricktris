package sample;

import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import java.util.concurrent.ThreadLocalRandom;

public class BackGroundsFX implements StandardFX{
    /**
     * Class containing all backgrounds used in the program.
     */
    // the used background image
    BackgroundImage backgroundImage;

    // the file path for all background images
    String bgResource = "file:\\JavaFXTesting\\resources\\Backgrounds\\";

    // an array of image names
    String[] backgroundImages = {"art1.jpg","art2.jpg","art3.jpg","art5.jpg","art6.jpg","art7.jpg",
            "art8.jpg","art9.jpg","art12.jpg","art14.jpg","art16.jpg","art20.jpg","art24.jpg",
            "art25.jpg","art26.jpg","art27.png","art28.jpg","art29.jpg","art31.jpg",
            "art32.jpg","art33.jpg","art34.jpg","art35.jpg","art36.jpg","art38.jpg","art39.jpg",
            "art40.jpg","art42.jpg","art43.jpg","art44.jpg","art45.jpg","art46.jpg","art47.jpg","art48.jpg"
            ,"art51.jpg","art52.jpg","art53.jpg","art54.jpg","art55.jpg","art57.jpg"
            ,"art58.jpg","art60.jpg","art61.jpg","art62.jpg","art64.jpg","art65.jpg","art66.jpg"
            ,"art67.jpg","art68.jpg","art70.jpg","art72.jpg"
    };

    public BackGroundsFX() {
    }

    public BackgroundImage getRandomBackground(){
        /**
         * Returns a random background when leveling up during game play.
         */
        Image image = new Image(bgResource +
                backgroundImages[ThreadLocalRandom.current().nextInt(backgroundImages.length-1)], WIDTH, HEIGHT, false, true);

        backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT );
        return backgroundImage;
    }

    public String getImageURL(){
        String url = bgResource + backgroundImages[ThreadLocalRandom.current().nextInt(backgroundImages.length-1)];
        return url;
    }
}
