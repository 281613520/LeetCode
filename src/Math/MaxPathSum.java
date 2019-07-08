package Math;


public class MaxPathSum {
    int result = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        max(root);
        return result;
    }

    private Integer max(TreeNode root) {
        if (root == null) return 0;
        int left = max(root.left);
        int right = max(root.right);
        int cur = Math.max(Math.max(left + root.val,right + root.val),root.val);
        result = Math.max(left + right + root.val,result);
        return cur;
    }
}
