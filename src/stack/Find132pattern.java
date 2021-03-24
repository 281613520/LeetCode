package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 456. 132 模式
 *
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 *
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 *
 *  
 *
 * 进阶：很容易想到时间复杂度为 O(n^2) 的解决方案，你可以设计一个时间复杂度为 O(n logn) 或 O(n) 的解决方案吗？
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 解释：序列中不存在 132 模式的子序列。
 * 示例 2：
 *
 * 输入：nums = [3,1,4,2]
 * 输出：true
 * 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
 * 示例 3：
 *
 * 输入：nums = [-1,3,2,0]
 * 输出：true
 * 解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
 *
 * 单调栈的一个应用
 *
 */
public class Find132pattern {
    public boolean find132pattern(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(nums[nums.length - 1]);
        int Max_2 = Integer.MIN_VALUE;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < Max_2) {
                return true;
            }
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                Max_2 = stack.pop();
            }

            if (nums[i] > Max_2){
                stack.push(nums[i]);
            }

        }

        return false;
    }
}
