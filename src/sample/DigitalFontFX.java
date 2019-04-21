package sample;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;
import java.util.List;

public class DigitalFontFX{
    int[][] A = {{0, 1, 1, 1, 0}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 1}};

    int[][] B = {{1, 1, 1, 1}, {1, 0,0, 1}, {1, 1, 1, 0}, {1, 0, 0, 1}, {1, 1, 1, 1,}};

    int[][] C = {{1, 1, 1,  1}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 1, 1, 1}};

    int[][] D = {{1, 1, 1, 0}, {1, 0, 0, 1}, {1, 0, 0, 1}, {1, 0, 0, 1}, {1, 1, 1, 0}};

    int[][] E = {{1, 1, 1, 1}, {1, 0, 0, 0}, {1, 1, 1, 0}, {1, 0, 0, 0}, {1, 1, 1, 1}};

    int[][] F = {{1, 1, 1, 1}, {1, 0, 0, 0}, {1, 1, 1, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}};

    int[][] G = {{1, 1, 1, 1}, {1, 0, 0, 0}, {1, 0, 1, 1}, {1, 0, 0, 1}, {1, 1, 1, 1}};

    int[][] H = {{1, 0, 0, 1}, {1, 0, 0, 1}, {1, 1, 1, 1}, {1, 0, 0, 1}, {1, 0, 0, 1}};

    int[][] I = {{1}, {0}, {1}, {1}, {1}};

    int[][] J = {{0, 0, 1}, {0, 0, 1}, {0, 0, 1}, {1, 0, 1}, {1, 1, 1}};

    int[][] K = {{1, 0, 0, 1}, {1, 0, 1, 0}, {1, 1, 0, 0}, {1, 0, 1, 0}, {1, 0, 0, 1}};

    int[][] L = {{1, 0, 0}, {1, 0, 0}, {1, 0, 0}, {1, 0, 0}, {1, 1, 1}};

    int[][] M = {{1, 0, 0, 0, 1}, {1, 1, 0, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 1}};

    int[][] N = {{1, 0, 0, 0, 1}, {1, 1, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 1, 1}, {1, 0, 0, 0, 1}};

    int[][] O = {{0, 1, 1, 0}, {1, 0, 0, 1}, {1, 0, 0, 1}, {1, 0, 0, 1}, {0, 1, 1, 0}};

    int[][] P = {{1, 1, 1, 1}, {1, 0, 0, 1}, {1, 1, 1, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}};

    int[][] Q = {{0, 1, 1, 0}, {1, 0, 0, 1}, {1, 0, 1, 1}, {1, 0, 0, 1}, {0, 1, 1, 0}};

    int[][] R = {{1, 1, 1, 1}, {1, 0, 0, 1}, {1, 1, 1, 0}, {1, 0, 0, 1}, {1, 0, 0, 1}};

    int[][] S = {{1, 1, 1, 1}, {1, 0, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 1}, {1, 1, 1, 1}};

    int[][] T = {{1, 1, 1}, {0, 1, 0}, {0, 1, 0}, {0, 1, 0}, {0, 1, 0}};

    int[][] U = {{1, 0, 0, 1}, {1, 0, 0, 1}, {1, 0, 0, 1}, {1, 0, 0, 1}, {0, 1, 1, 0}};

    int[][] V = {{1, 0, 0, 0, 1}, {1, 0, 0, 0, 1}, {1, 0, 0, 0, 1}, {0, 1, 0, 1, 0}, {0, 0, 1, 0, 0}};

    int[][] W = {{1, 0, 0, 0, 1}, {1, 0, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 1, 0, 1, 1}, {1, 0, 0, 0, 1}};

    int[][] X = {{1, 0, 0, 0, 1}, {0, 1, 0, 1, 0}, {0, 0, 1, 0, 0}, {0, 1, 0, 1, 0}, {1, 0, 0, 0, 1}};

    int[][] Y = {{ 1, 0, 1}, { 1, 0, 1}, {0, 1, 0}, {0, 1, 0}, {0, 1, 0}};

    int[][] Z = {{1, 1, 1, 1, 1}, {0, 0, 0, 0, 1}, {0, 0, 1, 0, 0}, {1, 0, 0, 0, 0}, {1, 1, 1, 1, 1}};

    int[][] zero = {{0,1,1,1,0},{1,0,0,1,1},{1,0,1,0,1},{1,1,0,0,1},{0,1,1,1,0}};
    int[][] one = {{1,1,0},{0,1,0},{0,1,0},{0,1,0},{1,1,1}};
    int[][] two = {{1,1,1,1},{1,0,0,1},{0,0,1,0},{0,1,0,0},{1,1,1,1}};
    int[][] three = {{1,1,1,1},{0,0,0,1},{0,1,1,0},{0,0,0,1},{1,1,1,1}};
    int[][] four = {{1,0,0,1},{1,0,0,1},{1,1,1,1},{0,0,0,1},{0,0,0,1}};
    int[][] five = {{1,1,1,1},{1,0,0,0},{1,1,1,1},{0,0,0,1},{1,1,1,1}};
    int[][] six = {{1,1,1,0},{1,0,0,0},{1,1,1,1},{1,0,0,1},{1,1,1,1}};
    int[][] seven = {{1,1,1},{0,0,1},{0,1,1},{0,0,1},{0,0,1}};
    int[][] eight = {{0,1,1,1,0},{1,0,0,0,1},{0,1,1,1,0},{1,0,0,0,1},{0,1,1,1,0}};
    int[][] nine = {{1,1,1,1},{1,0,0,1},{1,1,1,1},{0,0,0,1},{0,1,1,1}};
    int[][] period = {{0},{0},{0},{0},{1}};
    int[][] pp = {{0},{1},{0},{1},{0}};
    int[][] exclam = {{1},{1},{1},{0},{1}};
    int[][] space = {{0,0},{0,0},{0,0},{0,0},{0,0}};


    List<Integer[][]> digitalAlphabeth = new ArrayList<>();

    public DigitalFontFX() {

    }

    public HBox createText(String words, Color fill){
        String sentence = words.toUpperCase();
        HBox groupedLetters = new HBox();
        groupedLetters.setSpacing(5);

        for(int i = 0; i < words.length(); i++){
            switch(sentence.charAt(i)){
                case 'A':
                    groupedLetters.getChildren().add(createLetter(A, fill));
                    break;
                case 'B':
                    groupedLetters.getChildren().add(createLetter(B, fill));
                    break;
                case 'C':
                    groupedLetters.getChildren().add(createLetter(C, fill));
                    break;
                case 'D':
                    groupedLetters.getChildren().add(createLetter(D, fill));
                    break;
                case 'E':
                    groupedLetters.getChildren().add(createLetter(E, fill));
                    break;
                case 'F':
                    groupedLetters.getChildren().add(createLetter(F, fill));
                    break;
                case 'G':
                    groupedLetters.getChildren().add(createLetter(G, fill));
                    break;
                case 'H':
                    groupedLetters.getChildren().add(createLetter(H, fill));
                    break;
                case 'I':
                    groupedLetters.getChildren().add(createLetter(I, fill));
                    break;
                case 'J':
                    groupedLetters.getChildren().add(createLetter(J, fill));
                    break;
                case 'K':
                    groupedLetters.getChildren().add(createLetter(K, fill));
                    break;
                case 'L':
                    groupedLetters.getChildren().add(createLetter(L, fill));
                    break;
                case 'M':
                    groupedLetters.getChildren().add(createLetter(M, fill));
                    break;
                case 'N':
                    groupedLetters.getChildren().add(createLetter(N, fill));
                    break;
                case 'O':
                    groupedLetters.getChildren().add(createLetter(O, fill));
                    break;
                case 'P':
                    groupedLetters.getChildren().add(createLetter(P, fill));
                    break;
                case 'Q':
                    groupedLetters.getChildren().add(createLetter(Q, fill));
                    break;
                case 'R':
                    groupedLetters.getChildren().add(createLetter(R, fill));
                    break;
                case 'S':
                    groupedLetters.getChildren().add(createLetter(S, fill));
                    break;
                case 'T':
                    groupedLetters.getChildren().add(createLetter(T, fill));
                    break;
                case 'U':
                    groupedLetters.getChildren().add(createLetter(U, fill));
                    break;
                case 'V':
                    groupedLetters.getChildren().add(createLetter(V, fill));
                    break;
                case 'W':
                    groupedLetters.getChildren().add(createLetter(W, fill));
                    break;
                case 'X':
                    groupedLetters.getChildren().add(createLetter(X, fill));
                    break;
                case 'Y':
                    groupedLetters.getChildren().add(createLetter(Y, fill));
                    break;
                case 'Z':
                    groupedLetters.getChildren().add(createLetter(Z, fill));
                    break;
                case '1':
                    groupedLetters.getChildren().add(createLetter(one, fill));
                    break;
                case '2':
                    groupedLetters.getChildren().add(createLetter(two, fill));
                    break;
                case '3':
                    groupedLetters.getChildren().add(createLetter(three, fill));
                    break;
                case '4':
                    groupedLetters.getChildren().add(createLetter(four, fill));
                    break;
                case '5':
                    groupedLetters.getChildren().add(createLetter(five, fill));
                    break;
                case '6':
                    groupedLetters.getChildren().add(createLetter(six, fill));
                    break;
                case '7':
                    groupedLetters.getChildren().add(createLetter(seven, fill));
                    break;
                case '8':
                    groupedLetters.getChildren().add(createLetter(eight, fill));
                    break;
                case '9':
                    groupedLetters.getChildren().add(createLetter(nine, fill));
                    break;
                case '0':
                    groupedLetters.getChildren().add(createLetter(zero, fill));
                    break;
                case '.':
                    groupedLetters.getChildren().add(createLetter(period, fill));
                    break;
                case ':':
                    groupedLetters.getChildren().add(createLetter(pp, fill));
                    break;
                case '!':
                    groupedLetters.getChildren().add(createLetter(exclam, fill));
                    break;
                case ' ':
                    groupedLetters.getChildren().add(createLetter(space, fill));
                    break;
            }
        }

        return groupedLetters;
    }

    public VBox createLetter(int[][] letterArray, Color fill){
        VBox letter = new VBox();
        double LETTER_WIDTH = 100;
        double LETTER_HEIGHT = 120;
        int arrayWidth;
        int arrayHeight = 5;
        for(int[] array: letterArray){
            arrayWidth = array.length;
            if(arrayWidth <= 2){
                LETTER_WIDTH = 25;
            }
            HBox row = new HBox();
            for(int i = 0; i < arrayWidth; i++){
                Rectangle rect = new Rectangle();
                rect.setWidth(LETTER_WIDTH / arrayWidth);
                rect.setHeight(LETTER_HEIGHT / arrayHeight);
                rect.setArcWidth(5);
                rect.setArcHeight(5);
                if(array[i] == 1){
                    rect.setFill(fill);
                    rect.setVisible(true);
                    rect.setStrokeType(StrokeType.INSIDE);
                    rect.setStroke(Color.BLACK);
                }else{
                    rect.setFill(Color.TRANSPARENT);
                }
                row.getChildren().add(rect);
            }
            letter.getChildren().add(row);
        }
        return letter;
    }




}
