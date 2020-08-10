package context.week201;


import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个数组 nums 和一个整数 target 。
 *
 * 请你返回 非空不重叠 子数组的最大数目，且每个子数组中数字和都为 target 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,1,1], target = 2
 * 输出：2
 * 解释：总共有 2 个不重叠子数组（加粗数字表示） [1,1,1,1,1] ，它们的和为目标值 2 。
 * 示例 2：
 *
 * 输入：nums = [-1,3,5,1,4,2,-9], target = 6
 * 输出：2
 * 解释：总共有 3 个子数组和为 6 。
 * ([5,1], [4,2], [3,5,1,4,2,-9]) 但只有前 2 个是不重叠的。
 * 示例 3：
 *
 * 输入：nums = [-2,6,6,3,5,4,1,2,8], target = 10
 * 输出：3
 * 示例 4：
 *
 * 输入：nums = [0,0,0], target = 0
 * 输出：3
 */
public class Solution3 {
    public int maxNonOverlapping(int[] nums, int target) {
        int res = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - target)) {
                res++;
                sum = 0;
                map = new HashMap<>();
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return res;
    }
}
