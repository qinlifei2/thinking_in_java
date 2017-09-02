package with_leetcode;

/**
 * Created by qlf_workpc on 2016/11/3 0003.
 */

//Definition for a binary tree node.
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    public boolean one_node(TreeNode p, TreeNode q){
        if(p == null && q == null){
            return true;
        }
        else if(p == null || q == null){
            return false;
        }
        else if(p.val != q.val){
            return false;
        }
        else {
            return one_node(p.left, q.left) && one_node(p.right, q.right);
        }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        int base = 1;
        if(p == null && q == null){
            return true;
        }
        if(!one_node(p, q)){
            return false;
        }
        else {
            TreeNode[] node_list1 = {p};
            TreeNode[] node_list2 = {q};
//            TreeNode t1 = p;
//            TreeNode t2 = q;
            while (true){
                boolean all_null = true;
                TreeNode[] node_list3 = new TreeNode[2 * base];
                TreeNode[] node_list4 = new TreeNode[2 * base];
                for(int i = 0; i < base; i ++){
                    node_list3[2 * i] = node_list1[i] != null? node_list1[i].left : null;
                    node_list4[2 * i] = node_list2[i] != null? node_list2[i].left : null;
                    node_list3[2 * i + 1] = node_list1[i] != null? node_list1[i].right : null;
                    node_list4[2 * i + 1] = node_list2[i] != null? node_list2[i].right : null;
                }
                for(int j = 0; j < 2 *  base; j ++){
                    if(!one_node(node_list3[j], node_list4[j])) {
                        return false;
                    }
                    all_null = all_null && (node_list3[j] == null);
                }
                if(all_null){
                    return true;
                }
                node_list1 = node_list3;
                node_list2 = node_list4;
                base = base * 2;
            }
        }
    }


    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)){
            return true;
        }
        else if (root.left != null && root.right != null && isSymmetric(root.left) && isSymmetric(root.right)){
            return true;
        }
        return false;
    }





    public static void main(String args[]){
        TreeNode t = new TreeNode(12);
        TreeNode t1 = new TreeNode(12);
        TreeNode t2 = new TreeNode(12);
        TreeNode t11 = new TreeNode(-60);
        TreeNode t21 = new TreeNode(72);
        TreeNode t12 = new TreeNode(-60);
        TreeNode t22 = new TreeNode(72);
        t.left = t1;
        t.right = t2;
        t1.left = t11;
        t2.left = t21;
        t1.right = t12;
        t2.right = t22;

        TreeNode x = new TreeNode(0);
//        x.isSameTree(t1, t2);
//        x.isSymmetric(t);
        System.out.print(3 ^ 6 ^ 6);
    }
}


