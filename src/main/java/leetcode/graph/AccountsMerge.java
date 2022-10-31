package leetcode.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 721. Accounts Merge
 * https://leetcode.com/problems/accounts-merge/?envType=study-plan&id=level-3
 */
//1802
//TODO 몇번 풀긴했는데 union find를 String에 맞춰서 개발하는 연습하는데 좋다.
public class AccountsMerge {
    @Test
    void test(){
        AccountsMerge merge = new AccountsMerge();
    }

    public class UnionFind{
        public String[] names;
        int[] points;
        public Map<String,Integer> mails;

        public UnionFind(int size){
            names = new String[size];
            points = new int[size];
            for(int i = 0; i < size; i++){
                points[i] = i;
            }
            mails = new HashMap<>();
        }

        public void setName(int index, String name){
            names[index] = name;
        }

        public int find(String mail){
            if (mails.containsKey(mail)) {
                return findIdx(mails.get(mail));
            }
            return -1;
        }

        int findIdx(int idx){
            if(points[idx] == idx) return idx;
            points[idx] = findIdx(points[idx]);
            return points[idx];
        }

        public void merge(int source, int target){
            source = findIdx(source);
            target = findIdx(target);

            points[source] = target;
        }
    }
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        //Union find를 활용하는데,
        // 문제는 mail과 이름을 어떻게 mapping할 것인가이다

        // 만약
        //[["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
        // 4개의 accounts가 있다면 최대 account임으로
        // 0:John, 1:John, 2:Mary, 3:John으로 표시할 수 있다.
        // loop를 돌면서 map으로 자신의 parent를 포인트 하자
        // johnsmith@mail.com - 0 -> 1
        // john_newyork@mail.com - 0 -> 1
        // johnsmith@mail.com -> 1을 setting해야 하는데 0번 John이 있음으로 0의 0을 1로 변경해준다.째
        // john00@mail.com ->
        UnionFind uf = new UnionFind(accounts.size());

        for(int i = 0; i < accounts.size(); i++){
            List<String> list = accounts.get(i);
            uf.setName(i,list.get(0));

            for (int j = 1; j < list.size(); j++) {
                String mail = list.get(j);
                if(uf.mails.containsKey(mail)){
                    uf.merge(uf.find(mail), i);
                }
                uf.mails.put(mail, i);
            }
        }
        //마지막으로 이름 이후 메일을 sorting처리해 주자
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            result.add(new ArrayList<>());
        }

        for(Map.Entry<String,Integer> entry : uf.mails.entrySet()){
            // System.out.println(entry.getValue());
            int root = uf.findIdx(entry.getValue());

            result.get(root).add(entry.getKey());
        }

        List<List<String>> result2 = new ArrayList<>();
        for(int i = 0; i < accounts.size(); i++){
            List<String> list = result.get(i);
            if(0 < list.size()){
                String name = uf.names[uf.findIdx(i)];
                list.sort((a,b)->a.compareTo(b));
                list.add(0,name);
                result2.add(list);
            }
        }

        return result2;
    }
}
