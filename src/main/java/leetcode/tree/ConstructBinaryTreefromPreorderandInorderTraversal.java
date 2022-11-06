package leetcode.tree;

import java.util.*;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/?envType=study-plan&id=level-3
 */
//1319
//1400
//TODO preorder, inorder, postorder의 의미를 잘 알고, copyOfRange를 잘 사용하는 지를 보는 문제이다.
public class ConstructBinaryTreefromPreorderandInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0) return null;
        if(preorder.length == 1) return new TreeNode(preorder[0]);

        //preorder와 inorder를 갖고 TreeNode를 만들어야하는 문제

        // 우선 preorder는
        //      1
        //   2    3
        // 4  5
        // -> 1,2,4,5,3

        // 우선 inorder는
        //      1
        //   2    3
        // 4  5
        // -> 4,2,5,1,3

        // root를 찾고 loop하면 된다.
        // preorder의 1은 항상 root가 된다.

        // inorder의 1 left와 right의 node를 분리할 수 있다.
        TreeNode root = new TreeNode(preorder[0]);
        int rootIdx = 0;

        for (int i = 0; i < inorder.length; i++) {
            if(inorder[i] == preorder[0]) {
                rootIdx = i;
                break;
            }
        }
        int[] inLeft = Arrays.copyOfRange(inorder,0,rootIdx);
        int[] inRight = Arrays.copyOfRange(inorder, rootIdx+1,inorder.length);

        //preorder = [3,9,20,15,7]
        int[] preLeft = Arrays.copyOfRange(preorder,1,inLeft.length+1);
        int[] preRight = Arrays.copyOfRange(preorder,inLeft.length+1,preorder.length);

        root.left = buildTree(preLeft, inLeft);
        root.right = buildTree(preRight, inRight);

        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
