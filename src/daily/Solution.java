package daily;

import javax.swing.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int[] dislike : dislikes) {
            if (graph.containsKey(dislike[0])) {
                graph.get(dislike[0]).add(dislike[1]);
            } else {
                graph.put(dislike[0], new HashSet<Integer>() {{
                    add(dislike[1]);
                }});
            }
            if (graph.containsKey(dislike[1])) {
                graph.get(dislike[1]).add(dislike[0]);
            } else {
                graph.put(dislike[1], new HashSet<Integer>() {{
                    add(dislike[0]);
                }});
            }
        }

        // 默认丢在set1 中
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int i = 1; i <= n; i++) {
            if (!set1.contains(i) && !set2.contains(i)) {
                if (!dfs(i, set1, set2, graph, 1)) {
                    return false;
                }
            }
        }


        return true;
    }

    private boolean dfs(int num, Set<Integer> set1, Set<Integer> set2, Map<Integer, Set<Integer>> graph, int status) {
        Set<Integer> tmp = graph.getOrDefault(num, new HashSet<>());

        if (status == 1) {
            set1.add(num);
        } else if (status == 2) {
            set2.add(num);
        }

        for (int value : tmp) {
            if ((set1.contains(value) && set1.contains(num)) || (set2.contains(value) && set2.contains(num))) {
                return false;
            }

            if (!set1.contains(value) && !set2.contains(value)) {
                if (!dfs(value, set1, set2, graph, 3 - status)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0;
        int j = 0;
        int ans = 0;
        int n = fruits.length;
        while (j < n) {
            map.put(fruits[j], map.getOrDefault(fruits[j], 0) + 1);
            while (map.size() > 2) {
                map.put(fruits[i], map.getOrDefault(fruits[i], 0) - 1);
                if (map.get(fruits[i]) == 0) {
                    map.remove(fruits[i]);
                }
                i++;
            }
            ans = Math.max(ans, j - i + 1);
            j++;
        }
        return ans;
    }

    public int rob(int[] nums) {
        int n = nums.length;
        if (n <= 1) return nums[0];
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        dp[2] = Math.max(nums[0], nums[1]);
        // dp[i] = max(dp[i-1],nums[i-1] + dp[i-2])

        for (int i = 3; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }

        return dp[nums.length];
    }

    /**
     * 由于选一个i，就删除两边所有的数字 i-1,i+1 -->只能隔一位取数字，因此可以转换为rob题
     * 同时，需要处理数组为每个位置的个数数组，为after
     * dp[i] = max(dp[i-1]，dp[i-2] + i * after[i])
     * @param nums
     * @return
     */
    public int deleteAndEarn(int[] nums) {
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums) {
            max = Math.max(max, i);
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        int[] after = new int[max + 1];

        map.forEach((k, v) -> after[k] = v);

        int[] dp = new int[max + 1];
        dp[0] = 0;
        dp[1] = after[1];
        if (after.length ==2){
            return after[1];
        }
        dp[2] = Math.max(after[1], after[2]*2);

        for (int i = 3; i <= max; i++) {
            dp[i] = Math.max(dp[i-1] , dp[i-2] + i * after[i]);
        }

        return dp[max];
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        //s.possibleBipartition(3, new int[][]{{1, 2}, {1, 3}, {2, 3}});
       // s.totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4});
        s.deleteAndEarn(new int[]{2,2,3,3,3,4});
    }
}
