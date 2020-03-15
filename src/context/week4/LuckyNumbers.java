package context.week4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
 *
 * 幸运数是指矩阵中满足同时下列两个条件的元素：
 *
 * 在同一行的所有元素中最小
 * 在同一列的所有元素中最大
 *  
 *
 * 示例 1：
 *
 * 输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
 * 输出：[15]
 * 解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 * 示例 2：
 *
 * 输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
 * 输出：[12]
 * 解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 * 示例 3：
 *
 * 输入：matrix = [[7,8],[1,2]]
 * 输出：[7]

 */
public class LuckyNumbers {
    public static List<Integer> luckyNumbers (int[][] matrix) {
            List<Integer> res = new ArrayList<>();
            boolean[] flag = new boolean[matrix[0].length];

            for (int i = 0 ; i < matrix.length ; i++){
                boolean f = false;
                int location = 0;
                int min = matrix[i][0];
                for (int j = 0 ; j < matrix[0].length ; j++){
                    if (matrix[i][j] < min){
                        min = matrix[i][j];
                        location = j;
                    }
                }
                int max = matrix[i][location];
                for (int k = 0 ; k < matrix.length ; k++){
                    if (flag[k]) continue;
                    if (matrix[k][location] > max){
                        f = true;
                        break;
                    }
                }

                if (!f){
                    res.add(matrix[i][location]);
                    flag[i] = true;
                }
            }


            return res;
    }

    public static void main(String[] args) {
        System.out.println(luckyNumbers(new int[][]{{1,10,4,2},{9,3,8,7},{15,16,17,12}}));
    }
}
