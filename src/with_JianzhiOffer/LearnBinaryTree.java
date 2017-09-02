package with_JianzhiOffer;

import with_leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by qinlifei on 17-3-4.
 */
public class LearnBinaryTree {
    public void preOrder_recursive(TreeNode bTree){
        if (bTree != null){
            System.out.println(bTree.val);
            preOrder_recursive(bTree.left);
            preOrder_recursive(bTree.right);
        }
    }

    public void midOrder_recursive(TreeNode bTree){
        if (bTree != null){
            midOrder_recursive(bTree.left);
            System.out.println(bTree.val);
            midOrder_recursive(bTree.right);
        }
    }

    public void lateOrder_recursive(TreeNode bTree){
        if (bTree != null){
            lateOrder_recursive(bTree.left);
            lateOrder_recursive(bTree.right);
            System.out.println(bTree.val);
        }
    }

    public void preOrder(TreeNode bTree){
        //implement by stack
        Stack<TreeNode> stack = new Stack<>();
        if (bTree != null){
            stack.push(bTree);
        }
        while (!stack.isEmpty()){
            TreeNode top = stack.pop();
            System.out.println(top.val);
            if (top.right != null){
                stack.push(top.right);
            }
            if (top.left != null){
                stack.push(top.left);
            }
        }
    }

    public void midOrder(TreeNode bTree){
        Stack<TreeNode> stack = new Stack<>();
        while (bTree != null || !stack.isEmpty()) {
            while (bTree != null) {
                stack.push(bTree);
                bTree = bTree.left;
            }
            TreeNode top = stack.pop();
            System.out.println(top.val);
            bTree = top.right;
        }
    }

    public void lateOrder(TreeNode bTree){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prePrint = new TreeNode(-1);
        while (bTree != null || !stack.isEmpty()){
            while (bTree != null){
                stack.push(bTree);
                bTree = bTree.left;
            }
            TreeNode forMid = stack.peek();
            if (forMid.right == null || forMid.right == prePrint){
                stack.pop();
                System.out.println(forMid.val);
                prePrint = forMid;
            }
            else {
                bTree = forMid.right;
            }
        }
    }

    public void levelOrder(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null){
            queue.add(root);
        }
        while (!queue.isEmpty()){
            while (!queue.isEmpty()){
                TreeNode head = queue.remove();
                System.out.println(head.val);
                if (head.left != null)
                    queue.add(head.left);
                if (head.right != null)
                    queue.add(head.right);
            }
        }
    }

    public int treeHight(TreeNode root){
        if (root == null){
            return 0;
        }
        else {
            int leftHight = treeHight(root.left);
            int rightHight = treeHight(root.right);
            return Math.max(leftHight, rightHight) + 1;
        }
    }

    public int countNode(TreeNode root){
        if (root == null){
            return 0;
        }
        else {
            int countLeft = countNode(root.left);
            int countRight = countNode(root.right);
            return countLeft + countRight + 1;
        }
    }

    public int countLeaf(TreeNode root){
        if (root == null){
            return 0;
        }
        else if (root.left == null && root.right == null){
            return 1;
        }
        else {
            int countLeft = countLeaf(root.left);
            int countRight = countLeaf(root.right);
            return countLeft + countRight;
        }
    }

    public static void main(String[] args) {
        TreeNode a0 = new TreeNode(0);
        TreeNode a1 = new TreeNode(1);
        TreeNode a2 = new TreeNode(2);
        TreeNode a3 = new TreeNode(3);
        TreeNode a4 = new TreeNode(4);
        TreeNode a5 = new TreeNode(5);
        TreeNode a6 = new TreeNode(6);
        a5.left = a2;
        a5.right = a6;
        a2.left = a0;
        a2.right = a4;
        a0.right = a1;
        a4.left = a3;

        LearnBinaryTree learnBinaryTree = new LearnBinaryTree();
//        learnBinaryTree.preOrder(a5);
//        System.out.println("------------------");
        learnBinaryTree.midOrder(a5);
        System.out.println("------------------");
//        learnBinaryTree.lateOrder(a5);
//        learnBinaryTree.levelOrder(a5);
//        System.out.println(learnBinaryTree.treeHight(a5));
//        System.out.println(learnBinaryTree.countNode(a5));
//        System.out.println(learnBinaryTree.countLeaf(a5));
    }
}
