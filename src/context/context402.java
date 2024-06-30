package context;

import java.net.Inet4Address;
import java.util.*;

public class context402 {
    public int countCompleteDayPairs(int[] hours) {
        int count = 0;
        for (int i = 0; i < hours.length; i++) {
            for (int j = i+1; j < hours.length; j++) {
                int sum = hours[i] + hours[j];
                if (sum % 24 == 0) {
                    count++;
                }
            }
        }
        return count;
    }


    public long countCompleteDayPairs2(int[] hours) {
        Map<Integer, Long> map = new HashMap<>();
        long count = 0;

        for (int hour : hours) {
            int yushu = hour % 24;
            int cur = (24 - yushu%24)%24;
            if (map.containsKey(cur)) {
                count += map.get(cur);
            }
            map.put(yushu, map.getOrDefault(yushu, 0l) + 1);
        }
        return count;
    }


    public long maximumTotalDamage(int[] power) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : power) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        int n = map.size();
        int[] a = new int[n];
        int k = 0;
        for (Integer cur : map.keySet()) {
            a[k++] = cur;
        }
        Arrays.sort(a);
        long[] dp = new long[n+1];
        int j = 0;
        for (int i = 0; i < n; i++) {
            int x = a[i];
            while (a[j] < x-2){
                j++;
            }
            dp[i+1] = Math.max(dp[i],dp[j] + (long)a[i] * map.get(a[i]));
        }
        return dp[n];
    }


    public List<Integer> countOfPeaks(int[] nums, int[][] queries) {
        return new ArrayList<>();
    }
}
