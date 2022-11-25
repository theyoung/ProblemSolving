package leetcode.dp;

/*
907. Sum of Subarray Minimums
https://leetcode.com/problems/sum-of-subarray-minimums/
 */
//0941

import java.util.LinkedList;

//TODO monotonic stack의 끝판왕 다시 풀어보자. dp까지는 무리다.
public class SumofSubarrayMinimums {

    final int modulo = 1_000_000_007;

    //monotonic stack
    public int sumSubarrayMins(int[] arr) {
        int sum = 0;
        LinkedList<Integer> stack = new LinkedList<>();

        for(int i = 0; i < arr.length; i++){
            if(stack.isEmpty()) stack.add(i);
            if(arr[i] <= stack.peekLast()){
                int remove = stack.pollLast();
                // 1 3 2 5 2 4
                // 0 1 2 3 4 5
                // 1       2
                int pre = remove - stack.peekLast();
                int post = i - remove;
                sum += pre * post * arr[remove];
            }
            stack.add(i);
        }

        while(!stack.isEmpty()){
            int remove = stack.pollLast();
            // 1 3 2 5 2 4
            // 0 1 2 3 4 5
            // 1       2
            int pre = remove - stack.size()==0 ? -1:stack.peek();
            int post = arr.length - remove;
            sum += pre * post * arr[remove];
        }

        return sum;
    }



    //TLE
    public int sumSubarrayMins1D(int[] arr) {
        int sum = 0;
        for(int val : arr){
            sum += val;
        }

        for(int i = 1; i < arr.length; i++){
            for(int col = 0; col < arr.length-i; col++){
                arr[col] = Math.min(arr[col],arr[col+1]);
                sum = (sum+arr[col])%modulo;
            }
        }

        return sum;
    }

    //2D Array MLE 발생
    public int sumSubarrayMins2D(int[] arr) {
        // 1. 1개 선택, 모든 요소는 한번씩 나올 수 있다. sum all 0~n-1 (inclusive)
        // 2. 2개씩 선택한다면 어떻게 min을 알수 있을까?
        /*
        [3,1,2,4]
        3,1,2,4 = 10
        -> 이 아래는 prefix sum이 활용될 수 있나?
        1,1,2 = 4
        1,1 = 2
        1 = 1
         */

        /*
        [11,81,94,43,3]
        -> 2개씩 비교해서 더하면 된다.
        11,81,94,43,3 = 232
        11,81,43,3 = 138
        11,43,3 = 57
        11,3 = 14
        3
        =444
         */
        //전체 Array에서 가장 큰값을 빼가는 방식으로 sum하면 될까? 안됨...heap nono
        // stack? no
        // two point? no
        // for로 하면 LTE가 안날까?
        // [4,6,1,8,7,3,2]
        //  4,1,1,7,3,2,2
        //  4,1,1,7,3,2,[2]
        //  1,1,1,2,[2],[2],[2]

        //  4,1,1,7,3,2 -> 확실한 것은 가장 큰수는 순차적으로 삭제 된다. (가장 큰수 위치는 heap을 사용하면 된다.)
        //  1,1,1,3,2 -> 가장 작은 수는 왼쪽으로
        //  1,1,1,2
        //  1,1,1
        //  1,1

        /*
               0 1 2 3 4 5 6
             0 4 4 1 1 1 1 1
             1   6 1 1 1 1 1
             2     1 1 1 3 2
             3       8 7 3 2
             4         7 3 2
             5           3 2
             6             2
         */
        int sum = 0;
        int[][] dp = new int[arr.length][arr.length];
        for(int row = 0; row < arr.length; row++){
            for(int col = row; col < arr.length; col++){
                dp[row][col] = arr[row];
            }
        }

        for(int i = 1; i < arr.length; i++){
            for(int row = 0; row < arr.length-i; row++){
                dp[row][row+i] = Math.min(dp[row][row+i-1],dp[row+1][row+i]);
            }
        }

        for(int row = 0; row < arr.length; row++){
            for(int col = 0; col < arr.length; col++){
                sum = (sum + dp[row][col])%modulo;

            }
        }

        // System.out.println(Arrays.deepToString(dp));

        return sum;
    }



    //아래는 LTE가 난다
//    public int sumSubarrayMins(int[] arr) {
//        // 1. 1개 선택, 모든 요소는 한번씩 나올 수 있다. sum all 0~n-1 (inclusive)
//        // 2. 2개씩 선택한다면 어떻게 min을 알수 있을까?
//        /*
//        [3,1,2,4]
//        3,1,2,4 = 10
//        -> 이 아래는 prefix sum이 활용될 수 있나?
//        1,1,2 = 4
//        1,1 = 2
//        1 = 1
//         */
//
//        /*
//        [11,81,94,43,3]
//        -> 2개씩 비교해서 더하면 된다.
//        11,81,94,43,3 = 232
//        11,81,43,3 = 138
//        11,43,3 = 57
//        11,3 = 14
//        3
//        =444
//         */
//        int sum = 0;
//        for(int val : arr) sum+=val;
//
//
//        return (sum+getSumOfDuo(arr))%modulo;
//    }
//    final int modulo = 1_000_000_007;
//    int getSumOfDuo(int[] arr){
//        if(arr.length==1) return 0;
//
//        int sum = 0;
//        int[] next = new int[arr.length-1];
//
//        for(int i = 1; i < arr.length; i++){
//            int cur = Math.min(arr[i-1],arr[i]);
//            sum += (cur%modulo);
//            next[i-1]=cur;
//        }
//        return (sum + getSumOfDuo(next))%modulo;
//    }
}
