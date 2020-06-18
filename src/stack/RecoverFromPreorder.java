package stack;


import math.TreeNode;

import java.util.Stack;

/**
 * 1028. 从先序遍历还原二叉树
 *
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。
 *
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
 *
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 *
 * 给出遍历输出 S，还原树并返回其根节点 root。
 * 输入："1-2--3--4-5--6--7"
 * 输出：[1,2,5,3,4,6,7]
 *
 * 输入："1-2--3---4-5--6---7"
 * 输出：[1,2,5,3,null,6,null,4,null,7]
 *
 * 输入："1-401--349---90--88"
 * 输出：[1,401,null,349,88,90]
 */
public class RecoverFromPreorder {
    public TreeNode recoverFromPreorder(String S) {
        Stack<TreeNode> stack = new Stack<>();
        int index= 0;
        while (index < S.length()){
            int level = 0;
            while (S.charAt(index) == '-'){
                index++;
                level++;
            }

            int value = 0;
            while ( index < S.length() &&  Character.isDigit(S.charAt(index))){
                value = value*10 + S.charAt(index) -'0';
                index++;
            }

            TreeNode cur = new TreeNode(value);
            if (stack.size() == level){
                if (!stack.isEmpty()) {
                    stack.peek().left = cur;
                }
            }else {
                while (level!=stack.size()){
                    stack.pop();
                }
                stack.peek().right = cur;
            }

            stack.push(cur);
        }

        while (stack.size() >1){
            stack.pop();
        }
        return stack.peek();
    }
}
