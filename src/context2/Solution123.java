package context2;

import context.Solution381;
import context.week5.UndergroundSystem;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Solution123 {
    public String triangleType(int[] nums) {
        Arrays.sort(nums);
        int first = nums[0];
        int second = nums[1];
        int third = nums[2];

        if (first == second && second == third){
            return "equilateral";
        }
        if (first + second <= third){
            return "none";
        }
        if (first != second && second != third){
            return "scalene";
        }
        return "isosceles";
    }


    public int numberOfPairs(int[][] points) {
        Arrays.sort(points, (o1, o2) -> o1[0] == o2[0]? o2[1] - o1[1]: o1[0] - o2[0]);

        int n = points.length;
        int ans = 0;

        for (int i = 0 ; i < n ; i++){
            int y = points[i][1];
            int maxY = Integer.MIN_VALUE;
            for (int j = i+1 ; j < n ; j++){
                int y1 = points[j][1];
                if (maxY < y1 && y1 <= y ){
                    maxY = y1;
                    ans++;
                }
            }
        }
        return ans;
    }


    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long sum = 0;
        Map<Integer,Long> map = new HashMap<>();
        long res = Long.MIN_VALUE;
        for (int i = 0 ; i < n ; i++){
            sum += nums[i];
            int first = nums[i] - k;
            int second = nums[i] + k;
            Long value = map.getOrDefault(nums[i], Long.MAX_VALUE);

            if (sum < value) {
                map.put(nums[i], sum);
            }

            if (map.containsKey(first)){
                res = Math.max(res,sum - map.get(first) + first);
            }

            if (map.containsKey(second)){
                res = Math.max(res,sum - map.get(second) + second);
            }
        }

        return res == Long.MIN_VALUE? 0 : res;
    }

    public static void main(String[] args) {
        Solution123 solution = new Solution123();
        solution.maximumSubarraySum(new int[]{-1,-2,-3,-4},2);
    }
}
