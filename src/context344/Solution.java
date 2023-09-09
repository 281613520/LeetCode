package context344;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int[] distinctDifferenceArray(int[] nums) {
        int[] diff = new int[nums.length];

        int n = nums.length;

        Set<Integer> set = new HashSet<>();

        int[] preNums = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
            preNums[i] = set.size();
        }

        set.clear();

        for (int i = n; i > 0; i--) {
            if (i < n) {
                set.add(nums[i]);
            }
            diff[i - 1] = preNums[i - 1] - set.size();
        }


        return diff;
    }


    public int[] colorTheArray(int n, int[][] queries) {
        return null;
    }

    public int minIncrements(int n, int[] cost) {
        return 1;
    }
}
