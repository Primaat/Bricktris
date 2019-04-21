import java.util.Arrays;

public class Transposing {
    int rows = 0;
    int cols = 0;
    public static void main(String[] args) {
        int[][] a = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        //new Transposing().inPlaceTranspose(a);
        int[][] b = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
        new Transposing().outPlaceTranspose(b);
        int[][] c = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        new Transposing().unEvenTranspose(c);
        int[][] d = {{0, 0, 0, 0}, {0, 1, 1, 0}, {1, 1, 0, 0}, {0, 0, 0, 0}};
        new Transposing().rotateClockWise(d);
        int[][] e = {{0, 0, 1, 0}, {0, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        new Transposing().rotateCounterClockWise(e);
        new Transposing().rotateClockWise(a);
        new Transposing().rotateClockWise(a);
        new Transposing().rotateClockWise(a);
        new Transposing().rotateClockWise(a);



    }

    public void inPlaceTranspose(int[][] matrix){
        /*
        * This method transposes a matrix within itself
        * */

        for (int[] row : matrix) {
            System.out.println("A Before: " + Arrays.toString(row));
        }
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = i + 1; j < matrix.length; j++) { // transposing in same array must be: int j = i+1
                matrix[i][j] = matrix[i][j] + matrix[j][i];
                matrix[j][i] = matrix[i][j] - matrix[j][i];
                matrix[i][j] = matrix[i][j] - matrix[j][i];
            }
        }
        System.out.println("\n");
        for (int[] row : matrix) {
            System.out.println("A After: " + Arrays.toString(row));
        }
    }

    public void outPlaceTranspose(int[][] matrix){
        /*
        * This method transposes a matrix in a new array
        * */

        for (int[] row : matrix) {
            System.out.println("B Before: " + Arrays.toString(row));
        }
        int[][] x = new int[matrix.length][matrix[0].length]; // new array
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                x[i][j] = matrix[j][i]; // transposing into a new array
            }
        }
        System.out.println("\n");
        for (int[] row : x) {
            System.out.println("B After: " + Arrays.toString(row));
        }
    }

    public void unEvenTranspose(int[][] matrix){
        /*
        * This method transposes an array with different row / col lengths in a new array
        * */
        for (int[] row : matrix) {
            System.out.println("C Before: " + Arrays.toString(row));
        }
        int[][] y = new int[matrix[0].length][matrix.length]; // new array

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                y[j][i] = matrix[i][j]; // transposing into a new array
            }
        }
        System.out.println("\n");
        for (int[] row : y) {
            System.out.println("C After: " + Arrays.toString(row));
        }
    }

    public void rotateClockWise(int[][] matrix){
        /*
        This method rotates a matrix counter clock wise by first transposing the matrix within itself
         and then mirroring the rows.
         */

        for (int[] row : matrix) {
            System.out.println("D Before: " + Arrays.toString(row));
        }
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = i + 1; j < matrix.length; j++) { // transposing in same array must be: int j = i+1
                matrix[i][j] = matrix[i][j] + matrix[j][i];
                matrix[j][i] = matrix[i][j] - matrix[j][i];
                matrix[i][j] = matrix[i][j] - matrix[j][i];
            }
        }
        for (int[] row : matrix) {
            System.out.println("D After transpose: " + Arrays.toString(row));
        }
        System.out.println("\n");
        rows = matrix.length;
        cols = matrix[0].length;
        // Then rotate Clockwise
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < (cols / 2); col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[row][cols - 1 - col];
                matrix[row][cols - 1 - col] = temp;
            }
        }
        for (int[] row : matrix) {
            System.out.println("D After rotation: " + Arrays.toString(row));
        }
    }

    public void rotateCounterClockWise(int[][] matrix){
        /*
        This method rotates a matrix counter clock wise by first mirroring the rows
        and the transposing the matrix within itself
         */

        for (int[] row : matrix) {
            System.out.println("E Before: " + Arrays.toString(row));
        }
        rows = matrix.length;
        cols = matrix[0].length;
        // first rotate counter clockwise
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < (cols / 2); col++) {
                int temp = matrix[row][col];
                matrix[row][col] = matrix[row][cols - 1 - col];
                matrix[row][cols - 1 - col] = temp;
            }
        }
        for (int[] row : matrix) {
            System.out.println("E After Rotation: " + Arrays.toString(row));
        }
        System.out.println("\n");
        // then transpose
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = i + 1; j < matrix.length; j++) { // transposing in same array must be: int j = i+1
                matrix[i][j] = matrix[i][j] + matrix[j][i];
                matrix[j][i] = matrix[i][j] - matrix[j][i];
                matrix[i][j] = matrix[i][j] - matrix[j][i];
            }
        }
        System.out.println("\n");

        for (int[] row : matrix) {
            System.out.println("E After Transpose: " + Arrays.toString(row));
        }
    }
}
