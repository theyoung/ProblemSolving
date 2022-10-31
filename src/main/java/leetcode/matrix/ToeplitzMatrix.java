package leetcode.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 766. Toeplitz Matrix
 * https://leetcode.com/problems/toeplitz-matrix/
 */
//0920
//0924
public class ToeplitzMatrix {
    @Test
    void test(){
        ToeplitzMatrix matrix = new ToeplitzMatrix();
        Assertions.assertEquals(true, matrix.isToeplitzMatrix(new int[][]{{1,2,3,4}, {5,1,2,3}, {9,5,1,2}}));
        Assertions.assertEquals(false, matrix.isToeplitzMatrix(new int[][]{{2,2,3,4}, {5,1,2,3}, {9,5,1,2}}));
    }
    public boolean isToeplitzMatrix(int[][] matrix) {

        for (int row = 1; row < matrix.length; row++) {
            for (int col = 1; col < matrix[0].length; col++) {
                if(matrix[row][col] != matrix[row-1][col-1]) return false;
            }
        }

        return true;
    }
}
