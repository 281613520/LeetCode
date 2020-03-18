package erchashu;

import math.TreeNode;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的 前序 遍历。
 */
public class PreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) return res;

        stack.add(root);
        TreeNode cur;
        while (!stack.isEmpty()) {
            cur = stack.pop();
            res.add(cur.val);
            if (cur.right != null) {
                stack.add(cur.right);
            }

            if (cur.left != null) {
                stack.add(cur.left);
            }
        }
        return res;
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root,res);
        return res;
    }

    private void dfs(TreeNode cur , List<Integer> res){
        if (cur == null) return;

        res.add(cur.val);
        dfs(cur.left,res);
        dfs(cur.right,res);
    }
}
