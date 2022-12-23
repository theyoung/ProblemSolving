package leetcode.dp;
import org.junit.jupiter.api.Test;

import java.util.*;
/*
309. Best Time to Buy and Sell Stock with Cooldown
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solutions/75924/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems/
 */
//TODO dfs를 이용해서 buy와 sell status를 따로 처리하다 보니 동일 O(N)이지만 bottom up loop 보다 느리다. 일단 이해가 안감으로 이해를 할 수있는 dfs 부터 이해하자
public class BestTimetoBuyandSellStockwithCooldown {
    @Test
    void test() {
        this.maxProfit(new int[]{1,2,3,0,2});
    }


    Map<String,Integer> map;
    Map<String,Integer> profitMap;

    public int maxProfit(int[] prices) {
        // buy, rest, sell
        map = new HashMap<>();
        profitMap = new HashMap<>();
        return dfsUp(0,prices,true);
    }

    //dfs bottom up방식으로 최적화 해야한다
    //내가 하는 행위 바로 이후에 나올 수 있는 최대 profit을 저장하는 방식으로 진행하자
    // [1,2,3,0,2]
    //          | 2는 사졌을 수도 있고, 팔렸을 수도 있고, 아무것도 안했을 수 있다
    public int dfsUp(int index, int[] prices, boolean buy) { //살지, 팔지
        if(prices.length <= index) return 0; // profit
        String key = index + "" + buy;
        if(map.containsKey(key)) return map.get(key);

        int profit = 0;
        int resting = dfsUp(index+1, prices, buy); // 0 profit이 도착을 했고 지금 아무것도 안했기 때문에 0이 유지된다
        if(buy){
            int buying = dfsUp(index+1, prices, !buy) - prices[index]; //직전은 0, index값을 구매했음으로 profit은 줄어든다
            profit = Math.max(buying, resting);
        } else {
            int selling = dfsUp(index+2, prices, !buy) + prices[index]; // 직전 profit에 현재 갖고 있던 주식을 prices[i]에 팔았음으로 profit은 증가한다. 한 턴 쉬어야 한다
            profit = Math.max(selling, resting);
        }
        map.put(key, profit);
        return profit;
    }

    //top down인데 문제는 profit이 계속 업데이트가 된다는 것이다.
    public int dfs(int index, int[] prices, boolean buy, int profit){ //살지, 팔지
        if(prices.length <= index) return profit;
        String key = index + "" + buy;
        if(map.containsKey(key) && profit <= profitMap.get(key)) return map.get(key);

        int result = 0;
        int rest = dfs(index+1, prices, buy, profit);
        if(buy){
            int buying = dfs(index + 1, prices, !buy, profit - prices[index]);
            result = Math.max(rest, buying);
        } else {
            int selling = prices[index] + profit;
            selling = dfs(index + 2, prices, !buy, selling);
            result = Math.max(rest, selling);
        }
        profitMap.put(key,profit);
        map.put(key, result);
        return result;
    }
}
