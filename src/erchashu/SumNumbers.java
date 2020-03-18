package erchashu;

import math.TreeNode;

/**
 *
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 *
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 *
 * 计算从根到叶子节点生成的所有数字之和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 */
public class SumNumbers {
    public int sumNumbers(TreeNode root) {
        return dfs(root,0);
    }

    private int dfs(TreeNode cur , int val){
        if (cur == null )return 0;
        int tmp = val * 10 + cur.val;

        if (cur.left == null && cur.right == null) return tmp;
        return dfs(cur.left,tmp) + dfs(cur.right,tmp);
    }
}
