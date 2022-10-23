package contest315;

import java.util.*;

public class Solution {

    public int findMaxK(int[] nums) {
        Arrays.sort(nums);
        Set<Integer> map = new HashSet<>();

        int ans = -1;
        for (int value : nums) {
            if (value < 0) {
                map.add(value);
            } else {
                int tmp = -value;
                if (map.contains(tmp)) {
                    ans = Math.max(ans, value);
                }
            }
        }

        return ans;
    }

    public int countDistinctIntegers(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int value : nums) {
            set.add(value);
            int ans = reverseNum(value);
            set.add(ans);
        }

        return set.size();
    }

    private int reverseNum(int value) {
        int ans = 0;

        while (value > 0) {
            ans = ans * 10 + value % 10;
            value = value / 10;
        }
        return ans;
    }

    public boolean sumOfNumberAndReverse(int num) {
        if (num == 0) return true;
        for (int i = 1 ; i < num ; i++){
            if (i + reverseNum(i) == num){
                return true;
            }
        }

        return false;
    }

    public long countSubarrays(int[] nums, int minK, int maxK) {
        return 0;
    }
}
