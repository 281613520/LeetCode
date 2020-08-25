package BackTracing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 491. 递增子序列
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 *
 * 示例:
 *
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 *
 * 主要问题在于去重
 */
public class FindSubsequences {
    /**
     * 方法1 利用set去重
     */
    Set<List<Integer>> res1 = new HashSet<>();
    List<Integer> tmp = new ArrayList<>();
    public  List<List<Integer>> findSubsequences1(int[] nums) {
        dfs1(nums,0);
        return new ArrayList<>(res1);
    }

    private  void dfs1(int[] nums, int location) {
        if (location == nums.length){
            if (tmp.size() >= 2){
                res1.add(new ArrayList<>(tmp));
            }
            return;
        }

        if (tmp.size() >= 2){
            res1.add(new ArrayList<>(tmp));
        }

        if (tmp.size() == 0 || tmp.get(tmp.size()-1) <= nums[location]){
            tmp.add(nums[location]);
            dfs1(nums,location+1);
            tmp.remove(tmp.size()-1);
        }

        dfs1(nums,location+1);
    }

    /**
     * 方法2 ：因为数列是递增的
     * 那如何保证没有重复呢？我们需要给「不选择」做一个限定条件，只有当当前的元素不等于上一个选择的元素的时候，
     * 才考虑不选择当前元素，直接递归后面的元素。因为如果有两个相同的元素，我们会考虑这样四种情况：
     *
     * 前者被选择，后者被选择
     * 前者被选择，后者不被选择
     * 前者不被选择，后者被选择
     * 前者不被选择，后者不被选择
     * 其中第二种情况和第三种情况其实是等价的，我们这样限制之后，舍弃了第二种，保留了第三种，于是达到了去重的目的。
     */
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(nums,0);
        return res;
    }

    private void dfs(int[] nums, int location) {
        if (location == nums.length){
            if (tmp.size() >= 2){
                res.add(new ArrayList<>(tmp));
            }
            return;
        }

        if (tmp.size() == 0 || tmp.get(tmp.size()-1) <= nums[location]){
            tmp.add(nums[location]);
            dfs(nums,location+1);
            tmp.remove(tmp.size()-1);
        }

        if (tmp.size() == 0 ||tmp.get(tmp.size()-1) != nums[location]){
            dfs(nums,location+1);
        }
    }
}
