package leetcode.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 835. Image Overlap
 * https://leetcode.com/problems/image-overlap/
 */
//2340
//0004
public class ImageOverlap {
    @Test
    void test(){
        ImageOverlap overlap = new ImageOverlap();
        Assertions.assertEquals(3, overlap.largestOverlap(new int[][]{{1,1,0},{0,1,0},{0,1,0}}, new int[][]{{0,0,0},{0,1,1},{0,0,1}}));
        Assertions.assertEquals(1, overlap.largestOverlap(new int[][]{{1}}, new int[][]{{1}}));
        Assertions.assertEquals(0, overlap.largestOverlap(new int[][]{{0}}, new int[][]{{0}}));

    }

    public int largestOverlap(int[][] img1, int[][] img2) {
        // img1의 [n-1,n-1]과 img2의 [0,0]부터
        // left -> right 비교
        //img1의 row를 하나 증가 시키고 left -right 비교

        //최대 크기 grid는 (n-1)*2 + n matrix를 그리고 그 중간에 img2를 넣고 나머지는 0으로 채운다.
        //img1을 0,0부터 (n-1)*2 + n 까지 이동 시키면서 중간 n matirx만 비교하면 됨.

        int large = (img2.length-1) * 2 + img2.length;
        int[][] matrix = new int[large][large];

        //matrix
        for (int row = 0; row < img2.length; row++) {
            int tRow = row + img2.length-1;
            for (int col = 0; col < img2.length; col++) {
                int tCol = col + img2.length-1;
                matrix[tRow][tCol] = img2[row][col];
            }
        }
        System.out.println(Arrays.deepToString(matrix));
        //start point
        int result = 0;
        for(int row = 0; row < large - (img2.length-1); row++){
            for(int col = 0; col < large - (img2.length-1); col++){
                result = Math.max(result, compare(img1, matrix, row, col));
            }
        }

        return result;
    }

    private int compare(int[][] img1, int[][] matrix, int sRow, int sCol) {
        int count = 0;
        for (int row = 0; row < img1.length; row++) {
            int tRow = sRow + row;
            for (int col = 0; col < img1.length; col++) {
                int tCol = sCol + col;
                if(img1[row][col] == matrix[tRow][tCol] && matrix[tRow][tCol] == 1) count++;
            }
        }
        return count;
    }
}
