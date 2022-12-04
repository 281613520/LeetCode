package erchashu;

import math.TreeNode;

public class HasPathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;

        int res = sum - root.val;

        if (root.left == null && root.right == null){
            return 0 == res;
        }

        return hasPathSum(root.left,res) || hasPathSum(root.right , res);
    }
}
