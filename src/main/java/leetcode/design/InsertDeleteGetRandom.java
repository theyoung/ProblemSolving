package leetcode.design;

import java.util.*;

/*
380. Insert Delete GetRandom O(1)
https://leetcode.com/problems/insert-delete-getrandom-o1/?envType=study-plan&id=level-3
 */
//2244
//2306
//TODO map과 array를 같이 써보자. Random 함수도 잊지 말자
public class InsertDeleteGetRandom {

    HashMap<Integer,Integer> map;

    public InsertDeleteGetRandom() {
        map = new HashMap<>();
    }

    //bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
    public boolean insert(int val) {
        if(map.containsKey(val)) return false;
        map.put(val,val);
        return true;
    }

    //bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
    public boolean remove(int val) {
        if(map.containsKey(val)) {
            map.remove(val);
            return true;
        }
        return false;
    }

    //int getRandom() Returns a random element from the current set of elements
    // (it's guaranteed that at least one element exists when this method is called).
    // Each element must have the same probability of being returned.
    public int getRandom() {
        int size = map.size();
        double rand1 = Math.random() * 100_000;
        double rand2 = Math.random() * 100_000;

        return (int)map.values().toArray()[(int)(rand1+rand2)%size];
    }
}
