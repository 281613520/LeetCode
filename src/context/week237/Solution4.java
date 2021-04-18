package context.week237;


public class Solution4 {
    public int getXORSum(int[] arr1, int[] arr2) {
        int xor = 0;
        for (int num : arr2) {
            xor ^= num;
        }
        int res = 0;
        for (int num : arr1) {
            res ^= xor & num;
        }
        return res;
    }
}
