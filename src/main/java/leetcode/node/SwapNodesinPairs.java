package leetcode.node;

/**
 * 24. Swap Nodes in Pairs
 *https://leetcode.com/problems/swap-nodes-in-pairs/?envType=study-plan&id=level-3
 */
//2152
//2201
public class SwapNodesinPairs {

    public ListNode swapPairs(ListNode head) {
        ListNode root = new ListNode();
        root.next = head;

        ListNode node = root;

        while(node.next != null){
            ListNode first = node.next;
            ListNode second = first.next;
            if(second == null) break;

            ListNode secondNext = second.next;

            node.next = second;
            second.next = first;
            first.next = secondNext;

            node = first;
        }

        return root.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
