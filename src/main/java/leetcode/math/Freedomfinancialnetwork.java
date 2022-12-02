package leetcode.math;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;
/*
coverDebts
https://app.codesignal.com/company-challenges/freedomfinancialnetwork
 */
//30분 (15분내 끝냈어야 한다.)
//TODO 빚을 졌을때, 가장 적은돈을 사용하는 방법은 높은 이자율을 먼저 갚는 것이다.
public class Freedomfinancialnetwork {
    @Test
    void test(){
        System.out.println(this.solution(50, new int[]{2, 2, 5}, new int[]{200, 100, 150}));
    }


    double solution(int s, int[] debts, int[] interests) {
        // s의 10%를
        //모든 빛을 갚기위해 사용되는 최소 비용은?
        double pay = (double)s / 10;

        PriorityQueue<double[]> pq = new PriorityQueue<>((a,b)-> (int)(b[0] - a[0]));

        for(int i = 0; i < debts.length; i++){
            pq.add(new double[]{interests[i],debts[i]});
        }

        double total = 0;
        double save = pay;

        while(!pq.isEmpty()){
            while(0 < save && !pq.isEmpty()){
                double[] next = pq.poll();
                if(next[1] == save) {
                    total += save;
                    break;
                }
                if(next[1] < save){
                    save -= next[1];
                    total += next[1];
                } else {
                    next[1] = next[1] - save;
                    pq.add(new double[]{next[0], next[1]});
                    total += save;
                    break;
                }
            }
            save = 0;

            PriorityQueue<double[]> tmp = new PriorityQueue<>((a,b)-> (int)(b[0] - a[0]));

            while(!pq.isEmpty()){
                double[] next = pq.poll();
                next[1] = next[1] + (next[1] * (next[0]/100));
                tmp.add(next);
            }
            pq = tmp;
            save += pay;
        }
        return total;
    }
}
