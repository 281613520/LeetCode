package context403;

import java.util.Arrays;

public class Solution {

    public double minimumAverage(int[] nums) {
        Arrays.sort(nums);
        double ans = Integer.MAX_VALUE;
        int n = nums.length;
        for (int i = 0 ; i < n/2 ; i++){
            ans = Math.min(ans, nums[i] + nums[n-1-i]);
        }

        return ans/2.0;
    }

    public int minimumArea(int[][] grid) {
        int minx = Integer.MAX_VALUE;
        int miny = Integer.MAX_VALUE;
        int maxx = 0;
        int maxy = 0;

        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0 ; i < m ; i++){
            for (int j = 0 ; j < n ; j++){
                if (grid[i][j] ==1 ){
                    minx = Math.min(minx, i);
                    maxx = Math.max(maxx, i);
                    miny = Math.min(miny, j);
                    maxy = Math.max(maxy, j);
                }
            }
        }

        return (maxx - minx+1) * (maxy - miny+1);
    }


    public long maximumTotalCost(int[] nums) {
            // dp 0 代表以i开头  1 代表不以i开头
         // 从0～i i是否为正 。i正代表前面正负皆可 i负代表前面一定是正数
        int n = nums.length;
        long[][] dp = new long[n+1][2];

        dp[0][0] = nums[0];
        dp[0][1] = -Long.MAX_VALUE/2;

        for (int i = 1 ; i < n ; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]) + nums[i];
            dp[i][1] = dp[i-1][0] - nums[i];
        }

        return Math.max(dp[n-1][0],dp[n-1][1]);
    }
    // todo



}
