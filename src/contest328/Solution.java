package contest328;

import context.week5.UndergroundSystem;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] res = new int[n][n];


        for (int[] cur : queries) {
            int startX = cur[0];
            int startY = cur[1];
            int endX = cur[2];
            int endY = cur[3];

            for (int i = startX; i <= endX; i++) {
                for (int j = startY; j <= endY; j++) {
                    res[i][j]++;
                }
            }
        }

        return res;
    }

    public long countGood(int[] nums, int k) {
            Map<Integer,  Integer> map = new HashMap<>();

            int left = 0 ,right = 0,pairs = 0;
            long cnt = 0;

            while (right< nums.length) {
                pairs += map.getOrDefault(nums[right],0);
                map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

                while (pairs >= k && left <= right){
                    pairs -= map.get(nums[left]) -1;
                    map.put(nums[left], map.get(nums[left]) - 1);
                    left++;
                }

                cnt += left;
                right++;
            }

            return cnt;
    }

    public long maxOutput(int n, int[][] edges, int[] price) {
        // 正常思路是遍历边 来算以a为根结点的最大值

        long ans = 0;
        return ans;
    }

}
