package context349;

import java.util.Arrays;

public class Solution {
    public int findNonMinOrMax(int[] nums) {
        if (nums.length <= 2) {
            return -1;
        }
        Arrays.sort(nums);
        return nums[1];
    }

    public String smallestString(String s) {
        char[] chars = s.toCharArray();

        int location = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'a') {
            } else {
                location = i;
                break;
            }
        }

        for (int i = location == -1? 0:location; i < chars.length; i++) {
            if (chars[i] != 'a') {
                chars[i]--;
            } else {
                break;
            }
        }

        if (location == -1) {
            chars[s.length() - 1] = 'z';
        }


        return new String(chars);
    }



    public long minCost(int[] nums, int x) {
        long res = Long.MAX_VALUE;
        int n = nums.length;

        int[][] numMinMap = new int[n][n];

        // 预处理数组  第i个位置，移动j次后的最小值
        for (int i = 0; i < n; i++) {
            numMinMap[i][0] = nums[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                numMinMap[i][j] = Math.min(numMinMap[i][j - 1], nums[(i+j) % n]);
            }
        }


        // 枚举搬运次数
        for (int k = 0 ; k < nums.length ; k++){
            long cost = (long) k*x;
            for (int i = 0  ; i < n ; i++){
                cost += numMinMap[i][k];
            }
            res = Math.min(cost,res);

        }

        return res;

    }


}
