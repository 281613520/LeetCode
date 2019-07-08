package Math;

import java.util.Arrays;

public class ThreeSumClosest {
    public static int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int result = 0;
            int cha = Integer.MAX_VALUE;
            for (int i = 0 ; i < nums.length ; i++){
                int j = i + 1;
                int k = nums.length - 1;
                while (j < k){
                    if (nums[i] + nums[j] + nums[k] - target > 0){
                        int tmp = cha;
                        cha = Math.min(nums[i] + nums[j] + nums[k] - target,cha);
                        if (tmp != cha){
                            result = nums[i] + nums[j] + nums[k];
                        }
                        k--;
                    }else if (nums[i] + nums[j] + nums[k] - target < 0){
                        int tmp = cha;
                        cha = Math.min(-nums[i] - nums[j] - nums[k] + target,cha);
                        if (tmp != cha){
                            result = nums[i] + nums[j] + nums[k];
                        }
                        j++;
                    }else if (nums[i] + nums[j] + nums[k] - target == 0){
                        return nums[i] + nums[j] + nums[k];
                    }
                }
            }

            return result;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,2};
        System.out.println(String.valueOf(threeSumClosest(nums,3)));
    }
}
