package medium;

/**
 * Created by rachelren on 12/18/15.
 * Leetcode Problem Rotate Image
 */
public class Rotate {
    public void rotate(int[][] matrix){
        if (matrix.length == 0 || matrix.length == 1){
            ;
        }else {
            int half = matrix.length / 2;
            int evenOdd = matrix.length % 2;
            for (int i = 0; i < half; i++) {
                if (evenOdd == 1 && i == half) {
                    continue;
                } else {
                    for (int j = i; j < matrix.length - i - 1; j++) {
                        int temp = matrix[i][j];
                        int length = matrix.length - 1 - i;
                        matrix[i][j] = matrix[length - j + i][i];
                        matrix[length - j + i][i] = matrix[length][length - j + i];
                        matrix[length][length - j + i] = matrix[j][length];
                        matrix[j][length] = temp;
                    }
                }
            }
        }
    }

    public void printMatrix(int[][] matrix){
        String result = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (j < matrix.length - 1) {
                    result += matrix[i][j] + ",";
                } else {
                    result += matrix[i][j] + "\n";
                }
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args){
        Rotate r = new Rotate();
        int[][] matrix = new int[4][4];
        int[][] matrix1 = new int[0][0];
        int[][] matrix2 = new int[1][1];
        matrix2[0][0] = 0;
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++){
                matrix[i][j] = (i + j);
            }
        }
        System.out.println("Original Matrix");
        r.printMatrix(matrix);

        r.rotate(matrix1);
        r.rotate(matrix2);
        r.rotate(matrix);

        System.out.println("After Rotation");
        r.printMatrix(matrix);
    }
}

