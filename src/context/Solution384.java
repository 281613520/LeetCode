package context;

import java.util.Arrays;

public class Solution384 {
    public int[][] modifiedMatrix(int[][] matrix) {
        for (int j = 0; j < matrix[0].length; j++) {
            int max = 0;

            for (int i = 0; i < matrix.length; i++) {
                max = Math.max(max, matrix[i][j]);
            }

            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = max;
                }
            }
        }

        return matrix;

    }


    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            boolean flag = true;

            if (i + pattern.length > nums.length - 1) {
                break;
            }

            for (int l = 0; l < pattern.length; l++) {
                if (pattern[l] == 1) {
                    if (nums[i + l + 1] <= nums[i + l]) {
                        flag = false;
                    }
                } else if (pattern[l] == 0) {
                    if (nums[i + l + 1] != nums[i + l]) {
                        flag = false;
                    }
                } else if (pattern[l] == -1) {
                    if (nums[i + l + 1] >= nums[i + l]) {
                        flag = false;
                    }
                }
            }

            if (flag) {
                ans++;
            }
        }

        return ans;
    }


    public int maxPalindromesAfterOperations(String[] words) {
        // 不限制移动次数 -》 统计完整体的数据分布后 贪心分布
        int n = words.length;

        int[] nums = new int[26];
        int[] mark = new int[n];

        for (int i = 0; i < n; i++) {
            mark[i] = words[i].length();
            for (int j = 0; j < words[i].length(); j++) {
                nums[words[i].charAt(j) - 'a']++;
            }
        }

        int oddSum = 0;
        int evenSum = 0;

        for (int num : nums) {
            if (num %2 ==1){
                oddSum++;
                evenSum += num-1;
            }else {
                if (num != 0){
                    evenSum += num;
                }
            }
        }

        int ans = 0;

        if (oddSum == 0){
            return n;
        }else {
            Arrays.sort(mark);
            for (int count : mark) {
                if (count %2 == 0){
                    if (evenSum >= count){
                        evenSum -= count;
                        ans++;
                    }
                }else {
                    if (oddSum > 0){
                        if (evenSum >= (count-1)){
                            evenSum -= (count-1);
                            oddSum--;
                            ans++;
                        }
                    }else {
                        if(evenSum>=count){
                            evenSum-=count;
                            ans++;
                        }
                    }
                }
            }
        }

        return ans;


    }

    public static void main(String[] args) {
        Solution384 solution384 = new Solution384();
        solution384.maxPalindromesAfterOperations(new String[]{"cb","cc"});
    }
}
