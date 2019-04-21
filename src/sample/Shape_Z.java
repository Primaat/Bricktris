package sample;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Shape_Z extends Bricktrisminoes {
    /**
     * Class containing a shape, a list of shapeMatrices and a color.
     */
    private final List<int[][]> shapematrix = new ArrayList<>();
    private final Color color = Color.RED;

    public Shape_Z(){
        shapematrix.add(new int[][] {
                {1,1,0},
                {0,1,1},
                {0,0,0}
        });
        shapematrix.add(new int[][] {
                {0,0,1},
                {0,1,1},
                {0,1,0}
        });
        shapematrix.add(new int[][] {
                {0,0,0},
                {1,1,0},
                {0,1,1}
        });
        shapematrix.add(new int[][] {
                {0,1,0},
                {1,1,0},
                {1,0,0}
        });
    }
    @Override
    public List<int[][]> getShapeMatrix() {
        return shapematrix;
    }

    public Color getColor() {
        return color;
    }
}
