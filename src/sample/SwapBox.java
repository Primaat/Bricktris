package sample;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SwapBox {
    /**
     * Creates a VBox containing a swapped out shape for later use.
     */
    String id = "swapBox";

    // the shape contained in the swapbox
    Bricktrisminoes shape;

    // the color of the contained shape
    Color color;

    // the matrix of the contained shape
    int[][] shapeMatrix;

    // an empty matrix at the start of the game
    int[][] emptyMatrix = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
    VBox vbox;

    public SwapBox(Bricktrisminoes shape) {
        this.shape = shape;
        this.color = shape.getColor();
        this.shapeMatrix = shape.getShapeMatrix().get(0);
        setSwapBox();
    }

    public SwapBox() {
        /**
         * constructor for empty swapBox only!!!
         */
    }

    public void setSwapBox(){
        /**
         * Creates a swap box for the swapped shapes         *
         */
        vbox = new VBox();
        for(int i = 0; i < shapeMatrix.length; i++){
            HBox hbox = new HBox();
            for(int j = 0; j < shapeMatrix[0].length; j++){
                Rectangle rect = new Rectangle();
                rect.setWidth(100/shapeMatrix.length);
                rect.setHeight(100/shapeMatrix.length);
                if(shapeMatrix[i][j] == 1){
                    rect.setFill(color);
                    rect.setArcHeight(8);
                    rect.setArcWidth(8);
                }
                else{
                    rect.setFill(Color.TRANSPARENT);
                    rect.setVisible(false);
                }
                hbox.getChildren().add(rect);
            }
            vbox.getChildren().add(hbox);
        }
        vbox.setPadding(new Insets(10,10,-20,10));
        String cssStyle = "-fx-border-color: yellow;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 2;\n";
        vbox.setStyle(cssStyle);
        vbox.setId(id);
        //this.setPrefHeight(120);
    }

    public VBox getSwapBox(){
        return vbox;
    }

    public Color getColor() {
        return color;
    }

    public Bricktrisminoes getShape() {
        return shape;
    }

    public int[][] getShapeMatrix() {
        return shapeMatrix;
    }

    public void setShapeMatrix(int[][] shapeMatrix) {
        this.shapeMatrix = shapeMatrix;
    }

    public VBox getEmptySwapBox(){
        /**
         * returns an vbox containing an empty swap box
         */
        setShapeMatrix(emptyMatrix);
        setSwapBox();
        return vbox;
    }

}
