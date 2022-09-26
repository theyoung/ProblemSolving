package leetcode.matrix;

import leetcode.math.UglyNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 566. Reshape the Matrix
 * https://leetcode.com/problems/reshape-the-matrix/
 */

public class ReshapetheMatrix {

    @Test
    void test(){
        ReshapetheMatrix matrix = new ReshapetheMatrix();
        int[][] result = matrix.matrixReshape(new int[][]{{1,2},{3,4}},1,4);
        int[][] target = new int[][]{{1,2,3,4}};

        for (int row = 0; row < result.length; row++) {
            System.out.println(Arrays.toString(result[row]));
            Assertions.assertArrayEquals(target[row],result[row]);
        }

    }

    public int[][] matrixReshape(int[][] mat, int r, int c) {
        if(!checkPossible(mat,r,c)) return mat;

        int[][] result = new int[r][c];

        int nRow = 0;
        int nCol = 0;

        for (int row = 0; row < mat.length; row++) {
            for(int col = 0; col < mat[0].length; col++){
                if(c <= nCol){
                    nRow++;
                    nCol = 0;
                }
                result[nRow][nCol] = mat[row][col];
                nCol++;
            }
        }

        return result;
    }

    private boolean checkPossible(int[][] mat, int r, int c) {
        int count = r * c;
        int target = mat.length * mat[0].length;

        return count == target;
    }
}
