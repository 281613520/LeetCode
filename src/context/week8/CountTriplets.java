package context.week8;


/**
 * 5405. 形成两个异或相等数组的三元组数目
 * 给你一个整数数组 arr 。
 * <p>
 * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
 * <p>
 * a 和 b 定义如下：
 * <p>
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * 注意：^ 表示 按位异或 操作。
 * <p>
 * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [2,3,1,6,7]
 * 输出：4
 * 解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
 * 示例 2：
 * <p>
 * 输入：arr = [1,1,1,1,1]
 * 输出：10
 * 示例 3：
 * <p>
 * 输入：arr = [2,3]
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：arr = [1,3,5,7,9]
 * 输出：3
 * 示例 5：
 * <p>
 * 输入：arr = [7,11,12,9,5,2,7,17,22]
 * 输出：8
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 300
 * 1 <= arr[i] <= 10^8
 */
public class CountTriplets {
    public static int countTriplets(int[] arr) {

        //异或前缀和  异或的逆操作(1^2^3^4) ^(1^2) == 3^4
        int[] nums = new int[arr.length];

        nums[0] = arr[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] ^ arr[i];
        }

        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j; k < arr.length; k++) {

                    int a = i == 0? nums[j-1]:nums[i-1] ^ nums[j - 1];

                    int b = nums[j - 1] ^ nums[k];
                    if (a == b) {
                        res++;
                    }
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(countTriplets(new int[]{2, 3, 1, 6, 7}));
    }
}
