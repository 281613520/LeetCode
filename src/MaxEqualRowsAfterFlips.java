import context.week5.UndergroundSystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定由若干 0 和 1 组成的矩阵 matrix，从中选出任意数量的列并翻转其上的 每个 单元格。翻转后，单元格的值从 0 变成 1，或者从 1 变为 0 。
 *
 * 返回经过一些翻转后，行上所有值都相等的最大行数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[[0,1],[1,1]]
 * 输出：1
 * 解释：不进行翻转，有 1 行所有值都相等。
 * 示例 2：
 *
 * 输入：[[0,1],[1,0]]
 * 输出：2
 * 解释：翻转第一列的值之后，这两行都由相等的值组成。
 * 示例 3：
 *
 * 输入：[[0,0,0],[0,0,1],[1,1,0]]
 * 输出：2
 * 解释：翻转前两列的值之后，后两行由相等的值组成。
 *
 * 找类型一样的行数
 */
public class MaxEqualRowsAfterFlips {
    public static int maxEqualRowsAfterFlips(int[][] matrix) {
        int res = 0;
        int cols = matrix[0].length;

        Map<String ,Integer> map = new HashMap<>();

        for (int[] ints : matrix) {
            if (ints[0] == 1) {
                StringBuilder str = new StringBuilder();

                for (int j = 0; j < cols; j++) {
                    str.append(ints[j] ^ 1);
                }

                map.put(str.toString(), map.getOrDefault(str.toString(), 0) + 1);
            } else {
                StringBuilder str = new StringBuilder();

                for (int j = 0; j < cols; j++) {
                    str.append(ints[j]);
                }

                map.put(str.toString(), map.getOrDefault(str.toString(), 0) + 1);

            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()){
            res = Math.max(res,entry.getValue());
        }

        return res;

    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{{0,1},{1,0}};
        maxEqualRowsAfterFlips(nums);
    }
}
