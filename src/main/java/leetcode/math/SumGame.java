package leetcode.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 1927. Sum Game
 * https://leetcode.com/problems/sum-game/
 */

//TODO 수학적으로 문제를 풀어야 하는데 잘 모르겠다. 다음에 보자. Greedy로는 풀었다.
public class SumGame {

    @Test
    void test(){
        SumGame game = new SumGame();
        Assertions.assertFalse(game.sumGame("5023"));
        Assertions.assertTrue(game.sumGame("5024"));
        Assertions.assertTrue(game.sumGame("502?"));
        Assertions.assertTrue(game.sumGame("5?2?"));
        Assertions.assertTrue(game.sumGame("25??"));
        Assertions.assertFalse(game.sumGame("?3295???"));
    }

    public boolean sumGame(String num) {
        //?329 5???
        // 14~23 5~32
        // -> 5~13 win
        // -> 24~32 win

        //Alice는 좌와 우의 차를 최대화 하고 싶어한다.
        // - 값이 많은쪽에 9를 더한다.
        // - 값이 적은쪽에 0을 더한다.
        //Bob은 좌와 우의 차를 최소화 하고 싶어한다.
        // - 값이 많은쪽에 0을 더한다.
        // - 값이 적은쪽에 9를 더한다.
        int left = 0;
        int lCount = 0;
        int right = 0;
        int rCount = 0;

        for (int i = 0; i < num.length(); i++) {
            char ch = num.charAt(i);

            if(i < num.length()/2){
                if(ch == '?') lCount++;
                else left += num.charAt(i) - '0';
            } else {
                if(ch == '?') rCount++;
                else right += num.charAt(i) - '0';
            }
        }

        return game(left, lCount, right, rCount, true);
    }

    boolean game(int left, int lCount, int right, int rCount, boolean turn){
        if (lCount == 0 && rCount == 0) {
            return left != right;
        }

        if(right < left){
            int tmp = right;
            int tmpCount = rCount;
            right = left;
            rCount = lCount;
            left = tmp;
            lCount = tmpCount;
        }

        //왼쪽이 더 작은데 더이상 left를 키울 수 없다면 무조건 alice가 이긴다.
        if(lCount == 0) return true;
        if(turn){
            //alice turn일때 최대한 차를 늘리는게 필요 하다.
            //큰 값을 무조건 9로 키운다. 그러면 left를 더 키우려고 해도 9 초과가 불가함으로 현 상태가 유지된다.
            if(0 < rCount) return game(left,lCount,right+9,rCount-1,!turn);
            //r이 없을 경우 l을 최대한 줄이거나 r을 넘겨야 한다.
            else if(Math.abs(right - left) < 9){ // 차가 9이내 이면 l값에 9를 더함으로써 r을 넘기기 때문에 alice가 이긴다.
//                return game(left+9,lCount-1,right,rCount,!turn);
                return true;
            } else {
                return game(left,lCount-1,right,rCount,!turn);
            }
        } else {
            if(Math.abs(right-left) <= 9)
                return game(left+Math.abs(right-left),lCount-1,right,rCount,!turn);
            else
                return game(left+9,lCount-1,right,rCount,!turn);
        }
    }
}
