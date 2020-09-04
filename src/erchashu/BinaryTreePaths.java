package erchashu;

import math.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 *
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 */
public class BinaryTreePaths {
    List<String> res = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        String cur = "";

        treePath(cur,root);

        return res;
    }

    private void treePath(String cur, TreeNode root) {
        if (root == null) return;
        cur += root.val;

        if (root.left == null && root.right == null) {
            res.add(cur);
        }
        cur += "->";
        if (root.left != null) {
            treePath(cur,root.left);
        }

        if (root.right != null){
            treePath(cur,root.right);
        }
    }

}
