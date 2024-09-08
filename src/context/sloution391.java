package context;

public class sloution391 {
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int s = 0;
        for (int y = x; y != 0; y /= 10) {
            s += y % 10;
        }
        return x % s != 0 ? -1 : s;
    }


    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int drinkNums = 0;
        int emptyNums = 0;

        while ( numBottles > 0 || numBottles + emptyNums >= numExchange) {
            if (numBottles > 0){
                drinkNums+= numBottles;
                emptyNums+= numBottles;
                numBottles = 0;
            }else {
                if (emptyNums >= numExchange){
                    emptyNums -= numExchange;
                    numExchange++;
                    numBottles++;
                }
            }
        }
        return drinkNums;
    }

    public long countAlternatingSubarrays(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        long sum = 1;
        for (int i = 1 ; i < n ; i++){
            if (nums[i] != nums[i-1]){
                dp[i] = dp[i-1]+1;
            }else {
                dp[i] = 1;
            }
            sum += dp[i];
        }

        return sum;
    }

    public int minimumDistance(int[][] points) {
        return 1;
    }
}
