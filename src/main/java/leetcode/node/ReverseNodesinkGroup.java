package leetcode.node;

/**
 * 25. Reverse Nodes in k-Group
 * https://leetcode.com/problems/reverse-nodes-in-k-group/?envType=study-plan&id=level-3
 */
//2235
//2322
//TODO node의 이동관계를 조금더 확실하게 정의하고 프로그램에 접근 했어야 한다.
public class ReverseNodesinkGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        // pre : left직전 node
        // left : node의 시작 node
        // right : node의 tail node

        // 위의 3개가 정의 되면
        // left.next는 저장하고 이후 pre노드가 된다.
        // left+1 node를 잘라내자
        // soleNode = left.next;
        // left.next = soleNode.next;
        // soleNode.next = pre.next;
        // pre.next = soleNode;

        // pre.next == right이면 종료한다.

        // 다음 loop에서 위의 정의가 성립하지 않으면 종료 한다.

        if(k == 1) return head;

        ListNode result = new ListNode();
        ListNode append = result;

        while(head != null){
            ListNode left = head;

            int move = k;
            ListNode right = left;
            // System.out.println("left = " + left.val + " right = " +right.val);
            while(1 < move) { //자기 자신을 한번으로 봄으로 2이면 1을 move한다.
                right = right.next;

                if(right == null){ // move를 다 못하고 종료한 경우는 끝내야 한다.
                    append.next = left;
                    return result.next;
                }
                move--;
            }


            head = right.next;
            right.next = null;

            ListNode root = new ListNode();
            root.next = left;
            //여기서 부터 rotate한다.
            while(root.next != right){
                ListNode replaceNode = left.next;
                left.next = replaceNode.next;
                replaceNode.next = root.next;
                root.next = replaceNode;
            }
            append.next = root.next;

            while(append.next != null) append = append.next;
        }

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
