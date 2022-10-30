package leetcode.search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 1574. Shortest Subarray to be Removed to Make Array Sorted
 * https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/
 */
//TODO two pointer, binary search 등 다양하게 접근이 가능하다.
public class ShortestSubarraytobeRemovedtoMakeArraySorted {
    @Test
    void test(){
        ShortestSubarraytobeRemovedtoMakeArraySorted sorted = new ShortestSubarraytobeRemovedtoMakeArraySorted();
        Assertions.assertEquals(3, sorted.findLengthOfShortestSubarray(new int[]{1,2,3,10,4,2,3,5}));
        Assertions.assertEquals(4, sorted.findLengthOfShortestSubarray(new int[]{5,4,3,2,1}));
        Assertions.assertEquals(0, sorted.findLengthOfShortestSubarray(new int[]{1,2,3}));
        Assertions.assertEquals(2, sorted.findLengthOfShortestSubarray(new int[]{1,2,3,2,1}));
        Assertions.assertEquals(3, sorted.findLengthOfShortestSubarray(new int[]{4,3,2,1,2,3}));
    }
    public int findLengthOfShortestSubarray(int[] arr) {
        //stack을 쓰면 될것 같다. -> stack대신 two pointers를 썻
        //가장 짧은 subarray의 길이라고 하면
        //가장긴 stack의 length를 찾으면 된다.

        //[1,2,3,10,4,2,3,5] 에서다
        // 1,2,3,10 or 4 둘중에 하나를 선택 해야 한다. 10을 선택하면 4,2,3,5가 삭제 된다.
        // 1,2,3,10 or 4 4를 선택하면 10하나만 삭제 하게 되고 4,2가 성립하지 않기 때문에 안된다.
        // 값의 역전이 되는 시점을 subarray의 시작지점으로 잡는다. 여기선 10과 4가 삭제의 시작지점이 된다.
        // 10이 삭제 시작지점이면 직전 값인 3과 같거나 큰 값인 3 6번 position까지 subarray하면 된다.
        // 여기서 고민해야 할 점은 4번 포지션을 선택하면 그뒤 4,2로 decrease가 나오기 때문에 문제가 된다.
        //
        // |        /\    /
        // |   /\  /  \  /
        // |  /  \/    \/
        // | /
        // |----------------------

        //case1 : 왼쪽만 남기고 오른쪽을 모두 삭제하는 경우
        //1,2,3,4,2
        int left = 1;
        for (left = 1; left < arr.length; left++) {
            if(arr[left-1] <= arr[left]) continue;
            else break;
        }
        if(left == arr.length) return 0;

        int min = arr.length - left;
        int right = arr.length-1;
        //case2 : 오른쪽만 남기고 왼쪽을 모두 삭제하는 경우
        for(; 1 <= right; right--){
            if(arr[right-1] <= arr[right]) continue;
            else break;
        }
        // 6,4,3,2,1,2,3
        min = Math.min(min, right);
        //case3 : 중간을 삭제하는 경우
        //0~left-1, right~arr.length-1
        // 1,2,3,4,3,2,1,2,3,4,5
        for (int i = 0; i < left; i++) {

            //여기는 binary search 로 바꾸면  빠름
//            for (int j = right; j < arr.length; j++) {
//                if(arr[i] <= arr[j]){
//                    min = Math.min(min,j-(i+1));
//                }
//            }
            int l = right;
            int r = arr.length;
            while(l < r){
                int c = l + (r-l)/2;
                if(arr[c] < arr[i]) {
                    l = c+1;
                } else {
                    r = c;
                }
            }
            min = Math.min(min,l-(i+1));
        }

        return min;
    }
}
