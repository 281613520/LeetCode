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
        for (int i = 1; i < nums.length - 1; i++) {
            if (tmp <= 0) {
                tmp += nums[i];
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

        for (int i = 1; i < nums.length; i++) {
            max = Math.max(Math.max(premax * nums[i], premin * nums[i]), nums[i]);
            min = Math.min(Math.min(premin * nums[i], premax * nums[i]), nums[i]);

            premax = max;
            premin = min;
            ans = Math.max(premax, ans);
        }


        return ans;
    }

    public int maxScoreSightseeingPair(int[] values) {
        int res = 0, max = values[0] + 0;

        for (int j = 1; j < values.length; j++) {
            res = Math.max(res, max + values[j] - j);
            max = Math.max(max, values[j] + j);
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
        int[][] dp = new int[prices.length + 1][2];

        dp[1][0] = 0;
        dp[1][1] = -prices[0];


        for (int i = 2; i <= prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][1] + prices[i - 1], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i - 1], dp[i - 1][1]);
        }


        return dp[prices.length][0];
    }

    public int maxProfit3(int[] prices) {
        // 0 代表卖出
        // 1 代表买入
        // 2 代表冷冻期
        int[][] dp = new int[prices.length + 1][3];

        dp[1][0] = 0;
        dp[1][1] = -prices[0];
        dp[1][2] = 0;

        for (int i = 2; i <= prices.length; i++) {
            dp[i][0] = dp[i - 1][1] + prices[i - 1];
            dp[i][1] = Math.max(dp[i - 1][2] - prices[i - 1], dp[i - 1][1]);
            dp[i][2] = Math.max(dp[i - 1][0], dp[i - 1][2]);
        }


        return Math.max(dp[prices.length][0], dp[prices.length][2]);
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] res = new boolean[s.length() + 1];
        res[0] = true;
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 0; j < i; j++) {
                if (res[j] && wordDict.contains(s.substring(j, i))) {
                    res[i] = true;
                    break;
                }
            }
        }
        return res[s.length()];
    }


    public int trap(int[] height) {
        int[] left = new int[height.length];
        int[] right = new int[height.length];

        int left_max = height[0];
        int right_max = height[height.length - 1];

        for (int i = 0; i < height.length; i++) {
            left_max = Math.max(left_max, height[i]);
            left[i] = left_max;
        }

        for (int i = height.length - 1; i >= 0; i--) {
            right_max = Math.max(right_max, height[i]);
            right[i] = right_max;
        }

        int res = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int tmp = Math.min(left[i], right[i]);
            if (tmp > height[i]) {
                res += tmp - height[i];
            }
        }
        return res;

    }


    public int numberOfArithmeticSlices(int[] nums) {
        return 1;
    }


    public int numDecodings(String s) {
        int[] dp = new int[s.length()];

        if (s.charAt(0) == '0') {
            dp[0] = 0;
        } else {
            dp[0] = 1;
        }

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                dp[i] += dp[i - 1];
            }
            int pre = s.charAt(i - 1) - '0';
            int now = s.charAt(i) - '0';
            int num = pre * 10 + now;
            if (num >= 10 && num <= 26) {
                if (i < 2) {
                    dp[i] = dp[i] + 1;
                } else {
                    dp[i] += dp[i - 2];
                }
            }
        }


        return dp[s.length() - 1];
    }


    public int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];

        dp[1] = 1;

        int num2 = 1;
        int num3 = 1;
        int num5 = 1;

        for (int i = 2; i <= n; i++) {
            int p2 = dp[num2] * 2;
            int p3 = dp[num3] * 3;
            int p5 = dp[num5] * 5;


            dp[i] = Math.min(p2, Math.min(p3, p5));

            if (dp[i] == p2) {
                num2++;
            }

            if (dp[i] == p3) {
                num3++;
            }

            if (dp[i] == p5) {
                num5++;
            }
        }

        return dp[n];
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int depth = triangle.size();
        int maxLength = triangle.get(depth - 1).size();
        int[][] dp = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
        dp[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < triangle.size(); i++) {
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);

            for (int j = 1; j < triangle.get(i).size() - 1; j++) {
                dp[i][j] = triangle.get(i).get(j) + Math.min(dp[i - 1][j - 1], dp[i - 1][j]);
            }


            dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
        }

        int ans = dp[depth - 1][0];


        for (int i = 1; i < maxLength; i++) {
            ans = Math.min(ans, dp[depth - 1][i]);
        }

        return ans;

    }

    public int minFallingPathSum(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        System.arraycopy(matrix[0], 0, dp[0], 0, col);


        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (j == 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j + 1]) + matrix[i][j];
                    continue;
                }

                if (j == col - 1) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + matrix[i][j];
                    continue;
                }
                dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i - 1][j + 1]) + matrix[i][j];
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < col; i++) {
            ans = Math.min(ans, dp[row - 1][i]);
        }
        return ans;
    }


    public int[][] matrixBlockSum(int[][] mat, int k) {
        int row = mat.length;
        int col = mat[0].length;
        int[][] preMat = new int[row][col];
        preMat[0][0] = mat[0][0];
        for (int i = 1; i < row; i++) {
            preMat[i][0] = preMat[i - 1][0] + mat[i][0];
        }

        for (int j = 1; j < col; j++) {
            preMat[0][j] = preMat[0][j - 1] + mat[0][j];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                preMat[i][j] = preMat[i - 1][j] + preMat[i][j - 1] - preMat[i-1][j-1] + mat[i][j];
            }
        }

        int[][] ans = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int left_x = Math.max(i - k, 0);
                int left_y = Math.max(j - k, 0);
                int right_x = Math.min(i + k, row - 1);
                int right_y = Math.min(j + k, col - 1);

                if (left_x == 0 && left_y == 0) {
                    ans[i][j] = preMat[right_x][right_y];
                    continue;
                }

                if (left_x == 0) {
                    ans[i][j] = preMat[right_x][right_y] - preMat[right_x][left_y-1];
                    continue;
                }

                if (left_y == 0) {
                    ans[i][j] = preMat[right_x][right_y] - preMat[left_x-1][right_y];
                    continue;
                }

                ans[i][j] = preMat[right_x][right_y] - preMat[right_x][left_y-1] - preMat[left_x-1][right_y] + preMat[left_x-1][left_y-1];

            }
        }

        return ans;

    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.matrixBlockSum(new int[][]{{1,2,3},{4,5,6},{7,8,9}},1);
    }
}
