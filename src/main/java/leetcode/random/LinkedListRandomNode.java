package leetcode.random;

import java.util.Random;

/**
 * 382. Linked List Random Node
 * https://leetcode.com/problems/linked-list-random-node/
 */

public class LinkedListRandomNode {

     public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

    ListNode head;
    Random rand;
    public LinkedListRandomNode(ListNode head) {
        this.head = head;
        rand = new Random();
    }

    public int getRandom() {
        int rst = -1;
        int count = 0;
        ListNode node = head;

        while(node != null){
            count++;

            if(rand.nextInt(count) == 0){
                rst = node.val;
            }
            node = node.next;
        }
        return rst;
    }
}
