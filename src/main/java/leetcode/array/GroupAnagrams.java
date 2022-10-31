package leetcode.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 49. Group Anagrams
 * https://leetcode.com/problems/group-anagrams/
 */
//1422
//1428
public class GroupAnagrams {
    @Test
    void test(){
        GroupAnagrams anagrams = new GroupAnagrams();
        System.out.println(anagrams.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}).toString());
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for(String str : strs){
            char[] chs = str.toCharArray();
            Arrays.sort(chs);
            String key = String.valueOf(chs);
            List<String> pre = map.getOrDefault(key, new ArrayList<String>());
            pre.add(str);
            map.put(key, pre);
        }
        List<List<String>> result = new ArrayList<>();

        for(List<String> list : map.values()){
            result.add(list);
        }

        return result;
    }
}
