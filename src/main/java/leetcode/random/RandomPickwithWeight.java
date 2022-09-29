package leetcode.random;

import java.util.Random;

public class RandomPickwithWeight {

    int[] arr;
    int total = 0;

    public RandomPickwithWeight(int[] w) {
        arr = new int[w.length];

        for(int i = 0; i < w.length; i++){
            total += w[i];
            arr[i] = total;
        }

        // System.out.println(Arrays.toString(arr));
        // System.out.println(total);
    }

    public int pickIndex() {
        Random random = new Random();
        int target = random.nextInt(total)+1;
        // System.out.println(target);
        // int target = 6;
        int left = 0;
        int right = arr.length-1;

        while(left < right){
            int center = left + (right - left)/2;
            if(arr[center] == target){
                return center;
            }

            if(arr[center] < target){
                left = center + 1;
            } else { //target < center
                right = center;
            }
        }
        return left;
    }
}
