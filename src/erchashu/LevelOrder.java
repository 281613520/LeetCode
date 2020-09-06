package erchashu;

import math.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<TreeNode> list = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();
            while (!queue.isEmpty()) {
                list.add(queue.removeFirst());
            }

            for (TreeNode node : list){
                if (node != null) {
                    list2.add(node.val);
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            if (list2.size() != 0) {
                res.add(list2);
            }
        }

        return res;
    }
}
