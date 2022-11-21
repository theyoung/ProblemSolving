package leetcode.search;

import java.util.Arrays;

/*
875. Koko Eating Bananas
https://leetcode.com/problems/koko-eating-bananas/?envType=study-plan&id=level-3
 */
//2343
//0003
public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        int max = 0;
        for(int pile : piles) {
            if(max < pile) max = pile;
        };

        Arrays.sort(piles);
        int left = 1;
        int right = max;
        int hour = max;

        while(left < right){
            int center = left + (right - left)/2;
            int eatHour = eatAll(center, piles, h);

            if(eatHour < 0){//시간내에 먹을 수 없었다. k를 늘려줘야 한다.
                left = center + 1;
            } else {//시간내에 먹었지만 조금더 느리 먹을 수는 없을까?
                right = center;
            }
        }

        return left;
    }

    int eatAll(int k, int[] piles, int h){
        int remainder = 0;
        for (int i = 0; i < piles.length; i++) {
            remainder += piles[i]/k;
            if(piles[i]%k != 0) remainder++;

            if(h < remainder) return -1;
        }

        return remainder;
    }
}
