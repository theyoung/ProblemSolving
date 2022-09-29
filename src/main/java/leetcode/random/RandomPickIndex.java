package leetcode.random;

import java.util.*;

public class RandomPickIndex {
    Map<Integer, List<Integer>> map;
    int[] nums;
    public RandomPickIndex(int[] nums) {
        map = new HashMap<>();
        this.nums = nums;
        for(int i = 0; i < nums.length; i++){
            map.computeIfAbsent(nums[i],(k)-> new ArrayList<>());
            map.get(nums[i]).add(i);
        }
    }

    public int pick(int target) {
        List<Integer> list = map.get(target);

        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    public int reservoirPick(int target) {
        int index = -1;
        int count = 0;
        Random random = new Random();

        for (int i = 0; i < this.nums.length; i++) {
            if (target == nums[i]) {
                count++;
                if (random.nextInt(count) == 0) {
                    index = i;
                }
            }
        }

        return index;
    }
}
