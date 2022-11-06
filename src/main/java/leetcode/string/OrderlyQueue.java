package leetcode.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 899. Orderly Queue
 * https://leetcode.com/problems/orderly-queue/
 */
//TODO k가 2이면 permutaion을 만들 수 있다는 것을 아는 것이 중요하다. 못 풀었다. 다시 풀자.
public class OrderlyQueue {
    @Test
    void test(){
        OrderlyQueue queue = new OrderlyQueue();
        Assertions.assertEquals("acb", queue.orderlyQueue("cba", 1));
        Assertions.assertEquals("aaabc", queue.orderlyQueue("baaca", 3));
        Assertions.assertEquals("dfgimqtuvv", queue.orderlyQueue("mqvgtdfuiv",10));
//        Assertions.assertEquals("imvxz", queue.orderlyQueue("xmvzi",2));
        Assertions.assertEquals("imvxz", queue.orderlyQueue("izvmx",2));
        Assertions.assertEquals("abdabe", queue.orderlyQueue("dabeab",1));
        Assertions.assertEquals("aaaa", queue.orderlyQueue("aaaa",1));

        Assertions.assertEquals("aagtkuqxitavoyjqiupzadbdyymyvuteolyeerecnuptghlzsynozeuuvteryojyokpufanyrqqmtgxhyycltlnusyeyyqygwupc",
                queue.orderlyQueue("xitavoyjqiupzadbdyymyvuteolyeerecnuptghlzsynozeuuvteryojyokpufanyrqqmtgxhyycltlnusyeyyqygwupcaagtkuq",1));
        //"xitavoyjqiupzadbdyymyvuteolyeerecnuptghlzsynozeuuvteryojyokpufanyrqqmtgxhyycltlnusyeyyqygwupcaagtkuq"
        //1
    }

    String orderlyQueue(String s, int k) {
        if(k == 0) return s;
        if(k == 1){
            int min = 0;
            for (int i = 1; i < s.length(); i++) {
                int cur = i;
                int tar = min;
                while(tar != i && s.charAt(cur) == s.charAt(tar)){
                    cur = (cur+1)%s.length();
                    tar = (tar+1)%s.length();
                }

                if(s.charAt(cur) < s.charAt(tar)) min = i;
            }
            return s.substring(min) + s.substring(0,min);
        } else {
            char[] chs = s.toCharArray();
            Arrays.sort(chs);
            StringBuilder sb = new StringBuilder();
            sb.append(chs);
            return sb.toString();
        }
    }

    //k가 2이상일 경우 모든 경우의 수가 된다는 사실을 인지 했어야 했다.
    private String orderlyQueueWrong(String s, int k) {
        if(k <= 0 || s.length() < 1) return s;
        //s에서 가장 작은 char를 찾고
        // 그 char 앞을 잘라서 뒤로 보낸다.
        // 그리고 다시 orderlyQueue를 하면 될 것 같다.
        int smallIdx = 0;
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) < s.charAt(smallIdx)) smallIdx = i;
        }
        String splitedStr = s.substring(smallIdx) + s.substring(0, smallIdx);

        return s.charAt(smallIdx) + orderlyQueue(splitedStr.substring(1),k-1);
    }

    // LTE가 발생한다. 최적화가 필요하다.
    // dfs말고 bfs로 변경해 보자.
    public String orderlyQueueDFS(String s, int k) {
        // k길이 중 하나의 letter를 가장 마지막으로 옮길 수 있다.
        // 첫번째 letter의 순서를 지켜야 하나?

        //brute force로 해보자.
        // cba -> 한자리이면 loop가 나올때 까지 돌수 있다.

        // 다른 k도 그렇게 할 수 있다.

        // 최종적으로 최대 O(slength^k)가 걸릴 것 같다.
        //다른 방법이 생각이 안남으로 일단 해보자.
        // Priority queue를 활용해서 가장 작은 string을 저장하자.

        PriorityQueue<String> queue = new PriorityQueue<>((a,b)->a.compareTo(b));
        Set<String> set = new HashSet<>();

        helper(s, k, set, queue);

        return queue.poll();
    }

    void helper(String s, int k, Set<String> set, PriorityQueue<String> queue){
        if(set.contains(s)) return;
        set.add(s);
        queue.add(s);

        for(int i = 0; i < k; i++){
            String next = s.substring(0,i) + s.substring(i+1) + s.charAt(i);
            helper(next, k, set, queue);
        }

    }

}
