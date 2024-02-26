package context;

import java.util.HashMap;
import java.util.Map;

public class Solution382 {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> numCount = new HashMap<>();
        int ans = 0;

        for (int num : nums) {
            numCount.merge((long) num, 1, Integer::sum);
        }

        Integer c1 = numCount.remove(1L);
        ans = c1 != null ? c1 - 1 | 1 : 0;

        for (Long l : numCount.keySet()) {
            int res = 0;
            for (; numCount.getOrDefault(l, 0) > 1; l = l * l) {
                res += 2;
            }
            ans = Math.max(ans, res + (numCount.containsKey(l) ? 1 : -1));
        }
        return ans;
    }
}
