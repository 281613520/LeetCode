package BackTracing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. 组合总和 II
 *
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        // 这一步为了下一步做准备
        Arrays.sort(candidates);
        help(list,result,candidates,target,-1);
        return result;
    }

    public void help(List<Integer> list,List<List<Integer>> result,int[] candidates,int target,int curLocation){
        if(target == 0 ){
            result.add(new ArrayList<>(list));
        }
        if (target < 0) return;
        for(int i = curLocation + 1;i < candidates.length ; i++ ){
            // 小剪枝 防止重复
            if (i > curLocation+1 && candidates[i] == candidates[i - 1]) {
                continue;
            }

            if(target - candidates[i] >=0){
                list.add(candidates[i]);
                help(list, result, candidates, target - candidates[i], i);
                list.remove(list.size()-1);
            }
        }
    }
}
