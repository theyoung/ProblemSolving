package leetcode.design;

import java.util.*;

/**
 * 981. Time Based Key-Value Store
 * https://leetcode.com/problems/time-based-key-value-store/?envType=study-plan&id=level-3
 */
//2157
//2259
//TODO time bucket을 활용했다. key bucket을 활용해 보자.
public class TimeBasedKeyValueStore {
    /**
     * ["TimeMap","set","set","set","set","get","get","get","get","get","get","set","get","get","get","set","set","set","set","get","get"]
     * [[],["ctondw","ztpearaw",1],["vrobykydll","hwliiq",2],["gszaw","ztpearaw",3],["ctondw","gszaw",4],["gszaw",5],["ctondw",6],["ctondw",7],["gszaw",8],["vrobykydll",9],["ctondw",10],["vrobykydll","kcvcjzzwx",11],["vrobykydll",12],["ctondw",13],["vrobykydll",14],["ztpearaw","zondoubtib",15],["kcvcjzzwx","hwliiq",16],["wtgbfvg","vrobykydll",17],["hwliiq","gzsiivks",18],["kcvcjzzwx",19],["ztpearaw",20]]
     */

    TreeMap<Integer, Map<String,String>> map;
    int last = 0;
    public TimeBasedKeyValueStore() {
        map = new TreeMap<>();
    }

    public void set(String key, String value, int timestamp) {
        Map<String,String> timeMap = map.getOrDefault(timestamp, new HashMap<>());
        if(0 < last){
            Map<String,String> tmp = map.get(last);
            for(Map.Entry<String,String> entry : tmp.entrySet()){
                timeMap.put(entry.getKey(), new String(entry.getValue()));
            }
        }
        timeMap.put(key,value);
        map.put(timestamp, timeMap);
        last = timestamp;
    }

    public String get(String key, int timestamp) {
        if(last <= timestamp){
            String str = map.get(last).get(key);
            return str == null ? "" : str;
        }


        List<Integer> list = new ArrayList<>(map.keySet());

        int left = 0;
        int right = list.size();
        while(left < right){
            int center = left + (right-left)/2;
            if(list.get(center) <= timestamp){
                left = center + 1;
            } else {
                right = center;
            }
        }
        if(left == 0) return "";

        String str = map.get(list.get(left-1)).get(key);

        return str == null ? "" : str;
    }
}
