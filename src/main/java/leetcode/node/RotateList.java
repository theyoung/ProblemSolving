package leetcode.node;

/**
 * 61. Rotate List
 * https://leetcode.com/problems/rotate-list/?envType=study-plan&id=level-3
 */
//2202
//2231
//TODO 잘라내는 부분을 정확히 이해 했으면 더 빠르게 해결 했을 것 같다.
public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        //node의 길이를 알아야 한다.
        // node의 길이 % k 만큼 오른쪽으로 shift하면 된다.

        // 쉬프트 방법은 node길이 - (k+1)
        // 해당 node의 next따로 잘라내서 head에 붙여준다.
        if(head == null) return null;

        ListNode root = new ListNode();
        root.next = head;
        ListNode node = root;
        int length = 0;
        while(node != null){
            length++;
            node = node.next;
        }
        k = k % length;
        if(k == 0) return head;

        int moveCount = length - k - 1;

        node = root;
        for(int i = 0; i < moveCount; i++){
            node = node.next;
        }
        ListNode tailStart = node.next;
        node.next = null;
        ListNode tail = tailStart;
        while(tail.next != null) tail = tail.next;
        tail.next = root.next;
        root.next = tailStart;

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
