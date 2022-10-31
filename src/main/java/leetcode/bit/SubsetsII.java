package leetcode.bit;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 90. Subsets II
 * https://leetcode.com/problems/subsets-ii/?envType=study-plan&id=level-3
 */
//1148
//TODO bitmasking과 backtracking을 이용한 permutation 중요하다!
public class SubsetsII {
    @Test
    void test(){
        SubsetsII subsetsII = new SubsetsII();
        System.out.println(subsetsII.subsetsWithDup(new int[]{1,2,2}).toString());
        System.out.println(subsetsII.subsetsWithDup(new int[]{1,2,2,3,3,3}).toString());
        System.out.println(subsetsII.subsetsWithDup(new int[]{0}).toString());
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 2^10 임으로 모든 경우의 수를 처리하면 된다.
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        HashSet<String> seen = new HashSet<>();
        for(int i = 0; i < Math.pow(2,nums.length); i++){
            // 0000 0101
            // 1111 1010
            // 1111 1011 (-i)
            // 0000 0101  & 1111 1011 = 0001
            LinkedList<Integer> list = new LinkedList<>();
            for (int bit = 0; bit < nums.length; bit++) {
                int reverse = nums.length - bit - 1;
                if( 0 < (1 << reverse & i)){
                    list.addFirst(nums[reverse]);
                }
            }
            StringBuilder sb = new StringBuilder();
            for(int val : list){
                sb.append(val);
                sb.append(",");
            }
            String key = sb.toString();
            if(seen.contains(key)) continue;
            seen.add(key);
            result.add(list);
        }

        return result;
    }


    //backtracking
    List<List<Integer>> result;
    public List<List<Integer>> subsetsWithDupIterative(int[] nums) {
        Arrays.sort(nums);
        result = new ArrayList<>();
        helper((1<<nums.length)-1, nums, 0, new ArrayList<>());
        return result;
    }

    void helper(int mask, int[] nums, int left, List<Integer> list){
        result.add(new ArrayList<>(list));//기존 값

        //기존값에 추가되는
        for (int i = left; i < nums.length; i++) {
            list.add(nums[i]);
            helper(mask ^ (1 << i & mask), nums, i + 1, list);
            list.remove(list.size()-1);
            while(i+1 < nums.length && nums[i] == nums[i+1]) i++;
        }
    }

}
