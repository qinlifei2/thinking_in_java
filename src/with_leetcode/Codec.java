package with_leetcode;

import javafx.beans.binding.StringBinding;

import java.util.Stack;

/**
 * Created by qlf_workpc on 2017/9/3 0003.
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return stringBuilder.toString();
        }
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                stringBuilder.append(root.val).append(',');
                root = root.left;
            }
            root = stack.pop().right;
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0 || data == null){
            return null;
        }
        String[] valStrs = data.split(",");
        int[] nums = new int[valStrs.length];
        for (int i = 0; i < valStrs.length; i++) {
            nums[i] = Integer.parseInt(valStrs[i]);
        }
        Stack<TreeNode> stack = new Stack<>();
        int index = 0;
        TreeNode root = new TreeNode(nums[index]);
        index += 1;
        stack.push(root);
        return deserializeCore(index, nums, root, stack);
    }

    private TreeNode deserializeCore(int index, int[] nums, TreeNode root, Stack<TreeNode> stack) {
        TreeNode ans = root;
        while (index < nums.length){
            if (nums[index] < stack.peek().val){
                root.left = new TreeNode(nums[index]);
                root = root.left;
                index += 1;
                stack.push(root);
            }
            else {
                while (!stack.isEmpty() && stack.peek().val < nums[index]){
                    root = stack.pop();
                }
                root.right = new TreeNode(nums[index]);
                root = root.right;
                index += 1;
                stack.push(root);
            }
        }
        return ans;
    }
}
