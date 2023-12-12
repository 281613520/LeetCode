package context335;


import java.util.*;

public class Solution {
    public int passThePillow(int n, int time) {
        int i = 0;
        boolean flag = true;

        while (time >= 0){
            if (flag){
                if (i == n){
                    i--;
                    flag = false;
                }else {
                    i++;
                }
            }else {
                if (i == 1){
                    i++;
                    flag = true;
                }else {
                    i--;
                }
            }
            time--;
        }

        return i;
    }

    public long kthLargestLevelSum(TreeNode root, int k) {
        List<Long> list= new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            long sum = 0;
            while (size > 0){
                TreeNode cur = queue.poll();
                sum+= cur.val;
                if (cur.left != null) {
                    queue.add(cur.left);
                }

                if (cur.right != null) {
                    queue.add(cur.right);
                }
                size--;
            }

            list.add(sum);
        }

        if (list.size() < k ){
            return -1;
        }

        Collections.sort(list);
        return list.get(list.size() - k);
    }

    public int waysToReachTarget(int target, int[][] types) {
        int mod = 1000000000+7;

        int[][] dp = new int[types.length][target+1];

        //dp[i][j] = sum （dp[i-1][j-k*count]）

        for(int k=0; k<=types[0][0]&&k*types[0][1]<=target; k++){
            dp[0][k*types[0][1]]++;
        }

        for (int i = 1; i < types.length ; i++){
            dp[i][0] = 1;
            for (int j = 1; j <= target ; j++){
                for (int k = 0 ; k < types[i][0] && k* types[i][1] < j ; k++){
                    dp[i][j] += dp[i-1][j-k*types[i][1]];
                    dp[i][j] %= mod;
                }
            }
        }


        return dp[types.length-1][target] % mod;

    }
}
