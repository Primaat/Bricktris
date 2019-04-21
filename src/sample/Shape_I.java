package sample;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Shape_I extends Bricktrisminoes {
    /**
     * Class containing a shape, a list of shapeMatrices and a color.
     */
    private final List<int[][]> shapeMatrix = new ArrayList<>();
    private final Color color = Color.SKYBLUE;
    public Shape_I(){
        shapeMatrix.add(new int[][] {
                {0,0,0,0},
                {1,1,1,1},
                {0,0,0,0},
                {0,0,0,0}
        });
        shapeMatrix.add(new int[][] {
                {0,0,1,0},
                {0,0,1,0},
                {0,0,1,0},
                {0,0,1,0}
        });
        shapeMatrix.add(new int[][] {
                {0,0,0,0},
                {0,0,0,0},
                {1,1,1,1},
                {0,0,0,0}
        });
        shapeMatrix.add(new int[][] {
                {0,1,0,0},
                {0,1,0,0},
                {0,1,0,0},
                {0,1,0,0}
        });
    }
    @Override
    public List<int[][]> getShapeMatrix() {
        return shapeMatrix;
    }

    public Color getColor() {
        return color;
    }
}
