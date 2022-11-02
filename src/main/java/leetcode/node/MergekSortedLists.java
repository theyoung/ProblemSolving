package leetcode.node;

/**
 * 23. Merge k Sorted Lists
 * https://leetcode.com/problems/merge-k-sorted-lists/?envType=study-plan&id=level-3
 */
//2300
//2313
//TODO divide and Conquer로 푸는게 TC가 더 좋다.
public class MergekSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode root = new ListNode();
        ListNode node = root;

        while(true){
            ListNode small = new ListNode(Integer.MIN_VALUE);
            int index = -1;
            for(int i = 0; i < lists.length; i++){
                ListNode list = lists[i];
                if(list == null) continue;
                if(small.val < list.val) {
                    small = list;
                    index = i;
                }
            }

            if(small.val == Integer.MIN_VALUE) break;
            lists[index] = small.next;
            small.next = null;
            node.next = small;
            node = small;
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
