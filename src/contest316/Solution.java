package contest316;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public int appendCharacters(String s, String t) {
        int i = 0;
        int j = 0;

        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
            }
        }


        return t.length() - j;
    }

    public int countSubarrays(int[] nums, int k) {
        int res = 0;
        int location = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == k) {
                location = i;
                break;
            }
        }

        Map<Integer, Integer> left = new HashMap<>();
        left.put(0, 1);
        int count = 0;
        for (int i = location-1; i >= 0; i--) {
            if (nums[i] < k) {
                count--;
            } else {
                count++;
            }
            left.put(count, left.getOrDefault(count, 0) + 1);
        }

        count = 0;

        for (int i = location; i < nums.length; i++) {
            if (nums[i] < k) {
                count--;
            } else if (nums[i] > k){
                count++;
            }

            // 左右相等
            if (left.containsKey(-count)){
                res += left.get(-count);
            }
            // 靠左
            if (left.containsKey(-count+1)){
                res += left.get(-count+1);
            }
        }


        return res;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.countSubarrays(new int[]{3,2,1,4,5},4);
    }
}
