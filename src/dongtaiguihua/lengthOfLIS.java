package dongtaiguihua;

public class lengthOfLIS {
    public int lengthOfLIS(int[] nums) {
        if(nums.length < 2){
            return nums.length;
        }

        int[] result = new int[nums.length];
        result[0] = 1;
        for (int i = 1 ; i < nums.length ; i++){
            int max = 0;
            for (int j = 0 ; j < i ; j++){
                if (nums[i] > nums[j]){
                    max = Math.max(result[j],max);
                }
            }
            result[i] = max + 1;
        }

        int ans = 0;
        for (int value : result) {
            ans = Math.max(ans, value);
        }
        return ans;
    }
}
