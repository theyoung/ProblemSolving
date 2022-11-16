package leetcode.backtracking;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/*
78. Subsets
https://leetcode.com/problems/subsets/?envType=study-plan&id=level-3
 */
//TODO permutation의 기본이다. masking, cascading, backtracking
public class Subsets {

    //backtracking
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(List.of());

        for(int i = 1; i <= nums.length; i++){
            backtracking(nums,0,new ArrayList<>(),result,i);
        }

        return result;
    }

    void backtracking(int[] nums, int start, List<Integer> list, List<List<Integer>> result, int length){
        if(list.size() == length){
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            backtracking(nums,i+1,list,result,length);
            list.remove(list.size()-1);
        }
        return;
    }


    //Cascading
    public List<List<Integer>> subsetsCascading(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(null);
        result.add(list);

        for(int num : nums){
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> copy = new ArrayList<>(result.get(i));
                copy.add(num);
                result.add(copy);
            }
        }

        return result;
    }

    public List<List<Integer>> subsetsMask(int[] nums) {

        int mask = 1 << nums.length;

        List<List<Integer>> result = new ArrayList<>();
        result.add(List.of());

        for (int i = 1; i < mask; i++) {
            List<Integer> tmp = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if(0 < (i & 1 << j)){
                    tmp.add(nums[j]);
                }
            }
            result.add(tmp);
        }

        return result;
    }


}
