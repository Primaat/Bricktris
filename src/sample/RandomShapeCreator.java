package sample;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RandomShapeCreator {
    /**
     * Class used to create random shapes
     */

    // list containing all shape types
    private final List<Bricktrisminoes> shapes;

    // peekable list containing all next shapes
    private final Deque<Bricktrisminoes> nextShapes = new ArrayDeque<>();

    // list of all shapes visible in the preview box
    private final List<Bricktrisminoes> previewShapes = new ArrayList<>();

    // The preview box
    private VBox previewBox;

    public RandomShapeCreator(){
        /**
         * Fills the lists and creates the preview box
         */
        shapes = new ArrayList<>();
        shapes.add(new Shape_I());
        shapes.add(new Shape_O());
        shapes.add(new Shape_J());
        shapes.add(new Shape_L());
        shapes.add(new Shape_S());
        shapes.add(new Shape_Z());
        shapes.add(new Shape_T());

        for(int i = 0; i < 5; i++){
            Bricktrisminoes shape = shapes.get(ThreadLocalRandom.current().nextInt(shapes.size()));
            while(previewShapes.contains(shape)){
                shape = shapes.get(ThreadLocalRandom.current().nextInt(shapes.size()));
            }
            nextShapes.add(shape);
            previewShapes.add(shape);
        }

        createPreviewBox();
    }

    public Bricktrisminoes getShape(){
        /**
         * Returns the next shape from the preview shapes list and updates the preview box
         */
        if(nextShapes.size() <= 5){
            //Collections.shuffle(shapes);
            Bricktrisminoes shape = shapes.get(ThreadLocalRandom.current().nextInt(shapes.size()));
            while(previewShapes.contains(shape)){
                shape = shapes.get(ThreadLocalRandom.current().nextInt(shapes.size()));
            }
            nextShapes.add(shape);
            previewShapes.add(shape);
        }
        previewShapes.remove(0);
        createPreviewBox();
        return nextShapes.poll();
    }

    public Bricktrisminoes getNextShape(){
        return nextShapes.peek();
    }

    public void createPreviewBox(){
        /**
         * Creates the preview box
         */
        previewBox = new VBox();
        Rectangle rect;

        for(Bricktrisminoes shape: previewShapes){
            int[][] shapeMatrix = shape.getShapeMatrix().get(0);
            int shapeLength = shapeMatrix.length;
            Color color = shape.getColor();
            for(int i = 0; i < shapeLength; i++){
                HBox hbox = new HBox();
                hbox.setPadding(new Insets(0,2,0,2));
                for(int j = 0; j < shapeMatrix[0].length; j++){
                    rect = new Rectangle();
                    rect.setWidth(40.0/shapeLength);
                    rect.setHeight(40.0/shapeLength);
                    rect.setArcWidth(4);
                    rect.setArcHeight(4);
                    if(shapeMatrix[i][j] == 0){
                        rect.setFill(Color.WHITE);
                        rect.setVisible(false);
                    }
                    else{
                        rect.setFill(color);
                        rect.setVisible(true);
                    }
                    hbox.getChildren().add(rect);
                }
                previewBox.getChildren().add(hbox);
            }
        }
        String cssStyle = "-fx-border-color: yellow;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 2;\n";
        previewBox.setPrefHeight(240);
        previewBox.setStyle(cssStyle);
        previewBox.setPadding(new Insets(8,8,8,8));
    }

    public VBox getPreviewBox() {
        return previewBox;
    }
}
