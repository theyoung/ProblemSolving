package leetcode.matrix;

/*
blurFaces
https://app.codesignal.com/company-challenges/verkada/
 */
//18분
//TODO 10분내로 풀어야 한다. matrix 시간 줄이는 방법 없을까...
public class BlurFaces {
    double[][] solution(int[][] img) {

/*
represented as a matrix of integers, where each integer corresponds to a color.
 The number in the ith (0-based) row and jth (0-based) column represents the color of the pixel in the ith row and jth column of the image.
 you need to replace each number of the matrix with the average of the numbers in the neighboring cells.
 only the 8 surrounding neighbors
*/
        double[][] newImg = new double[img.length][img[0].length];
        int[][] move = new int[][]{{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
        for(int row = 0; row < img.length; row++){
            for(int col = 0; col < img[0].length; col++){
                int count = 0;
                int sum = 0;

                for(int[] next : move){
                    int nRow = row + next[0];
                    int nCol = col + next[1];

                    if(nRow < 0 || nCol < 0 || img.length <= nRow || img[0].length <= nCol) continue;
                    count++;
                    sum+=img[nRow][nCol];
                }

                newImg[row][col] = sum/(double)count;

            }
        }
        return newImg;

    }
}
