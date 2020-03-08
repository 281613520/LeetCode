package dongtaiguihua;

import java.util.Arrays;

/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 * <p>
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 */
public class NumSquares {
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        if (n < 2) return 1;
        for (int i = 2; i <= n; i++) {
            int x = 1;
            while ( i >= x * x){
                //因为dp[x*x] = 1
                dp[i] = Math.min(dp[i],dp[i - x*x] + 1);
                x++;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numSquares(12));
    }
}
