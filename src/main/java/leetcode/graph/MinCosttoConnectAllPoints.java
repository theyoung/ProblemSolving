package leetcode.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1584. Min Cost to Connect All Points
 * https://leetcode.com/problems/min-cost-to-connect-all-points/
 */
public class MinCosttoConnectAllPoints {

    @Test
    void test(){
        MinCosttoConnectAllPoints min = new MinCosttoConnectAllPoints();

        Assertions.assertEquals(20,min.minCostConnectPoints(new int[][]{{0,0},{2,2},{3,10},{5,2},{7,0}}));
        Assertions.assertEquals(18,min.minCostConnectPoints(new int[][]{{3,12}, {-2,5}, {-4,1}}));
    }


    public int minCostConnectPoints(int[][] points) {

        List<int[]> list = new ArrayList<>();

        getCosts(points, list);

        int sum = 0;

        //2. 모든 포인트에 대한 Cost를 포인트별로 Sorting한다. N^logN
        list.sort((a, b) -> a[0] - b[0]);

        //3. Cost가 가장 작은 포인트 별로 Grouping한다.
        // Grouping시 Cost를 sum한다
        UnionFind unionFind = new UnionFind(points.length);

        int edgeCount = 0;
        int pointer = 0;

        while(edgeCount < (points.length-1)){
            int[] edge = list.get(pointer);
            if (unionFind.union(edge[1], edge[2])) {
                edgeCount++;
                sum += edge[0];
            }
            pointer++;
        }

        return sum;
    }

    //1. 모든 포인트에 대한 Cost를 산출한다. N^2
    //return [start point][end point] = cost
    int[][] getCosts(int[][] points, List<int[]> list){
        int[][] costs = new int[points.length][points.length];

        //동일 vertex에 대해서는 다시 길이를 찾지 않게됨 각 vertex별 최소 길이만을 찾게됨
        for(int start = 0; start < points.length; start++){
            for (int end = start+1; end < points.length; end++) {
                costs[start][end] = Math.abs(points[start][0] - points[end][0]) + Math.abs(points[start][1] - points[end][1]);
                list.add(new int[]{costs[start][end],start,end});
            }
        }
        return costs;
    }

    class UnionFind {

        int[] arr;

        public UnionFind(int size) {
            arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = i;
            }
        }

        public boolean union(int source, int target){
            int sourceTop = find(source);
            int targetTop = find(target);

            //if cyclic
            if(sourceTop == targetTop) return false;
            else arr[targetTop] = sourceTop;
            return true;
        }

        public int find(int source){
            if(arr[source] == source) return source;
            arr[source] = find(arr[source]);
            return arr[source];
        }
    }

}

