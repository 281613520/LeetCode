package BackTracing;


import java.util.*;

/**
 * 47. 全排列 II
 * <p>
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 */
public class PermuteUnique {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrace(new ArrayList<>(), res, nums, new HashSet<>());
        return res;
    }

    private void backtrace(List<Integer> tmp, List<List<Integer>> res, int[] nums, Set<Integer> set) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (set.contains(i)) {
                continue;
            }

            // 剪枝
            if (i > 0 && nums[i] == nums[i - 1] && !set.contains(i - 1)) {
                continue;
            }

            tmp.add(nums[i]);
            set.add(i);
            backtrace(tmp, res, nums, set);
            tmp.remove(tmp.size() - 1);
            set.remove(i);

        }
    }

}
