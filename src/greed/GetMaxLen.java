package greed;

/**
 * 给你一个整数数组 nums ，请你求出乘积为正数的最长子数组的长度。
 * <p>
 * 一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。
 * <p>
 * 请你返回乘积为正数的最长子数组长度。
 * <p>
 *  
 * <p>
 * 示例  1：
 * <p>
 * 输入：nums = [1,-2,-3,4]
 * 输出：4
 * 解释：数组本身乘积就是正数，值为 24 。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,-2,-3,-4]
 * 输出：3
 * 解释：最长乘积为正数的子数组为 [1,-2,-3] ，乘积为 6 。
 * 注意，我们不能把 0 也包括到子数组中，因为这样乘积为 0 ，不是正数。
 * 示例 3：
 * <p>
 * 输入：nums = [-1,-2,-3,0,1]
 * 输出：2
 * 解释：乘积为正数的最长子数组是 [-1,-2] 或者 [-2,-3] 。
 * 示例 4：
 * <p>
 * 输入：nums = [-1,2]
 * 输出：1
 * 示例 5：
 * <p>
 * 输入：nums = [1,2,3,5,-6,4,0,10]
 * 输出：4
 */
public class GetMaxLen {
    public int getMaxLen(int[] nums) {
        int[] p = new int[nums.length];
        int[] n = new int[nums.length];
        p[0] = nums[0] <= 0 ? 0 : 1;
        n[0] = nums[0] >= 0 ? 0 : 1;
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                n[i] = 0;
                p[i] = 0;
            }
            if (nums[i] > 0) {
                p[i] = p[i-1] + 1;
                n[i] = n[i-1] > 0 ? n[i-1] + 1:0;
            }
            if (nums[i] < 0) {
                p[i] = n[i-1] > 0 ? n[i-1] + 1:0;
                n[i] = p[i-1] + 1;
            }

            res = Math.max(res,p[i]);
        }

        return res;
    }
}
