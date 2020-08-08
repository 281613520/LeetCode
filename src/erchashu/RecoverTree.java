package erchashu;

import math.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 二叉搜索树中的两个节点被错误地交换。
 * <p>
 * 请在不改变其结构的情况下，恢复这棵树。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,null,null,2]
 * <p>
 *    1
 *   /
 *  3
 *   \
 *    2
 * <p>
 * 输出: [3,1,null,null,2]
 * <p>
 *    3
 *   /
 *  1
 *   \
 *    2
 * 示例 2:
 * <p>
 * 输入: [3,1,4,null,null,2]
 * <p>
 * 3
 * / \
 * 1   4
 *    /
 *   2
 * <p>
 * 输出: [2,1,4,null,null,3]
 * <p>
 * 2
 * / \
 * 1   4
 *    /
 *  3
 * <p>
 * 要注意到题目中  只有两个节点被交换
 */
public class RecoverTree {
    public void recoverTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        //中序遍历
        search(root, list);

        int x = -1;
        int y = -1;

        for (int  i = 0 ; i < list.size() - 1 ; i++){
            if (list.get(i).val > list.get(i+1).val){
                y = i+1;
                if (x == -1){
                    x = i;
                }else {
                    break;
                }

            }
        }

        int tmp = list.get(x).val;
        list.get(x).val = list.get(y).val;
        list.get(y).val = tmp;
    }

    private void search(TreeNode node, List<TreeNode> list) {
        if (node == null) return;


        search(node.left, list);
        list.add(node);
        search(node.right, list);
    }
}
