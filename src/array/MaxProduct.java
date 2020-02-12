package array;

/**
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 *
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 */
public class MaxProduct {

    public int maxProduct(int[] nums) {
        int max = nums[0];
        int premax = nums[0];
        int premin = nums[0];
        int curmax,curmin;
        for (int i = 1 ; i < nums.length ; i++){
            curmax = Math.max(nums[i],Math.max(premax*nums[i],premin*nums[i]));
            curmin =Math.min(nums[i],Math.min(premax*nums[i],premin*nums[i]));
            premax = curmax;
            premin = curmin;
            max = Math.max(max,curmax);
        }
        return max;
    }
}
