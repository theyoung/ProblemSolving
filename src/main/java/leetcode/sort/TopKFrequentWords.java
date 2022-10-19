package leetcode.sort;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 692. Top K Frequent Words
 * https://leetcode.com/problems/top-k-frequent-words/
 */
//TODO Priority Queue와 Sorting 사용 방법을 공부하려면 좋은 문제이다.
public class TopKFrequentWords {
    @Test
    void test(){
        TopKFrequentWords words = new TopKFrequentWords();

        System.out.println(words.topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
        System.out.println(words.topKFrequent(new String[]{"the","day","is","sunny","the","the","the","sunny","is","is"}, 4));
    }

    public List<String> topKFrequent(String[] words, int k) {
        // hashmap으로 word별 counting을 진행 한다.
        // max의 값도 같이 counting한다.
        // O(N)이 된다.

        // max가 있는 값으로 hashmap에서 search한다.
        // List에 넣고 sorting한다.
        // 최종적으로 O(NlogN)이 된다.
        HashMap<String,Integer> map = new HashMap<>();

        for(String word : words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<Value> pq = new PriorityQueue<>(
                (a,b)-> {
                    if(a.frequent == b.frequent){
                        return a.word.compareTo(b.word);
                    }
                    return b.frequent - a.frequent;
                }
        );
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.add(new Value(entry.getValue(), entry.getKey()));
        }

        List<String> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            result.add(pq.poll().word);
        }

        return result;
    }

    class Value{
        public int frequent = 0;
        public String word = "";

        public Value(int frequent, String word){
            this.frequent = frequent;
            this.word = word;
        }
    }
}
