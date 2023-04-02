package context337;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    public int[] evenOddBit(int n) {
        String str = Integer.toBinaryString(n);
        int odd = 0;
        int even = 0;

        for (int i = 0; i < str.length(); i++) {
            boolean flag = str.charAt(str.length() - i - 1) == '1';
            if (i % 2 == 0) {
                if (flag) {
                    even++;
                }
            } else {
                if (flag) {
                    odd++;
                }
            }
        }


        return new int[]{even, odd};
    }


    public boolean checkValidGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] graph = new boolean[m][n];


        dfs(0, 0, grid, graph, grid[0][0]);


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!graph[i][j]) {
                    return false;
                }
            }
        }

        return true;

    }

    private void dfs(int i, int j, int[][] grid, boolean[][] graph, int last) {

        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || graph[i][j]) {
            return;
        }

        if (i == 0 && j == 0 && !graph[i][j]) {
            if (!graph[0][0]) {
                graph[0][0] = true;
            } else {
                return;
            }
        }


        if (!(i == 0 && j == 0) && grid[i][j] - last != 1) {
            return;
        }

        graph[i][j] = true;

        dfs(i + 2, j + 1, grid, graph, grid[i][j]);
        dfs(i + 2, j - 1, grid, graph, grid[i][j]);
        dfs(i - 2, j + 1, grid, graph, grid[i][j]);
        dfs(i - 2, j - 1, grid, graph, grid[i][j]);
        dfs(i + 1, j + 2, grid, graph, grid[i][j]);
        dfs(i + 1, j + 2, grid, graph, grid[i][j]);
        dfs(i + 1, j - 2, grid, graph, grid[i][j]);
        dfs(i - 1, j + 2, grid, graph, grid[i][j]);
        dfs(i - 1, j - 2, grid, graph, grid[i][j]);
    }


    int[] map = new int[2001];
    int ans = 0;

    public int beautifulSubsets(int[] nums, int k) {

        dfs2(0, nums, map, k);

        return ans - 1;
    }

    private void dfs2(int i, int[] nums, int[] map, int k) {
        if (i >= nums.length) {
            return;
        }

        dfs2(i + 1, nums, map, k);

        int location = i + nums.length;

        if (map[location + k] == 0 && map[location - k] == 0) {
            map[location]++;
            ans++;
            dfs2(i + 1, nums, map, k);
            map[location]--;
        }
    }

    public int findSmallestInteger(int[] nums, int value) {
        int len = nums.length;
        Map<Integer,Integer>  map = new HashMap<>();
        for (int num : nums) {
            map.put((num % value + value) % value, map.getOrDefault((num % value + value) % value, 0) + 1);
        }

        for (int k = 0; k <= len; k++) {
            int mapKey = k % value;
            Integer mapVal = map.get(mapKey);
            if (mapVal == null) {
                return k;
            } else {
                if (mapVal == 1) {
                    map.remove(mapKey);
                } else {
                    map.put(mapKey, mapVal - 1);
                }
            }
        }
        return len;
    }
}
