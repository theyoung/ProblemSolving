package leetcode.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 219. Contains Duplicate II
 * https://leetcode.com/problems/contains-duplicate-ii/
 */
//TODO Set을 이용하는 방법도 있다.
//1423
//1429
public class ContainsDuplicateII {
    @Test
    void test(){
        ContainsDuplicateII con = new ContainsDuplicateII();
        Assertions.assertEquals(true, con.containsNearbyDuplicate(new int[]{1,2,3,1}, 3));
        Assertions.assertEquals(true, con.containsNearbyDuplicate(new int[]{1,0,1,1}, 1));
        Assertions.assertEquals(false, con.containsNearbyDuplicate(new int[]{1,2,3,1,2,3}, 2));
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])){
                if(i - map.get(nums[i]) <= k) return true;
            }
            map.put(nums[i], i);
        }

        return false;
    }
}
