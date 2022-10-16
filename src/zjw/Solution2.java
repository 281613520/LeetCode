package zjw;

public class Solution2 {
    public static void main(String[] args) {
        System.out.println(jump(2));
    }

    public static int jump(int nums){
        if (nums < 3){
            return 1;
        }

        int[] dp = new int[nums+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3 ; i <= nums ; i++){
            dp[i] = dp[i-1]+dp[i-3];
        }
        return dp[nums];
    }
}
