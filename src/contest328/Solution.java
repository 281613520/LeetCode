package contest328;

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
        Map<Integer, Integer> map = new HashMap<>();
        int lo = 0;
        int hi = 0;
        long cnt = 0;
        int pairs = 0;

        while (hi < nums.length) {
            map.put(nums[hi], map.getOrDefault(nums[hi], 0) + 1);
            pairs += map.getOrDefault(nums[hi], 0);

            while (pairs >= k && lo <= hi) {
                pairs -= map.get(nums[lo]) - 1;
                map.put(nums[lo], map.get(nums[lo]) - 1);
                lo++;
            }

            cnt += lo;
            hi++;
        }

        return cnt;
    }

    public long maxOutput(int n, int[][] edges, int[] price) {

        return 0;
    }

}
