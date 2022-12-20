package leetcode.array;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/*
841. Keys and Rooms
https://leetcode.com/problems/keys-and-rooms/description/
 */
public class KeysandRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        //모든 룸을 접근할 수 있다면 true를 return 해라
        //0번 룸은 열려있다.
        //0번부터 연결된 모든 키를 얻는다. rooms에 없는 키가 있다면 false
        boolean[] roomUnlock = new boolean[rooms.size()];
        Deque<Integer> keys = new LinkedList<>(rooms.get(0));
        roomUnlock[0] = true;

        while(!keys.isEmpty()){
            int roomNumber = keys.pollFirst();

            if(!roomUnlock[roomNumber]){
                roomUnlock[roomNumber] = true;
                keys.addAll(rooms.get(roomNumber));
            }
        }

        for(boolean check : roomUnlock){
            if(!check) return false;
        }
        return true;
    }
}
