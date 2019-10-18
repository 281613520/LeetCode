package math;

public class MissingElement {
    public int missingElement(int[] nums, int k) {
        for (int i = 1 ; i < nums.length ; i++){
            int a = nums[i];
            int b = nums[i - 1];
            if (b - a - 1> k){
                return a + k;
            }
            k = k-(b - a - 1);
        }

        return k+nums[nums.length - 1];
    }

    public static void main(String[] args) {
        System.out.printf(String.valueOf('a' - 'Z'));
    }
}
