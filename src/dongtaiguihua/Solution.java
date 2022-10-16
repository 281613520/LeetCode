package dongtaiguihua;

import java.util.Arrays;

public class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2;  i <= n ; i ++){
            int x = 1;
            while (i >= x*x){
                dp[i] = Math.min(dp[i],dp[i-x*x]+1);
                x++;
            }
        }
        return dp[n];
    }

    public int fib(int n) {
        if (n == 0) return n;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2 ; i <= n ; i++){
            dp[i] = dp[i-1]+ dp[i-2];
        }

        return dp[n];
    }

    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 0;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3 ; i <= n ; i++){
            dp[i] = dp[i-1]+ dp[i-2]+dp[i-3];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.tribonacci(4);
    }
}
