package leetcode.greedy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 2144. Minimum Cost of Buying Candies With Discount
 * https://leetcode.com/problems/minimum-cost-of-buying-candies-with-discount/
 */
//2220
//2231
//TODO i % 3 != n %3 <- 이 trick을 찾아낼 수 있을까?
public class MinimumCostofBuyingCandiesWithDiscount {
    @Test
    void test(){
        MinimumCostofBuyingCandiesWithDiscount cost = new MinimumCostofBuyingCandiesWithDiscount();
        Assertions.assertEquals(5, cost.minimumCost(new int[]{1,2,3}));
        Assertions.assertEquals(23, cost.minimumCost(new int[]{6,5,7,9,2,2}));
        Assertions.assertEquals(10, cost.minimumCost(new int[]{5,5}));
    }

    public int minimumCost(int[] cost) {
        //2개 사면 1free
        // 산 2개의 캔디 중 가장 싼것과 같거나 낮은 가격으로 무료

        //greedy로 접근 하면 된다.

        // cost를 sorting하고 구매한 가격이 최저가가 맞을까?
        // 증명이 필요하다.
        // [a,b,c] a<b<c = b,c are best
        // [a,b,c,d] c,d = cd b free a best
        // 만약에
        // c,d,b를 산 cost가 c,d,a를 산 코스트 보다 작을 수 있을까? 불가능함

        Arrays.sort(cost);
        int result = 0;
        for(int i = cost.length - 1; 0 <= i; i-= 3){
            result += cost[i];
            if(0 < i) result += cost[i-1];
            continue;
        }

        return result;
    }

    //0,1,2,3,4,5,6
    //0,1,2,0,1,2,0
    //7%3 = 1
}
