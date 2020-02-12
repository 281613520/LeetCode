package dongtaiguihua;

import java.util.*;

/**
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 *
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 *
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
public class LongestConsecutive {

    // solution1
    public int longestConsecutive1(int[] nums) {
        if (nums.length == 0) return 0;
        int res = 1;
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0 ; i < nums.length ; i++){
            hashSet.add(nums[i]);
        }

        for (int i = 0 ; i < nums.length ; i++){
            int cur = 1;
            int tmp = nums[i];
            while(hashSet.contains(tmp + 1)){
                cur++;
                tmp++;

            }
            res = Math.max(cur,res);
        }

        return res;
    }

    // solution2
    public int longestConsecutive2(int[] nums) {
        if (nums.length == 0) return 0;
        int res = 1;

        Set<Integer> hashSet = new HashSet<>();

        for (int i = 0 ; i < nums.length ; i++){
            hashSet.add(nums[i]);
        }

        for (int i = 0 ; i < nums.length ; i++){
            if (!hashSet.contains(nums[i] - 1)){
                int cur = 1;
                int tmp = nums[i];
                while(hashSet.contains(tmp + 1)){
                    cur++;
                    tmp++;

                }
                res = Math.max(cur,res);
            }
        }

        return res;
    }
}
