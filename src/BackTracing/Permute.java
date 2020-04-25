package BackTracing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 46. 全排列
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 */
public class Permute {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrace(new ArrayList<>(),res, nums,new HashSet<>());
        return res;
    }

    private void backtrace(List<Integer> tmp, List<List<Integer>> res, int[] nums, Set<Integer> set){
        if (tmp.size() == nums.length){
            res.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = 0; i < nums.length ; i++){
            if (!set.contains(i)) {
                tmp.add(nums[i]);
                set.add(i);
                backtrace(tmp, res, nums, set);
                tmp.remove(tmp.size() - 1);
                set.remove(i);
            }
        }
    }

}
