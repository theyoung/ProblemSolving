package leetcode.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 926. Flip String to Monotone Increasing
 * https://leetcode.com/problems/flip-string-to-monotone-increasing/
 */
//0935
//0956
public class FlipStringtoMonotoneIncreasing {
    @Test
    void test(){
        FlipStringtoMonotoneIncreasing mono = new FlipStringtoMonotoneIncreasing();
        Assertions.assertEquals(1, mono.minFlipsMonoIncr("00110"));
        Assertions.assertEquals(0, mono.minFlipsMonoIncr("11111"));
        Assertions.assertEquals(0, mono.minFlipsMonoIncr("00000"));
        Assertions.assertEquals(2, mono.minFlipsMonoIncr("010110"));
        Assertions.assertEquals(2, mono.minFlipsMonoIncr("00011000"));
    }
    public int minFlipsMonoIncr(String s) {
        //3가지 case로 생각할 수 있을 것 같다.
        // 1.s를 모두 1로 만드는 방법
        // 2.s를 모두 0으로 만드는 방법
        // 3.어떤 지점의 우측을 1로 좌측을 0으로 만드는 방법
        //  -> 3번 case를 최적화 해야한다.

        // 우선 1번 2번 케이스를 조사하자
//        int zero = 0;
//        for (char ch : s.toCharArray()) {
//            if(ch=='0') zero++;
//        }
//        //1을 zero로 만드는 수 or zero를 1로 만드는 수
//        int min = Math.min(s.length()-zero, zero);

        //3번 케이스를 위해서 s를 순회하면서 1을 0으로 만드는 시점과 우측에 0을 1로 만드는 시점의 min을 찾자
        //prefix sum이 가능할 것같다.
        //왼쪽에서 오른쪽으로 1의 갯수와
        //오른쪽에서 왼쪽으로의 0의 갯수가 가장 낮은 곳을 찾으면 된다.
        int[] rdp = new int[s.length()];
        rdp[rdp.length-1] = s.charAt(s.length()-1)=='0'? 1: 0;

        for(int i = s.length()-2; 0 <= i; i--){
            rdp[i] = rdp[i+1] + (s.charAt(i)=='0'? 1: 0);
        }
        int min = rdp[0];
        int last = 0;
        for (int i = 0; i < s.length(); i++) {
            last = last + (s.charAt(i) == '1'? 1: 0);
            if(s.length() <= i+1){
                min = Math.min(min, last);
            } else {
                min = Math.min(min, last+rdp[i+1]);
            }
        }

        return min;
    }
}
