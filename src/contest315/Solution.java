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
        int minI = -1;
        int maxI = -1;
        int i0 =-1;
        long ans = 0;

        for (int i = 0 ; i < nums.length ; i++){
            if (nums[i] == minK) minI = i;
            if (nums[i] == maxK) maxI = i;

            if (nums[i] < minK || nums[i] > maxK) i0 = i;

            ans += Math.max(0,Math.min(minI,maxI) - i0);

        }


        return ans;
    }


    public static void main(String[] args) {
        Solution s = new Solution();
        s.countSubarrays(new int[]{1,3,5,2,7,5},1,5);
    }
}
