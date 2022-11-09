package leetcode.design;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU Cache
 * https://leetcode.com/problems/lru-cache/?envType=study-plan&id=level-3
 */
//TODO 더블 LinkedList Edge 케이스가 많을 수 있다. 그림으로 그려서 일일이 따라가야 한다.
@ExtendWith(LRUCache.LRUCacheParameterResolver.class)
public class LRUCache {
    @Test
    void test(){
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4,4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));

    }


    Node head;
    Node tail;
    Map<Integer, Node> map;

    int capacity;

    public LRUCache(Integer capacity) {
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.pre = head;
        map = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        int value = -1;
        if(map.containsKey(key)){
            Node cur = map.get(key);
            // System.out.println("get");
            moveFirst(cur);
            value = cur.value;
        }
        return value;
    }
    //["LRUCache","put","put","get","put","get","put","get","get","get"]
    //     [[2], [1,0],[2,2], [1], [3,3],  [2],[4,4],  [1],  [3],  [4]]
    //            1     2,1  1,2    3,1         4,3     x

    public void put(int key, int value) {
        Node node = new Node(key,value);

        if(map.containsKey(key)){
            node = map.get(key);
            node.value = value;
        }

        map.put(key, node);
        // System.out.println("put");
        moveFirst(node);

        if(capacity < map.size()){
            Node rm = tail.pre;
            rm.pre.next = tail;
            tail.pre = rm.pre;
            rm.next = null;
            rm.pre = null;
            map.remove(rm.key);
            // System.out.println("-> "+rm.key);
        }
        // System.out.println(map.size());
    }

    void moveFirst(Node cur){

        if(cur.next != null){
            Node pre = cur.pre;
            Node next = cur.next;
            pre.next = next;
            next.pre = pre;
        }

        Node first = head.next;
        head.next = cur;
        cur.pre = head;
        cur.next = first;
        first.pre = cur;
    }

    class Node {
        public Node pre;
        public Node next;
        public int value = -1;
        public int key = -1;
        public Node(int key,int value){
            this.key = key;
            this.value = value;
        }
    }

    public static class LRUCacheParameterResolver implements ParameterResolver {
        @Override
        public boolean supportsParameter(ParameterContext parameterContext,
                                         ExtensionContext extensionContext) throws ParameterResolutionException {
            return parameterContext.getParameter().getType() == Integer.class;
        }

        @Override
        public Object resolveParameter(ParameterContext parameterContext,
                                       ExtensionContext extensionContext) throws ParameterResolutionException {
            return Integer.valueOf(2);
        }
    }
}
