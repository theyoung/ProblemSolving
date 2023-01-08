package leetcode.math;
import java.util.*;
/*
149. Max Points on a Line
https://leetcode.com/problems/max-points-on-a-line/description/
 */
public class MaxPointsonaLine {
    // 각 포인트에서 constant를 신경쓸 필요없이
    // slope만 check하는 방법으로 해결되었다
    public int maxPoints(int[][] points) {
        int max = 0;
        for(int i = 0; i < points.length-1; i++){
            Map<Double,Integer> map = new HashMap<>();

            for(int j = i+1; j < points.length; j++){

                double slope = getSlope(points[i],points[j]);
                map.put(slope,map.getOrDefault(slope,0)+1);

                if(max < map.get(slope)) max = map.get(slope);
            }
        }
        return max+1;
    }

    double getSlope(int[] a, int[] b){
        if(a[0] == b[0]) return Double.MAX_VALUE;
        if(a[1] == b[1]) return 0.;
        return (b[1] - a[1]) / (double)(b[0] - a[0]);
    }

    //아래는 constant 변화량 까지 계산하다 보니 부적합했다
    public int maxPointsN(int[][] points) {
        if(points.length <= 2) return points.length;
        //모든 점과 점을 연결해 보면 된다
        // y = 기울기x + c
        // if x == 0 then c = y
        //두 점이 주어지면 1차 방정식을 만들어라
        // (3,4) (5,5)
        // 기울기 = y의 변화량 / x의 변화량
        // 5-4/5-3 = 1/2
        // 4 = 0.5*3 + c
        // c = 4 - 1.5
        // c = 2.5
        // y = 0.5x + 2.5
        Arrays.sort(points, (a,b)->{
            if(a[0]!=b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        Map<Integer,Integer> xLines = new HashMap<>();
        Map<Integer,Integer> yLines = new HashMap<>();

        int max = 0;
        for(int[] point : points){
            xLines.put(point[0], xLines.getOrDefault(point[0],0)+1);
            max = Math.max(max, xLines.get(point[0]));
            yLines.put(point[1], yLines.getOrDefault(point[1],0)+1);
            max = Math.max(max, yLines.get(point[1]));
        }

        // System.out.println(Arrays.deepToString(points));

        for(int i = 0; i < points.length-1; i++){
            for(int j = i+1; j < points.length; j++){
                double bias = getBias(points[i], points[j]);
                double cont = getConst(bias, points[i]);
                // System.out.println(bias + " " + cont);
                max = Math.max(max,getCount(bias, cont, points));
            }
        }

        return max;
    }

    int getCount(double bias, double cont, int[][] points){
        int count = 0;
        for(int[] point : points){
            if(lieOnSameLine(bias, cont, point)) count++;
        }
        return count;
    }

    double getBias(int[] a, int[] b){
        if(b[0] == a[0] || b[1] == a[1]) return 0;
        // System.out.println(Math.round((double)(b[1] - a[1]) / (double)(b[0] - a[0])*100)/100.);
        return Math.round((double)(b[1] - a[1]) / (double)(b[0] - a[0])*100)/100.;
    }

    double getConst(double bias, int[] a){
        return Math.round((a[1] - bias*a[0])*100)/100;
    }

    boolean lieOnSameLine(double bias, double cont, int[] point){
        return 0 == Math.round(point[0] * bias + cont  - point[1]);
    }
}
