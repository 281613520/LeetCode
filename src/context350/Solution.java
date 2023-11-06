package context350;

import java.util.Arrays;

public class Solution {

    public int distanceTraveled(int mainTank, int additionalTank) {
        int n = Math.min((mainTank - 1) >> 2, additionalTank);
        return (mainTank + n) * 10;
    }


    public int specialPerm(int[] nums) {
        //先处理元素？
        return 1;
    }


    public int findValueOfPartition(int[] nums) {
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;

        for (int  i = 1 ; i < nums.length ; i++){
            ans = Math.min(nums[i]-nums[i-1], ans);
        }

        return ans;
    }

    public int paintWalls(int[] cost, int[] time) {
        return 1;
    }
}
