package dongtaiguihua;

/**
 * 给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,6,5,1,8]
 * 输出：18
 * 解释：选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。
 * 示例 2：
 *
 * 输入：nums = [4]
 * 输出：0
 * 解释：4 不能被 3 整除，所以无法选出数字，返回 0。
 * 示例 3：
 *
 * 输入：nums = [1,2,3,4,4]
 * 输出：12
 * 解释：选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）。
 */
public class MaxSumDivThree {
    public static int maxSumDivThree(int[] nums) {
        int[][] dp = new int[nums.length+1][3];

        int sum = 0;
        for (int i : nums){
            sum += i;
        }
        if (sum %3 ==0) return sum;

        int tmp = nums[0] % 3;

        for (int i = 1 ; i < 3 ; i++){
            dp[0][i] = Integer.MIN_VALUE;
        }
        dp[0][tmp] = nums[0];

        for(int i = 1 ; i < nums.length ;i++){
            int j = nums[i] % 3;

            if (j == 0){
                dp[i][0] =Math.max(dp[i-1][0],dp[i-1][0] + nums[i]);
                //保证相加余数仍然为1
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][1] + nums[i]);
                //保证相加余数任然为2
                dp[i][2] = Math.max(dp[i-1][2],dp[i-1][2] + nums[i]);
            }

            if (j == 1){
                dp[i][0] =Math.max(dp[i-1][0],dp[i-1][2] + nums[i]);
                dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0] + nums[i]);
                dp[i][2] =Math.max(dp[i-1][2],dp[i-1][1] + nums[i]);
            }
            if (j == 2){
                dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1] + nums[i]);
                dp[i][1] = Math.max(dp[i-1][1],dp[i-1][2] + nums[i]);
                dp[i][2] =Math.max(dp[i-1][2],dp[i-1][0] + nums[i]);
            }
        }

        return dp[nums.length - 1][0];
    }

    public static void main(String[] args) {
        System.out.println( maxSumDivThree(new int[]{1,2,3,4,4}));
    }
}
