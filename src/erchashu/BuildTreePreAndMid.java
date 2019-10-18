package erchashu;

import math.TreeNode;

/**
 * qianxu + zhongxu
 */
public class BuildTreePreAndMid {
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder.length == 0 && inorder.length == 0) return null;

        TreeNode root = new TreeNode(preorder[0]);

        if (preorder.length == 1 && inorder.length == 1)return root;
        int locationInInorder = 0;
        for (int i = 0 ; i <= inorder.length - 1 ; i++){
            if (inorder[i] == preorder[0]){
                locationInInorder = i;
                break;
            }
        }

        root.left = help(preorder,1, locationInInorder +1, inorder ,0 , locationInInorder);
        root.right =help(preorder, locationInInorder+1, preorder.length ,inorder,locationInInorder+1, inorder.length);

        return root;

    }

    private TreeNode help(int[] preorder, int p_start, int p_end, int[] inorder, int i_start, int i_end) {
        // preorder 为空，直接返回 null
        if (p_start == p_end) {
            return null;
        }
        int root_val = preorder[p_start];
        TreeNode root = new TreeNode(root_val);
        //在中序遍历中找到根节点的位置
        int i_root_index = 0;
        for (int i = i_start; i < i_end; i++) {
            if (root_val == inorder[i]) {
                i_root_index = i;
                break;
            }
        }
        int leftNum = i_root_index - i_start;
        //递归的构造左子树
        root.left = help(preorder, p_start + 1, p_start + leftNum + 1, inorder, i_start, i_root_index);
        //递归的构造右子树
        root.right = help(preorder, p_start + leftNum + 1, p_end, inorder, i_root_index + 1, i_end);
        return root;
    }
}
