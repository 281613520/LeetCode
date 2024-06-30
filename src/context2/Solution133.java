package context2;

public class Solution133 {

    public int minimumOperations(int[] nums) {
        int ans = 0;


        for (int num : nums) {
            ans += num % 3 != 0 ? 1 : 0;
        }

        return ans;
    }


    public int minOperations(int[] nums) {
        int ans = 0;

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] == 0) {
                nums[i + 1] ^= 1;
                nums[i + 2] ^= 1;
                ans++;
            }
        }

        return nums[nums.length - 1] != 0 && nums[nums.length - 2] != 0 ? ans : -1;
    }

    //01101
    //10010
    //11101
    //11110
    //11111 4
    public int minOperations2(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            if (num == 0 && ans%2 == 0){
                ans++;
            }else if (num ==1 && ans%2 == 1){
                // 这时候也是0
                ans++;
            }
        }
        return ans;
    }
}
