package leetcode.array;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/*
count-of-pairs-in-an-array-whose-sum-is-a-perfect-square
https://www.geeksforgeeks.org/count-of-pairs-in-an-array-whose-sum-is-a-perfect-square/
 */
//TODO Map을 사용하면 O(Constant * N)이 된다
public class Countofpairsinanarraywhosesumisaperfectsquare {
    @Test
    void test(){
        System.out.println(this.countPairs(new int[]{2, 3, 6, 9, 10, 20 }));
//        System.out.println(this.countPairs(new int[]{0,4}));
    }

    int countPairs(int arr[]){
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for(int val : arr){
            max = Math.max(max, val);
            map.put(val, map.getOrDefault(val,0)+1);
        }
        Set<Integer> set = new HashSet<>();
        for(int i = 0 ; i <= max; i++){
            set.add(i*i);
        }
        int count = 0;
        int singleCount = 0;
        for(int val : set){
            for(int find : arr){
                count += map.getOrDefault(val - find,0);
//                System.out.println(val+ " " + find + " = " + count);
                if(val == find+find) {
                    singleCount++;
                    count--;
                }
            }
        }

        return (count/2) + singleCount;
    }
}
