package leetcode.search;

import java.util.Arrays;

/*
528. Random Pick with Weight
https://leetcode.com/problems/random-pick-with-weight/?envType=study-plan&id=level-3
 */
//2203
//2233
//TODO Arrays.binarySearch를 활용하는 방법을 잘 기억하자.
public class RandomPickwithWeight {
    double sum = 0;
    int[] word;
    double[] probability;
    public RandomPickwithWeight(int[] w) {
        probability = new double[w.length];

        word = w;
        for (int i = 0; i < w.length; i++) {
            sum += w[i];
        }
        probability[0] = word[0]/sum;

        for (int i = 1; i < w.length; i++) {
            probability[i] = (word[i]/sum) + probability[i-1];
        }
        // System.out.println(Arrays.toString(probability));
    }

    public int pickIndex() {
        double target = Math.random();
        // for(int i = 0; i < probability.length; i++){
        //     if(target <= probability[i]) return i;
        // }
        int result = Arrays.binarySearch(probability,target);
        return result < 0 ? -1 * result -1 : result;
    }


//    double sum = 0;
//    int[] word;
//    double[] probability;
//    public RandomPickwithWeight(int[] w) {
//        probability = new double[w.length];
//
//        word = w;
//        for (int i = 0; i < w.length; i++) {
//            sum += w[i];
//        }
//        for (int i = 0; i < w.length; i++) {
//            probability[i] = word[i]/sum;
//        }
//    }
//
//    public int pickIndex() {
//        for(int i = 0; i < probability.length; i++){
//            double prob = probability[i];
//            if(Math.random() < prob) return i;
//        }
//        return probability.length-1;
//    }
}
