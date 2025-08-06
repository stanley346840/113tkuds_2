import java.util.Arrays;

public class MatrixCalculator {

    // 矩陣加法
    public static int[][] add(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = A[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[i][j] = A[i][j] + B[i][j];

        return result;
    }

    // 矩陣乘法
    public static int[][] multiply(int[][] A, int[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;
        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++)
            for (int j = 0; j < colsB; j++)
                for (int k = 0; k < colsA; k++)
                    result[i][j] += A[i][k] * B[k][j];

        return result;
    }

    // 矩陣轉置
    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transposed = new int[cols][rows];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                transposed[j][i] = matrix[i][j];

        return transposed;
    }

    // 找出最大值與最小值
    public static int[] findMinMax(int[][] matrix) {
        int min = matrix[0][0];
        int max = matrix[0][0];

        for (int[] row : matrix)
            for (int value : row) {
                if (value < min) min = value;
                if (value > max) max = value;
            }

        return new int[]{min, max};
    }

    // 輸出矩陣
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix)
            System.out.println(Arrays.toString(row));
    }

    // 主程式測試
    public static void main(String[] args) {
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] matrixB = {
            {7, 8, 9},
            {10, 11, 12}
        };

        int[][] matrixC = {
            {1, 2},
            {3, 4},
            {5, 6}
        };

        System.out.println("矩陣 A：");
        printMatrix(matrixA);

        System.out.println("\n矩陣 B：");
        printMatrix(matrixB);

        System.out.println("\n矩陣 A + B：");
        printMatrix(add(matrixA, matrixB));

        System.out.println("\n矩陣 A × C：");
        printMatrix(multiply(matrixA, matrixC));

        System.out.println("\n矩陣 A 的轉置：");
        printMatrix(transpose(matrixA));

        int[] minMax = findMinMax(matrixB);
        System.out.println("\n矩陣 B 的最大值：" + minMax[1]);
        System.out.println("矩陣 B 的最小值：" + minMax[0]);
    }
}
