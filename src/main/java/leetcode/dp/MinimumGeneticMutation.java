package leetcode.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 433. Minimum Genetic Mutation
 * https://leetcode.com/problems/minimum-genetic-mutation/
 */
//2217
//2251
//TODO dp로 문제를 풀었는데 bfs로 푸는 의도로 문제를 낸것 같다. 다음에는 bfs로 풀어보자.
public class MinimumGeneticMutation {

    @Test
    void test(){
        MinimumGeneticMutation mutation = new MinimumGeneticMutation();
        Assertions.assertEquals(1, mutation.minMutation("AACCGGTT","AACCGGTA",new String[]{"AACCGGTA"}));
        Assertions.assertEquals(2, mutation.minMutation("AACCGGTT","AAACGGTA",new String[]{"AACCGGTA","AACCGCTA","AAACGGTA"}));
        Assertions.assertEquals(3, mutation.minMutation("AAAAACCC","AACCCCCC",new String[]{"AAAACCCC","AAACCCCC","AACCCCCC"}));
    }

    public int minMutation(String start, String end, String[] bank) {
        //acgt 8자리
        //start에서 1의 char만 변경할 수 있다.
        //변경대상은 bank에서 골라야 한다.
        //변경된 bank는 start가 된다.
        //최종 end에 도착하는데 min의 횟수로 도착하면 된다.

        //가장 쉬운 방법은 모든 경우의 수를 도는 것이다. bank의 length는 10밖에 되지 않는다.
        // 10,9,8,7... O(N^2) 이 된다.

        //DP를 활용하면 개선이 될 것 같다.
        //start + bit로 관리해보자.
        int result = helper(start, end, (1<<bank.length) -1, bank, new HashMap<String,Integer>());
        return result < 11 ? result : -1;
    }

    int helper(String start, String end, int mask, String[] bank, Map<String,Integer> map){
//        System.out.println(Integer.toBinaryString(mask));
        String key = start+mask;

        if(map.containsKey(key)) return map.get(key);

        if(start.equals(end)) return 0;

        int min = 11;

        for (int i = 0; i < bank.length; i++) {
            if(0 == (mask & 1 << i)) continue;
            int diff = 0;
            for (int j = 0; j < 8; j++) {
                if(start.charAt(j) != bank[i].charAt(j)){
                    diff++;
                }
            }
            if(diff==1){
                min = Math.min(min, helper(bank[i], end, mask ^ (1 << i), bank, map) + 1);
            }
        }

        map.put(key, min);
        return min;
    }
}
