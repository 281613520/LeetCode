package BackTracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 */
public class SubsetsWithDup {
    List<Integer> t = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        dfs(false, 0, nums);
        return ans;
    }

    public void dfs(boolean choosePre, int cur, int[] nums) {
        if (cur == nums.length) {
            ans.add(new ArrayList<>(t));
            return;
        }
        dfs(false, cur + 1, nums);
        if (!choosePre && cur > 0 && nums[cur - 1] == nums[cur]) {
            return;
        }
        t.add(nums[cur]);
        dfs(true, cur + 1, nums);
        t.remove(t.size() - 1);
    }


    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        dfs2(nums, 0, res, new ArrayList<>());

        return res;
    }

    private void dfs2(int[] nums, int i, List<List<Integer>> res, List<Integer> tmp) {

        res.add(new ArrayList<>(tmp));

        for (int j = i; j < nums.length; j++) {
            if ( j > i && nums[j] == nums[j-1]){
                continue;
            }
            tmp.add(nums[j]);
            dfs2(nums, j + 1, res, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }


    public static void main(String[] args) {
        SubsetsWithDup subsetsWithDup = new SubsetsWithDup();


        subsetsWithDup.subsetsWithDup2(new int[]{1, 2, 2});
    }
}
