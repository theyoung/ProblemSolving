package leetcode.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 2076. Process Restricted Friend Requests
 * https://leetcode.com/problems/process-restricted-friend-requests/
 */
//TODO union find를 이용한 문제이다. node로 접근하면 어려운데 uf로 접근하면 쉽다.
public class ProcessRestrictedFriendRequests {
    @Test
    void test(){
        ProcessRestrictedFriendRequests requests = new ProcessRestrictedFriendRequests();
        Assertions.assertArrayEquals(new boolean[]{true,false}, requests.friendRequests(3,new int[][]{{0,1}}, new int[][]{{0,2},{2,1}}));
        Assertions.assertArrayEquals(new boolean[]{true,false}, requests.friendRequests(3,new int[][]{{0,1}}, new int[][]{{1,2},{0,2}}));
        Assertions.assertArrayEquals(new boolean[]{true,false,true,false}, requests.friendRequests(5,new int[][]{{0,1},{1,2},{2,3}}, new int[][]{{0,4},{1,2},{3,1},{3,4}}));
        Assertions.assertArrayEquals(new boolean[]{true,true,true,true,true}, requests.friendRequests(7,new int[][]{{0,6},{6,2}}, new int[][]{{0,2},{2,3},{0,2},{6,4},{6,4}}));
    }

    class UnionFind {
        int[] union;

        public UnionFind(int n){
            union = new int[n];
            for(int i = 0; i < n; i++) union[i] = i;
        }

        public void union(int source, int target){
            source = find(source);
            target = find(target);

            if(source == target) return;

            union[target] = source;
        }

        public int find(int source){
            if(union[source] == source) return source;
            union[source] = find(union[source]);
            return union[source];
        }

    }

    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        boolean[] result = new boolean[requests.length];

        UnionFind uf = new UnionFind(n);
        int i = 0;
        for (int[] request : requests) {
            int source = uf.find(request[0]);
            int target = uf.find(request[1]);
            if(source == target) {
                result[i] = true;
                i++;
                continue;
            }
            boolean fail = false;
            for(int[] rest : restrictions){
                int me = uf.find(rest[0]);
                int you = uf.find(rest[1]);
                if((me == source && you == target) || (you == source && me == target)){
                    fail = true;
                    break;
                }
            }
            if(!fail){
                uf.union(source,target);
            }
            result[i] = !fail;
            i++;
        }
        return result;
    }
}
