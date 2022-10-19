package dongtaiguihua;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

    public int climbStairs(int n) {
     // dp[i] = dp[i-1]+ dp[i-2]
        if (n == 0 || n == 1) return 1;
        if (n ==2) return 2;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n ; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n+1];
        if (n < 3) return 0;

        // dp[i] = min(dp[i-1] + cost[i-1],dp[i-2] + cost[i-2])
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 0;
        for (int i = 3 ; i <= n ; i++){
            dp[i] = Math.min(dp[i-1]+cost[i-1],dp[i-2]+cost[i-2]);
        }

        return dp[n];
    }

    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0;
        int j = 0;
        int ans = 0;
        int n = fruits.length;
        while (j < n) {
            map.put(fruits[j], map.getOrDefault(fruits[j], 0) + 1);
            while (map.size() > 2) {
                map.put(fruits[i], map.getOrDefault(fruits[i], 0) - 1);
                if (map.get(fruits[i]) == 0) {
                    map.remove(fruits[i]);
                }
                i++;
            }
            ans = Math.max(ans, j - i + 1);
            j++;
        }
        return ans;
    }

    public int rob(int[] nums) {
        int n = nums.length;
        if (n <= 1) return nums[0];
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        dp[2] = Math.max(nums[0], nums[1]);
        // dp[i] = max(dp[i-1],nums[i-1] + dp[i-2])

        for (int i = 3; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }

        return dp[nums.length];
    }

    /**
     * 由于选一个i，就删除两边所有的数字 i-1,i+1 -->只能隔一位取数字，因此可以转换为rob题
     * 同时，需要处理数组为每个位置的个数数组，为after
     * dp[i] = max(dp[i-1]，dp[i-2] + i * after[i])
     *
     * @param nums
     * @return
     */
    public int deleteAndEarn(int[] nums) {
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums) {
            max = Math.max(max, i);
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        int[] after = new int[max + 1];

        map.forEach((k, v) -> after[k] = v);

        int[] dp = new int[max + 1];
        dp[0] = 0;
        dp[1] = after[1];
        if (after.length == 2) {
            return after[1];
        }
        dp[2] = Math.max(after[1], after[2] * 2);

        for (int i = 3; i <= max; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + i * after[i]);
        }

        return dp[max];
    }

    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && nums[j] >= i - j) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[nums.length - 1];
    }

    public int jump(int[] nums) {
        if (nums.length == 1){
            return 0;
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1 ; i < nums.length  ; i++){
            for (int j = 0 ; j < i; j++){
                if (i-j <= nums[j]) {
                    dp[i] = Math.min(dp[i],dp[j]+1);
                }
            }
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.tribonacci(4);
    }
}
