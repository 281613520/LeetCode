package erchashu;

import math.TreeNode;

public class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        if (root.left != null && root.right == null) return false;
        if (root.left == null) return false;

        return help(root.left, root.right);
    }


    private boolean help(TreeNode left, TreeNode right) {
        if (left== null && right ==null) return true;
        if (left != null && right == null) return false;
        if (left == null) return false;
        if (left.val == right.val){
            return help(left.left,right.right)&& help(left.right,right.left);
        }
        return false;
    }
}
