package context.week254;

import context.week5.UndergroundSystem;

import java.util.Arrays;

public class Solution2 {
    public static int[] rearrangeArray(int[] nums) {
        if (nums.length <= 2) return nums;

        int diff = nums[1] - nums[0];


        int pre_diff = Integer.MAX_VALUE;
        for (int i = 2 ; i < nums.length ; i++){

            int cur_diff = nums[i] - nums[i-1];

            if (diff == cur_diff){
               int tmp = nums[i-1];
               nums[i-1] = nums[i];
               nums[i] = tmp;
               diff = -diff;
               if (pre_diff == nums[i-1] - nums[i-2]){
                   int tmp2 = nums[i-2];
                   nums[i-2] = nums[i-1];
                   nums[i-1] = tmp2;
               }
               pre_diff = nums[i-1] - nums[i-2];
            }else {
                pre_diff = diff;
                diff = cur_diff;

            }
        }

        return nums;
    }

    public static void main(String[] args) {
            rearrangeArray(new int[]{1,2,3,4,5,6,7,8});
    }
}
