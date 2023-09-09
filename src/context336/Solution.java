package context336;

import java.util.*;

public class Solution {
    public int vowelStrings(String[] words, int left, int right) {
        Set<Character> aeiou = new HashSet<>();
        aeiou.add('a');
        aeiou.add('e');
        aeiou.add('i');
        aeiou.add('o');
        aeiou.add('u');

        int ans = 0;

        for (int i = left; i <= right; i++) {
            String cur = words[i];
            if (aeiou.contains(cur.charAt(0)) && aeiou.contains(cur.charAt(cur.length() - 1))) {
                ans++;
            }

        }

        return ans;

    }


    public int maxScore(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        long s = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            s += nums[i];
            if (s <= 0) break;
            res++;
        }
        return res;
    }


    public long beautifulSubarrays(int[] nums) {
        if (nums.length == 1 && nums[0] == 0) {
            return 1;
        }

        long res = 0;

        int[] pre_sum = new int[nums.length];

        pre_sum[0] = nums[0];

        if (nums[0] == 0) {
            res++;
        }

        Map<Integer, Long> map = new HashMap<>();
        map.put(pre_sum[0], 1L);

        for (int i = 1; i < nums.length; i++) {
            pre_sum[i] = pre_sum[i - 1] ^ nums[i];
            int tmp = pre_sum[i];

            if (tmp == 0) {
                res++;
            }

            if (map.containsKey(tmp)) {
                res += map.get(tmp);
            }
            map.put(pre_sum[i], map.getOrDefault(pre_sum[i], 0L) + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.beautifulSubarrays(new int[]{0});
    }
}
