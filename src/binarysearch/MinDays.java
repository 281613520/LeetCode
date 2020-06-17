package binarysearch;


import java.util.*;

/**
 * 1482. 制作 m 束花所需的最少天数
 * 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
 *
 * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
 *
 * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
 *
 * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 1
 * 输出：3
 * 解释：让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
 * 现在需要制作 3 束花，每束只需要 1 朵。
 * 1 天后：[x, _, _, _, _]   // 只能制作 1 束花
 * 2 天后：[x, _, _, _, x]   // 只能制作 2 束花
 * 3 天后：[x, _, x, _, x]   // 可以制作 3 束花，答案为 3
 * 示例 2：
 *
 * 输入：bloomDay = [1,10,3,10,2], m = 3, k = 2
 * 输出：-1
 * 解释：要制作 3 束花，每束需要 2 朵花，也就是一共需要 6 朵花。而花园中只有 5 朵花，无法满足制作要求，返回 -1 。
 * 示例 3：
 *
 * 输入：bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
 * 输出：12
 * 解释：要制作 2 束花，每束需要 3 朵。
 * 花园在 7 天后和 12 天后的情况如下：
 * 7 天后：[x, x, x, x, _, x, x]
 * 可以用前 3 朵盛开的花制作第一束花。但不能使用后 3 朵盛开的花，因为它们不相邻。
 * 12 天后：[x, x, x, x, x, x, x]
 * 显然，我们可以用不同的方式制作两束花。
 * 示例 4：
 *
 * 输入：bloomDay = [1000000000,1000000000], m = 1, k = 1
 * 输出：1000000000
 * 解释：需要等 1000000000 天才能采到花来制作花束
 * 示例 5：
 *
 * 输入：bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2
 * 输出：9
 */
public class MinDays {
    public static int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) {
            return -1;
        }

        Set<Integer> nums = new TreeSet<>(Comparator.comparingInt(o -> o));

        for (int i : bloomDay) {
            nums.add(i);
        }

        List<Integer> list = new ArrayList<>(nums);
        int[] data = new int[list.size()];

        for (int i = 0 ;  i < data.length ; i++){
            data[i] = list.get(i);
        }

        int left = 0;
        int right = data.length - 1;
        while (left < right){
            int mid = (left + right)/2;
            int day = data[mid];
            int curM = checkFlag(bloomDay,day,k);

            if (curM < m){
                left = mid+1;
            }else {
                right = mid;
            }
        }

        return data[left];
    }

    private static int checkFlag(int[] bloomDay, int day, int k) {
        int count = 0;
        int res = 0;
        for (int value : bloomDay) {
            if (value <= day) {
                count++;
            } else {
                count = 0;
            }
            if (count == k) {
                res++;
                count = 0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(minDays(new int[]{1,10,3,10,2},3,1));
    }
}
