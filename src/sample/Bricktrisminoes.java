package sample;

import javafx.scene.paint.Color;

import java.util.List;

public abstract class Bricktrisminoes {
    /**
     * abstract class implemented by the shape classes
     */
    abstract List<int[][]> getShapeMatrix();
    abstract Color getColor();
}
