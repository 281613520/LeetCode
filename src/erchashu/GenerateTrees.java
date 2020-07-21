package erchashu;


import math.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *95. 不同的二叉搜索树 II
 *
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 *
 *  
 *
 * 示例：
 *
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 */
public class GenerateTrees {
    public static List<TreeNode> generateTrees(int n) {
        if(n < 1){
            return new ArrayList<>();
        }else {
            return generate(1,n);
        }
    }

    private static List<TreeNode> generate(int left, int right) {
        List<TreeNode> result = new ArrayList<>();

        if (left > right){
            // 这边加入null是因为下面要进入循环
            result.add(null);
            return result;
        }

        for (int i = left ; i <= right ; i++){
            List<TreeNode> l = generate(left,i-1);
            List<TreeNode> r = generate(i+1,right);
            //这里 如果l或者r为空，那么这个树其实是无法构建的
            for (TreeNode leftNode : l){
                for (TreeNode rightNode : r){
                    TreeNode root = new TreeNode(i);
                    root.left = leftNode;
                    root.right = rightNode;
                    result.add(root);
                }
            }
        }

        return result;

    }

    public static void main(String[] args) {
        generateTrees(3);
    }
}
