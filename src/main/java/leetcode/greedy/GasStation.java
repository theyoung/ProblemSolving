package leetcode.greedy;

/*
134. Gas Station
https://leetcode.com/problems/gas-station/?envType=study-plan&id=level-3
 */
//TODO 한참을 풀었는데 결국 답을 봤다. Math와 직관 문제인듯 한데 어렵다. Greedy같은 느낌은 안들었음. 다시 풀어보자.
public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        //각 step별로 얻을 수 있는 price는 gas[i]-cost[i] 이다.
        //loop가 가능함으로 2배로 계산하자.
        int[] price = new int[gas.length*2];
        int[] prefixSum = new int[gas.length*2];
        // gas = [1,2,3,4,5], cost = [3,4,5,1,2]
        // price = [-2,-2,-2,3,3,-2,-2,-2,3,3]
        int sum = 0;
        for (int i = 0; i < gas.length; i++) {
            price[i] = gas[i] - cost[i];
            sum += price[i];
        }

        //모든 price의 합이 0이하면 답은 나올 수 없다.
        if(sum < 0) return -1;

        //0이상이라는 것은 어딘가는 답이라는 것이다.
        //0이상이 나오고 이후 모두 양수가 나온다면 답이 될 수 있다.
        // 그런데 이걸 주어진 시간에 어떻게 알아내지?
        int start = 0;
        int cur = 0;
        for (int i = 0; i < price.length; i++) {
            cur += price[i];
            if(cur < 0){
                start = i+1;
                cur = 0;
            }
        }

        return -1;
    }


    public int canCompleteCircuitBruteForce(int[] gas, int[] cost) {
        //1. gas[i] < cost[i] 는 실행 Point가 될 수 없다.
        //2. 현 gas[i]에서 갈수 있는 최대 거리를 미리 계산 하면 좋다.. -> dp
            //그런데 이건 O(N^2)이 될것 같다.
        //일단 brute force로 해결해 보자.
        for (int i = 0; i < gas.length; i++) {
            int tank = gas[i] - cost[i];
            int next = (i+1)%gas.length;
            while(0 <= tank && next != i){
                tank = (tank + gas[next]) - cost[next];
                next = (next+1)%gas.length;
            }
            if(next == i && 0 <= tank) return i;
        }

        return -1;
    }
}
