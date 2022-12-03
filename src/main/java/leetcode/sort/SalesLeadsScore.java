package leetcode.sort;

import java.util.ArrayList;
import java.util.List;

/*
salesLeadsScore
https://app.codesignal.com/company-challenges/freedomfinancialnetwork
 */
//10분
//TODO Sorting problem이다. Class 정의만 된다면 쉽다.
public class SalesLeadsScore {
    String[] solution(String[] names, int[] time, int[] netValue, boolean[] isOnVacation) {

        // 휴가 중이지 않은 리더로
        // decreasing order 처리해라

        // score = netValue[i] * time[i] / 365
        // - greater time first
        // - lexicographically order
        List<Person> list = new ArrayList<>();

        for(int i = 0; i < names.length; i++){
            if(isOnVacation[i]) continue;
            list.add(new Person(netValue[i] * time[i] / 365., time[i], names[i]));
        }

        list.sort((a,b)->{
            if(a.score != b.score) return Double.compare(b.score, a.score);
            if(a.time != b.time) return b.time - a.time;
            return a.name.compareTo(b.name);
        });
        String[] result = new String[list.size()];
        for(int i=0; i < list.size(); i++){
            result[i] = list.get(i).name;
        }
        return result;
    }

    public class Person{
        double score = 0;
        int time = 0;
        String name = "";

        public Person(double score, int time, String name){
            this.score = score;
            this.time = time;
            this.name = name;
        }
    }
}
