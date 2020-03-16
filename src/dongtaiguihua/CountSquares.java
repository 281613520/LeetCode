package dongtaiguihua;

/**
 * 给你一个 m * n 的矩阵，矩阵中的元素不是 0 就是 1，请你统计并返回其中完全由 1 组成的 正方形 子矩阵的个数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * 输出：15
 * 解释：
 * 边长为 1 的正方形有 10 个。
 * 边长为 2 的正方形有 4 个。
 * 边长为 3 的正方形有 1 个。
 * 正方形的总数 = 10 + 4 + 1 = 15.
 * 示例 2：
 *
 * 输入：matrix =
 * [
 *   [1,0,1],
 *   [1,1,0],
 *   [1,1,0]
 * ]
 * 输出：7
 * 解释：
 * 边长为 1 的正方形有 6 个。
 * 边长为 2 的正方形有 1 个。
 * 正方形的总数 = 6 + 1 = 7.
 */
public class CountSquares {
    public static int countSquares(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];

        int sum = 0;

        for (int i = 0 ; i < matrix.length ;i++){
            if (matrix[i][0] == 1){
                dp[i][0] = 1;
                sum ++;
            }
        }

        for (int j = 1 ; j < matrix[0].length ;j++){
            if (matrix[0][j] == 1){
                dp[0][j] = 1;
                sum ++;
            }
        }


        for (int i = 1 ; i < matrix.length ; i++){
            for (int j = 1 ; j < matrix[0].length ; j++){
                if (matrix[i][j] == 1){
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j-1],dp[i-1][j]),dp[i-1][j-1]);
                    sum += dp[i][j];
                }
            }
        }

        return sum;
    }
//[[1,0,1],[1,1,0],[1,1,0]]
    public static void main(String[] args) {
        countSquares(new int[][]{{1,0,1},{1,1,0},{1,1,0}});
    }
}
