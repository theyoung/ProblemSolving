package leetcode.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
451. Sort Characters By Frequency
https://leetcode.com/problems/sort-characters-by-frequency/
 */
//TODO Bucket sort를 사용하면 O(N)으로 줄일 수 있다.
public class SortCharactersByFrequency {


    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0)+1);
        }
        List<Map.Entry> list = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            list.add(entry);
        }

        list.sort((a,b)->{
            if(a.getValue() != b.getValue()) return (int)b.getValue() - (int)a.getValue();
            return ((Character)a.getKey()).compareTo((Character)b.getKey());
        });

        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Character,Integer> entry : list){
            for(int i = entry.getValue(); 0 < i; i--){
                sb.append(entry.getKey());
            }
        }

        return sb.toString();
    }
}
