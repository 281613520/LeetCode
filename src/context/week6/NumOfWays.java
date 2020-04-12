package context.week6;

/**
 * 你有一个 n x 3 的网格图 grid ，你需要用 红，黄，绿 三种颜色之一给每一个格子上色，且确保相邻格子颜色不同（也就是有相同水平边或者垂直边的格子颜色不同）。
 *
 * 给你网格图的行数 n 。
 *
 * 请你返回给 grid 涂色的方案数。由于答案可能会非常大，请你返回答案对 10^9 + 7 取余的结果。
 */
public class NumOfWays {
    public static int numOfWays(int n) {
        long[][] dp = new long[n][3];
        dp[0][0] = 3;

        for ( int i  = 1 ; i < 3 ; i++){
            dp[0][i] = dp[0][i-1] * 2;
        }

        for ( int i  = 1 ; i < n ; i++){
            dp[i][0] = dp[i-1][0] * 2;
        }

        for (int i = 1 ; i < n ; i++){
            for (int j = 1 ; j < 3 ; j++){
                dp[i][j] = 3 * Math.max(Math.max(dp[i-1][j - 1],dp[i-1][j]),dp[i][j - 1]);
            }
        }

        return (int) (dp[n-1][2] % (100000007));
    }

    public static void main(String[] args) {
        System.out.println(numOfWays(3));
    }
}
