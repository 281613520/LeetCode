package array;

/**
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * <p>
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * <p>
 * 说明：
 * <p>
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 * <p>
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 * <p>
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class GetPermutation {
    public String getPermutation(int n, int k) {
        int[] tmp = new int[n];
        for (int i = 0; i < n; i++) {
            tmp[i] = i + 1;
        }

        StringBuilder result = new StringBuilder();

        while (n > 0) {
            int pos = (k - 1) / fac(n - 1);
            result.append(tmp[pos]);
            //去掉选走的元素
            if (n - 1 - pos >= 0) System.arraycopy(tmp, pos + 1, tmp, pos, n - 1 - pos);
            k = k - pos * fac(n - 1);
            n--;
        }


        return result.toString();
    }

    private int fac(int n) {
        int result = 1;
        while (n > 0) {
            result = result * n;
            n--;
        }
        return result;
    }
}
