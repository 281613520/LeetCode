package dongtaiguihua;

import java.util.Arrays;

/**
 * 410. 分割数组的最大值
 * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
 *
 * 注意:
 * 数组长度 n 满足以下条件:
 *
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * 示例:
 *
 * 输入:
 * nums = [7,2,5,10,8]
 * m = 2
 *
 * 输出:
 * 18
 *
 * 解释:
 * 一共有四种方法将nums分割为2个子数组。
 * 其中最好的方式是将其分为[7,2,5] 和 [10,8]，
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 * dp[i][j] = min(for(0 < k < i )dp[k][j-1],sub(k+1,i))
 */
public class SplitArray {
    public int splitArray(int[] nums, int m) {
            int row = nums.length;
            int[][] dp = new int[row+1][m+1];

            for (int[] tmp : dp){
                Arrays.fill(tmp,Integer.MAX_VALUE);
            }

            int[] sub = new int[row + 1];

            for (int i = 0 ; i < row ; i++){
                sub[i+1] = sub[i] + nums[i];
            }

            dp[0][0] = 0;
            for (int i = 1 ; i <= row ; i++){
                for (int j = 1 ; j <= Math.min(i,m);j++){
                    for (int k = 0; k < i; k++) {
                        dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sub[i] - sub[k]));
                    }
                }
            }

            return dp[row][m];

    }
}
