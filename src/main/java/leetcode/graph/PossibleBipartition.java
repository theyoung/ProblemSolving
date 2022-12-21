package leetcode.graph;
import java.util.*;

/*
886. Possible Bipartition
https://leetcode.com/problems/possible-bipartition/description/
 */
//TODO graph문제 기회 될때 다시 풀어보자. Time Complexity 처리도 공부잘 하자
public class PossibleBipartition {

    /*
    graph
    node와 연결된 node는 color가 반대여야 한다.
    한번 연결된 노드는 color를 받게 됨으로 queue에 다시 들어갈수 없다.
    O(node)
    각 node에 연결된 모든 edge를 한번씩 검사한다.
    예를 들어서
        1-2-3
        | |
        4-5-6
    위와 같다면 1노드는 2, 2노드는 3, 3노드는 1, 4노드는 2, 5노드는 3, 6노드는 1
    모든 edge는 12회로 Edge의 갯수라고 볼수 있다.
    한 노드에 있는 엣지는 단 한번만 조회됨으로 모든 edges의 합만 visited를 한다.

    O(node + e)


     */
    class Node {
        int number;
        List<Node> list;
        int color;
        public Node(int number){
            this.number = number;
            this.list = new ArrayList<>();
            this.color = -1;
        }
    }

    public boolean possibleBipartitionGraph(int n, int[][] dislikes) {
        Node[] nodes = new Node[n+1];
        for(int i = 0; i < nodes.length; i++) nodes[i] = new Node(i);

        for(int[] edge : dislikes){
            nodes[edge[0]].list.add(nodes[edge[1]]);
            nodes[edge[1]].list.add(nodes[edge[0]]);
        }

        Deque<Node> queue = new LinkedList<>();
        for(int i = 0; i < nodes.length; i++) queue.addLast(nodes[i]);

        while(!queue.isEmpty()){
            Node node = queue.pollFirst();
            if(node.color < 0) node.color = 0;//0 red, 1 black

            for(Node next : node.list){
                if(next.color < 0){
                    next.color = 1 - node.color;
                    queue.addFirst(next);
                } else if(next.color == node.color) return false;
            }
        }

        return true;
    }



    public boolean possibleBipartition(int n, int[][] dislikes) {
        /*

         */



        /**
         * 아래는 논리는 비약하지만 작동하는데 문제는 없다. graph로 처리하는 게 더 좋다.O(n^2)
         //2개의 group을 유지한다.
         // partyA, partyB
         // left, right 포인트를 기반으로
         // left가 partyA와 partyB중 어디에 포함되는지 확인하고
         //  - 포함되어 있다면 right가 left와 같은 파티에 있는지 확인한다
         //   . 같은 파티에 포함되어있다면 return false
         //   . 없다면 반대편에 넣는다
         //  - left가 양쪽다 포함되어 있지 않다면

         // right가 partyA와 partyB중 어디에 포함되는지 확인하고
         //  - 포함되어 있다면 left가 right와 같은 파티에 있는지 확인한다
         //   . 같은 파티에 포함되어있다면 return false
         //   . 없다면 반대편에 넣는다
         //
         //  - left가 양쪽다 포함되어 있지 않다면
         //   .왼쪽에 우선적으로 넣는다 -> 성립하는 전제일까?
         //   [1,2],[1,3],[5,4],[5,1]
         //   [1] [2]
         //   [1] [2,3]
         //   [1,5] [2,3,4,1] -> fail
         //   [1,4] [2,3,5]
         //   [1,4] [2,3,5] -> success
         // -> 성립하지 않기 때문에 sorting이 필요할까?
         //   [1,2],[1,3],[5,4],[5,1]
         //   [1,2],[1,3],[1,5],[4,5] -> sorting이 필요할 것 같다
         */
        //[1,2],[1,3],[1,8],[1,9],[4,7],[7,8]
        // [1] [2,3,8,9]
        // [1,4] [2,3,8,9,7] -> false
        // [1,7] [2,3,8,4] -> 4,7의 자리를 반대로 하면 성립한다
        // 만약 관련있는 포인트를 모두 넣고, 이후 포인트 들을 다시 순환 시키면 되지 않을까?
        // [1,2],[1,3],[1,8],[1,9],[4,7],[7,8]
        // [1] [2,3,8,9]
        // [1,7] [2,3,8,9]
        // [1,7] [2,3,8,9,4]

        for(int i = 0; i < dislikes.length; i++){
            Arrays.sort(dislikes[i]);
        }
        Arrays.sort(dislikes,(a,b)->
                {
                    if(a[0] != b[0]) return a[0] - b[0];
                    return a[1] - b[1];
                }
        );

        Deque<int[]> que = new LinkedList<>();
        for(int[] edge : dislikes) que.add(new int[]{edge[0],edge[1]});

        Set<Integer> partyA = new HashSet<>();
        Set<Integer> partyB = new HashSet<>();

        //[1,2],[1,3],[1,8],[1,9],[4,7],[7,8]
        while(!que.isEmpty()){
            //bfs를 통해서 순환 시키자
            //여기에 온것은 앞서 아무곳도 속하지 않은 포인트라는 것이다
            int[] init = que.pollFirst();
            partyA.add(init[0]);
            partyB.add(init[1]);

            for(int i = que.size(); 0 < i; i--){
                int[] edge = que.pollFirst();
                int left = edge[0];
                int right = edge[1];

                if(partyA.contains(left) && partyA.contains(right)) {
                    // System.out.println(partyA.toString() + " / " + partyB.toString());
                    // System.out.println(left + " " + right);
                    return false;
                }
                if(partyB.contains(left) && partyB.contains(right)) {
                    // System.out.println(partyA.toString() + " / " + partyB.toString());
                    // System.out.println(left + " " + right);
                    return false;
                }

                if(partyA.contains(left)) partyB.add(right);
                else if(partyA.contains(right)) partyB.add(left);
                else if(partyB.contains(left)) partyA.add(right);
                else if(partyB.contains(right)) partyA.add(left);
                else {
                    que.addLast(new int[]{left,right});
                }
            }

            //clear는 왜 가능한가?
            /*
            1번 [a,b]-2번 [c,d]

            3번 [e,f]-4번 [g,h]

            4개의 그룹이 있을 때 다음과 같은 케이스를 생각해 볼 수 있다.

            1. 3번과 4번 그룹은 1번과 2번 그룹과의 관계가 없다.
               1번과 2번 그룹을 clear하고 3번과 4번 그룹을 처리해도 문제가 없다.
            2. 3번 혹은 4번 그룹이 1번 혹은 2번과 하나씩만 관계가 있을 경우
                1번 - 2번
                |
                3번 - 4번
                이경우 4번 그룹은 2번 그룹에 자동으로 포함됨으로 clear해도 문제가 없다.
            3. 1번 - 2번
                |  \
               3번 - 4번
                3번 그룹과 4번 그룹이 1번 그룹에 포함된 값을 ref 하고 있는 경우
                [1,2]-[3,4]
                  |  \
                [2,5] [1,6]
                [a,b] [c,d] -> a혹은 b가 1번 그룹과 관계가 있고, c혹은 d번이 1번 그룹과 관계가 있다면
               sorting을 했음으로
               [1,3][1,4][2,3][2,4] 가 1번과 2번 그룹의 관계이고
               [1,3][1,4][a,b][2,3][2,4][c,d]
                a or b는 1 or 2가 되어야함
                a는 1과 2에 소속됨으로 첫번째 loop에서 이미 포함이 되게 됨
                c or d도 첫번째 loop에서 포함되게 됨
            4.  [1,2]-[7,8]
                   /    |
                [3,7] [4,8]
                [a,b] [c,d] -> a혹은 b가 2번 그룹과 관계가 있고, c혹은 d번이 2번 그룹과 관계가 있다면
                [1,7][1,8][2,7][2,8]
                [3,4][3,8][7,4][7,8] 이 첫번째 그룹이 끝났을 때 que에 남아 있어야 하는데 가능하지 않음
                [1,7][1,8][2,7][2,8][3,4][3,8] -> 여기서 8과 3은 처리가 됨
                [1,7][1,8][2,7][2,8][3,4][3,8][7,4] -> 여기서 4도 처리가 됨 2번째 그룹으로 남을 수가 없음
            5.  [1,2]-[7,8]
                   | /
                [2,7] [4,9]
                3번 혹은 4번 그룹이 1번2번 그룹의 구성요소를 포함하는 경우임 독립으로 분리되기 위해서는

                [a,c][b] a < b < c에서 a와 b가 윗그룹을 포함한다면 다음 상태가 되어야 [a,b] [b,c]

                [1,7][1,8][a,b][b,c][2,7][2,8] a < c라고 하면
                위에 처럼 구성이 되고 [a,b][b,c]가 앞선 구성의 대상이 되지 않는다면 성립함
                그러나 sorting이 되었음으로
                [a,b]가 통과하기 위해서는
                [a=2,b=4][b=4,c=7]이 되어야함
                [1,7][1,8][2,4][b,c][2,7][2,8]
                여기서 b는 a보다 클수밖에 없음으로
                [1,7][1,8][2,4][2,7][2,8][b,c] 가 되고 이것은
                [1,7][1,8][2,4][2,7][2,8][4,7] 이 됨으로 첫번째에 4,7은 사라짐
                [a,b][d,e]에서 a->b->e가 따로 분리 될 수 있는 경우가 있을까?
                [a,d][a,e][b,d][b,e]일때 [a,b][b,e]가 숨을 수 있는 곳은?
                존재 하지 않음.
             */

            partyA.clear();
            partyB.clear();
        }

        return true;
    }
}
