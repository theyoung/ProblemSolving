package leetcode.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. Minimum Window Substring
 * https://leetcode.com/problems/minimum-window-substring/
 */
// TODO Sliding window 기초 문제이다. 다만 아스키코드 테이블 범위에 다소 신경을 써야한다.
public class MinimumWindowSubstring {

    @Test
    void test(){
        MinimumWindowSubstring subwindow = new MinimumWindowSubstring();
        Assertions.assertEquals("BANC",subwindow.minWindow("ADOBECODEBANC", "ABC"));
        Assertions.assertEquals("a",subwindow.minWindow("a", "a"));
        Assertions.assertEquals("ba",subwindow.minWindow("acba", "ab"));
        Assertions.assertEquals("",subwindow.minWindow("a", "aa"));
        Assertions.assertEquals("cwae",subwindow.minWindow("cabwefgewcwaefgcf", "cae"));
        Assertions.assertEquals("sasasas",subwindow.minWindow("sasasas", "ssss"));
        Assertions.assertEquals("",subwindow.minWindow("sasasa", "ssss"));
        Assertions.assertEquals("wqkzuyotckqcusdiqubeqglkvuocttzrllqfjhzorpqnjwxbqyfiess",subwindow.minWindow("gehzduwqkzuyotckqcusdiqubeqglkvuocttzrllqfjhzorpqnjwxbqyfiesscmigicfzn", "qsvczwsslkhwg"));
        Assertions.assertEquals("",
                subwindow.minWindow("sdiqubeqglkvuocttzrllqfjhzorpqnjwxbqyfiess", "qsvczwsslkhwg"));//q1s3v1c1z1w2l1k1h1g1

    }

    public String minWindow(String s, String t) {
        //s = "ADOBECODEBANC", t = "ABC"
        //brute force로 간단히 접근한다면
        // t는 ABC 3개로 구성 됨으로
        // 최소 length인 3부터 하나씩 늘려가면서 비교하면 된다.

        // for 0+minlen to s.length
        //     compare if i...minlen contains all t chars
        //          then return true
        // return false

        // s가 10이고 t가 1일때 최악은
        // for를 한번 거침으로 O(N)
        // for내부에서 각 char를 한번씩 길이만틈 비교함으로 O(N^2)
        // 최종적으로 O(N^3)임으로 좋지 못함 줄여야함

        // 앞서 비교했던 결과를 재활용 해보자. 주의해야 할 것은 t에 중복 갯수가 나온다는 것이다.
        // ADOBECODEBANC    A[x],B[x],C[x] left=0, right=0
        // |                A[0],B[x],C[x] left=0, right=0 -> 모든 char가 발견 될때까지 right를 옮기자. 단, left가 찾고자 하는 char가 아니면 left를 욺직인다. right와 같이
        // |  |             A[0],B[0],C[x] left=0, right=3
        // |    |           A[0],B[0],C[0] left=0, right=5 -> 0,5가 첫번째 candidate, 모든 결과를 찾았음으로 left를 욺직인다.
        //    | |           A[x],B[0],C[0] left=3, right=5 -> left가 욺직이면서 A를 잃었다. 다음 match가 있는 곳까지 욺직인다.
        //    |      |      A[0],B[0],C[0] left=3, right=10 -> A를 찾을 때까지 right를 욺직인다. 3,10 두번째 candidate
        //      |    |      A[0],B[x],C[0] left=5, right=10 -> left가 욺직였다. 하지만 B는 2번 나온상태임으로 5,10 candidate
        // 위와 같이 하면 left가 n번 욺직였고, right가 n번 욺직였음으로 O(N)으로 가능하다. 비교하는 부분은 t의 길이이다. O(N*Tlen)

        //한자리일경우 바로 처리 가능하다.
        if(t.length() == 1){
            return s.contains(t)? t : "";
        }
        int[] target = new int[Math.abs('A' - 'z')+1];
        int[] running = new int[Math.abs('A' - 'z')+1];

        for (int i = 0; i < t.length(); i++) {
            target[t.charAt(i) - 'A']++;
        }

        int left = 0;
        int right = -1;
        int minLeft = 0;
        int minRight = -1;

        while (right < s.length()) {
            // full match 상태가 아니면 right을 옮겨서 full match를 만든다.
            while(!matchAll(running, target)){
                right++;
                if(s.length() <= right) break;
                running[s.charAt(right) - 'A']++;
            }
            //full match 상태에서 left를 옮겨서 full match가 아니게 만들어 주고 min을 구한다.
            while(matchAll(running, target)){
                int min = Math.min(s.length() - 1, right);
                if(minRight < minLeft || (min -left) < (minRight - minLeft)){
                    minLeft = left;
                    minRight = min;
                }

                running[s.charAt(left) - 'A']--;
                left++;
            }
        }

        return minRight < minLeft? "" : s.substring(minLeft, Math.min(s.length(),minRight+1));
    }

    boolean matchAll(int[] running, int[] target){
        for (int i = 0; i < target.length; i++) {
            // target이 0이면 의미 없는 값이다.
            if(target[i] == 0) continue;
            // running이 같거나 많은것은 문제 없다.
            if(running[i] < target[i]) return false;
        }
        return true;
    }


}
