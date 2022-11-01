package leetcode.node;

/**
 * 2. Add Two Numbers
 * https://leetcode.com/problems/add-two-numbers/?envType=study-plan&id=level-3
 */
//2139
//2150
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode node = result;
        boolean carry = false;

        while(l1 != null || l2 != null){
            int l1val = l1 == null ? 0 : l1.val;
            int l2val = l2 == null ? 0 : l2.val;

            int val = l1.val + l2.val + (carry? 1 : 0);
            if(9 < val) carry = true;
            else carry = false;

            node.next = new ListNode(val%10);
            node = node.next;

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        if(carry) node.next = new ListNode(1);

        return result.next;
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
