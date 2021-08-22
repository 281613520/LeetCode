package context.week255;

import java.util.Arrays;

public class Solution1 {
    public int findGCD(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int cur : nums){
            max = Math.max(cur,max);
            min = Math.min(cur,min);
        }

        return GCD(max,min);
    }

    private int GCD(int max, int min) {
        int t = max%min;
        while(t!=0){
            max=min;
            min=t;
            t=max%min;
        }
        return min;
    }
}
