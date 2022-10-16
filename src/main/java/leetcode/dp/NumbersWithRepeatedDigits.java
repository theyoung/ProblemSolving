package leetcode.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 1012. Numbers With Repeated Digits
 * https://leetcode.com/problems/numbers-with-repeated-digits/
 */
//TODO 이걸 40분내에 풀수 있나... 다시 풀어보자. permutation가 dp를 같이 활용해야 한다.
public class NumbersWithRepeatedDigits {

    @Test
    void test(){
        NumbersWithRepeatedDigits digits = new NumbersWithRepeatedDigits();
        Assertions.assertEquals(1, digits.numDupDigitsAtMostN(11));
        Assertions.assertEquals(1, digits.numDupDigitsAtMostN(20));
        Assertions.assertEquals(10, digits.numDupDigitsAtMostN(100));
        Assertions.assertEquals(261, digits.numDupDigitsAtMostN(999));
        Assertions.assertEquals(262, digits.numDupDigitsAtMostN(1000));
        Assertions.assertEquals(994388230, digits.numDupDigitsAtMostN(1000000000));

    }

    public int numDupDigitsAtMostN(int n) {
        LinkedList<Integer> list = new LinkedList<>();
        int substraction = 0;

        for(int i = n+1; 0 < i; i/=10){
            list.addFirst(i%10);
        }
//        System.out.println(list.toString());

        //n의 자릿수까지 일괄적으로 빼야할 값들을 넣어준다.
        for (int i = 1; i < list.size(); i++) {
            substraction += 9 * permutation(9, i-1);
        }
//        System.out.println(substraction);

        HashSet<Integer> seen = new HashSet<>();

        //각 자릿수별 빼야할 값들을 계산해 준다.
        for (int i = 0; i < list.size(); i++) {
            for (int j = i > 0 ? 0 : 1; j < list.get(i); j++) {
                // 첫자리는 무조건 1부터 시작한다.
                // 만약 7867이 n이라고 하면 1xxx~6xxx까지 불필요 값을 빼줘야 한다.
                // 다음에는 70xx~77xx까지 불필요 값을 빼줘야 한다. 0~7까지 하지만 1~6은 사용될 수 없다. 0~7까지 값을 확인한다.
                if(!seen.contains(j))
                    //1xxx, 2xxx, 3xxx, 4xxx, 5xxx, 6xxx => 9*8*7
                    //70xx, 71xx, 72xx, 73xx, 74xx, 75xx, 76xx, 77xx => 9*8*7
                    substraction += permutation(9-i, list.size()-i-1); //choose는 앞서 i개의 선택을 선점 당했음으로 9-i가 맞다 가령 위의 예는 첫번째 두번째 점령되었음으로 7개를 2depth permutation 해야 한다
            }
            if(seen.contains(list.get(i))) break; // 만약 7745라고 했을때 6xxx완료 76xx까지 완료 했다면, 다음은 773x를 비교 해야 하는데 이미 77이 겹치는 상태가 발생 함으로 더이상 검토는 불필요 한다.
            seen.add(list.get(i)); //예를 들어 첫째자리수 6xxx까지 완료 했다면, 7로 시작하는 모든 경우는 나올 수 없음으로 7을 add해 준다. 마찬가지로 다음은 8,6,7식이다.
        }

        return n - substraction;
    }

    int permutation(int choose, int until){
        int prod = 1;
        int right = choose;

        while(0 < until){
            prod *= right;
            right--;
            until--;
        }
        return prod;
    }




    //아래는 TLE가 발생한다. 최적화 필요하다.
    //while 부분을 줄여야 하는데...
    public int numDupDigitsAtMostNTLE(int n) {
        if(n < 11) return 0;

        int last = 1;

        for (int i = 12; i <= n; i++) {
            int[] dup = new int[10];
            int tmp = i;
            while(0 < tmp){
                int val = tmp%10;
                tmp = tmp / 10;
                dup[val]++;
                if(1<dup[val]){
                    System.out.println(i);
                    last++;
                    break;
                }
            }
        }

        return last;
    }

//    int dfs(int n){
//        if(n < 11) return 0;
//        int result = 0;
//        int[] dup = new int[10];
//        int tmp = n;
//        while(0 < tmp){
//            int val = tmp % 10;
//            tmp = tmp /10;
//            dup[val]++;
//            if(1 < dup[val]) {
//                result = 1;
//                break;
//            }
//        }
//        result += dfs(n - 1);
//        return result;
//    }
}
