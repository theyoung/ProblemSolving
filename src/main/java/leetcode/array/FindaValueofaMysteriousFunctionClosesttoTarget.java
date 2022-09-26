package leetcode.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 1521. Find a Value of a Mysterious Function Closest to Target
 * https://leetcode.com/problems/find-a-value-of-a-mysterious-function-closest-to-target/
 */

public class FindaValueofaMysteriousFunctionClosesttoTarget {
    @Test
    void test(){
        FindaValueofaMysteriousFunctionClosesttoTarget find = new FindaValueofaMysteriousFunctionClosesttoTarget();
        Assertions.assertEquals(2,find.closestToTarget(new int[]{9, 12, 3, 7, 15}, 5));
        Assertions.assertEquals(999999,find.closestToTarget(new int[]{1000000,1000000,1000000}, 1));
        Assertions.assertEquals(0,find.closestToTarget(new int[]{1,2,4,8,16}, 0));
    }
    //23:04
    //TODO : 다시 풀어야 한다.
    public int closestToTarget(int[] arr, int target) {
        return 0;
    }

}
