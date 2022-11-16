package leetcode.search;

/*
374. Guess Number Higher or Lower
https://leetcode.com/problems/guess-number-higher-or-lower/
 */
//2131
//2143
public class GuessNumberHigherorLower {
    public int guessNumber(int n) {
        int left = 0;
        int right = n;

        while(true){
            int center = left + (right-left)/2;
            int picked = guess(center);

            if(picked == 0) return center;
            if(picked == -1) {
                right = center-1;
            }
            if(picked == 1){
                left = center+1;
            }
        }

    }
    int guess(int target){
        return 0;
    }
}
