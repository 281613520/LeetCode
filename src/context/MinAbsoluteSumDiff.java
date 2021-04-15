package context;

import java.util.Arrays;

/**
 * 1818. 绝对差值和
 * <p>
 * 给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。
 * <p>
 * 数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始）。
 * <p>
 * 你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。
 * <p>
 * 在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 109 + 7 取余 后返回。
 * <p>
 * |x| 定义为：
 * <p>
 * 如果 x >= 0 ，值为 x ，或者
 * 如果 x <= 0 ，值为 -x
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,7,5], nums2 = [2,3,5]
 * 输出：3
 * 解释：有两种可能的最优方案：
 * - 将第二个元素替换为第一个元素：[1,7,5] => [1,1,5] ，或者
 * - 将第二个元素替换为第三个元素：[1,7,5] => [1,5,5]
 * 两种方案的绝对差值和都是 |1-2| + (|1-3| 或者 |5-3|) + |5-5| = 3
 * 示例 2：
 * <p>
 * 输入：nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
 * 输出：0
 * 解释：nums1 和 nums2 相等，所以不用替换元素。绝对差值和为 0
 * 示例 3：
 * <p>
 * 输入：nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
 * 输出：20
 * 解释：将第一个元素替换为第二个元素：[1,10,4,4,2,7] => [10,10,4,4,2,7]
 * 绝对差值和为 |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20
 */
public class MinAbsoluteSumDiff {
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        long res = 0;
        int[] tmp = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res += Math.abs(nums1[i] - nums2[i]);
            tmp[i] = nums1[i];
        }

        long origin = res;

        Arrays.sort(tmp);

        for (int i = 0; i < nums1.length; i++) {
            int cur = nums2[i];
            int ans = findMin(tmp, cur);
            int pre = Math.abs(nums2[i] - nums1[i]);
            res = Math.min(res, origin - pre + ans);
        }

        return (int) (res % 1000000007);
    }

    private int findMin(int[] nums1, int cur) {
        int l = 0, r = nums1.length - 1;

        int lnum = nums1[l], rnum = nums1[r];

        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums1[mid] == cur) {
                return 0;
            } else if (nums1[mid] < cur) {
                lnum = nums1[mid];
                l = mid + 1;
            } else {
                rnum = nums1[mid];
                r = mid - 1;
            }
        }

        return Math.min(Math.abs(lnum - cur),Math.abs(rnum - cur));
    }

    public static void main(String[] args) {
        //[1,10,4,4,2,7]
        //[9,3,5,1,7,4]
        MinAbsoluteSumDiff minAbsoluteSumDiff = new MinAbsoluteSumDiff();
        minAbsoluteSumDiff.minAbsoluteSumDiff(new int[]{1,10,4,4,2,7},new int[]{9,3,5,1,7,4});
    }
}
