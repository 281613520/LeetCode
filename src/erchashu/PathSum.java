package erchashu;

import math.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PathSum {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) return res;

        help(root,res,sum,new ArrayList<>());

        return res;
    }

    private void help(TreeNode node,List<List<Integer>> res,int sum,List<Integer> tmp){
        if (node == null) return;
        tmp.add(node.val);
        int r = sum - node.val;

       if (node.left == null && node.right == null){
           if (r == 0){
               res.add(new ArrayList<>(tmp));
           }
       }else {
           help(node.left,res,r,tmp);
           help(node.right,res,r,tmp);
       }

       // 精髓
       tmp.remove(tmp.size() -1);
    }
}
