package dongtaiguihua;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            int x = 1;
            while (i >= x * x) {
                dp[i] = Math.min(dp[i], dp[i - x * x] + 1);
                x++;
            }
        }
        return dp[n];
    }

    public int fib(int n) {
        if (n == 0) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 0;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }

    public int climbStairs(int n) {
        // dp[i] = dp[i-1]+ dp[i-2]
        if (n == 0 || n == 1) return 1;
        if (n == 2) return 2;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        if (n < 3) return 0;

        // dp[i] = min(dp[i-1] + cost[i-1],dp[i-2] + cost[i-2])
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 0;
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
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
        if (nums.length == 1) {
            return 0;
        }

        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (i - j <= nums[j]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[nums.length - 1];
    }


    public int maxSubArray(int[] nums) {
        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > 0) nums[i] += nums[i - 1];
            max = Math.max(nums[i], max);
        }

        return max;
    }

    public int maxSubarraySumCircular(int[] nums) {
        int max = nums[0];
        int sum = nums[0];
        int tmp = nums[0];

        // 求最大
        for (int i = 1; i < nums.length; i++) {
            if (tmp >= 0) {
                tmp += nums[i];
            } else {
                tmp = nums[i];
            }
            max = Math.max(tmp, max);

            sum += nums[i];
        }

        //使用到了环，则必定包含 A[n-1]和 A[0]两个元素且说明从A[1]到A[n-2]这个子数组中必定包含负数
        tmp = 0;
        int min = 0;
        for (int i = 1; i < nums.length -1; i++) {
            if (tmp <= 0) {
                tmp +=nums[i];
            } else {
                tmp = nums[i];
            }
            min = Math.min(tmp, min);
        }


        return Math.max(max, sum - min);
    }

    public int maxProduct(int[] nums) {
        int max;
        int min;
        int premax = nums[0];
        int premin = nums[0];
        int ans = nums[0];

        for (int i = 1 ; i< nums.length ; i++){
            max = Math.max(Math.max(premax*nums[i] , premin*nums[i]),nums[i]);
            min = Math.min(Math.min(premin*nums[i],premax*nums[i]),nums[i]);

            premax = max;
            premin = min;
            ans = Math.max(premax,ans);
        }


        return ans;
    }

    public int maxScoreSightseeingPair(int[] values) {
        int res = 0,max = values[0]+0;

        for (int j = 1 ; j < values.length ; j++){
            res = Math.max(res,max + values[j] - j);
            max = Math.max(max,values[j] + j);
        }

        return res;
    }

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int min = Integer.MAX_VALUE;
        for (int price : prices) {
            if (min > price) {
                min = price;
            }
            if (price - min > maxProfit) {
                maxProfit = price - min;
            }

        }
        return maxProfit;
    }


    public int maxProfit2(int[] prices) {
        // 0 代表卖出
        // 1 代表买入
        int[][] dp = new int[prices.length +1][2];

        dp[1][0] = 0;
        dp[1][1] = -prices[0];


        for (int i = 2 ; i <= prices.length ; i++){
            dp[i][0] = Math.max(dp[i-1][1] + prices[i-1],dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][0] - prices[i-1],dp[i-1][1]);
        }


        return dp[prices.length][0];
    }

    public int maxProfit3(int[] prices) {
        // 0 代表卖出
        // 1 代表买入
        // 2 代表冷冻期
        int[][] dp = new int[prices.length +1][3];

        dp[1][0] = 0;
        dp[1][1] = -prices[0];
        dp[1][2] = 0;

        for (int i = 2 ; i <= prices.length ; i++){
            dp[i][0] = dp[i-1][1] + prices[i-1];
            dp[i][1] = Math.max(dp[i-1][2] - prices[i-1],dp[i-1][1]);
            dp[i][2] = Math.max(dp[i-1][0],dp[i-1][2]);
        }


        return Math.max(dp[prices.length][0],dp[prices.length][2]);
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] res = new boolean[s.length()+1];
        res[0] = true;
        for (int i = 1 ; i < s.length() +1 ; i++){
            for (int j = 0 ; j < i ; j++){
                if (res[j] && wordDict.contains(s.substring(j,i))){
                    res[i] = true;
                    break;
                }
            }
        }
        return res[s.length()];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.maxSubarraySumCircular(new int[]{9,-4,-7,9});
    }
}
