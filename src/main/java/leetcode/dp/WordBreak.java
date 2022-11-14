package leetcode.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
139. Word Break
https://leetcode.com/problems/word-break/?envType=study-plan&id=level-3
 */
//TODO DP로 풀었어야 했는데, BFS로 풀었다.
public class WordBreak {
    @Test
    void test(){
        Assertions.assertEquals(true, this.wordBreak("leetcode", List.of("leet","code")));
        Assertions.assertEquals(true, this.wordBreak("applepenapple", List.of("apple","pen")));
        Assertions.assertEquals(false, this.wordBreak("catsandog", List.of("cats","dog","sand","and","cat")));
    }


    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        boolean[] dp = new boolean[s.length()];
        boolean[] search = new boolean[s.length()];

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);

        while (!queue.isEmpty()) {
            int startPoint = queue.pollFirst();
            if(s.length() <= startPoint) continue;
            if(search[startPoint]) continue;

            for(String word : wordDict){
                if(s.startsWith(word,startPoint)){
                    int index =  startPoint + word.length() - 1;
                    if(s.length() <= index) continue;
                    queue.add(index+1);
                    dp[index] = true;
                }
            }
            search[startPoint] = true;
        }

        return dp[s.length()-1];
    }



}
