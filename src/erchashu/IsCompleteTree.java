package erchashu;

import math.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * 给定一个二叉树，确定它是否是一个完全二叉树。
 * <p>
 * 百度百科中对完全二叉树的定义如下：
 * <p>
 * 若设二叉树的深度为 h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。（注：第 h 层可能包含 1~ 2h 个节点。）
 */
public class IsCompleteTree {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        LinkedList<TreeNode> list = new LinkedList<>();

        list.add(root);
        boolean flag = false;
        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = list.removeFirst();
                if (cur != null){
                    if (flag)
                        return false;
                    list.add(cur.left);
                    list.add(cur.right);
                }else {
                    flag = true;
                }
            }
        }

        return true;
    }
}
