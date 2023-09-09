package dongtaiguihua.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);

        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = dfs(node.left);
        int right = dfs(node.right);
        ans = Math.max(left + right + node.val, ans);

        return Math.max(Math.max(left, right) + node.val, node.val);
    }


    int ans2 = 0;

    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] treeList = new ArrayList[n];
        Arrays.setAll(treeList, e -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            treeList[parent[i]].add(i);
        }

        dfs(0, treeList,s);

        return ans2 + 1;
    }

    private int dfs(int i, List<Integer>[] treeList,String s) {
        int max = 0;

        for (int j : treeList[i]){
            int len = dfs(j,treeList,s)+1;
            if (s.charAt(i) != s.charAt(j)){
                ans2 = Math.max(ans2, max + len);
                max = Math.max(max, len);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        solution.longestPath(new int[]{-1,0,0,1,1,2},"abacbe");
    }
}
