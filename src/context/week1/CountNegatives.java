package context.week1;


/**
 * 给你一个 m * n 的矩阵 grid，矩阵中的元素无论是按行还是按列，都以非递增顺序排列。 
 *
 * 请你统计并返回 grid 中 负数 的数目。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
 * 输出：8
 * 解释：矩阵中共有 8 个负数。
 * 示例 2：
 *
 * 输入：grid = [[3,2],[1,0]]
 * 输出：0
 * 示例 3：
 *
 * 输入：grid = [[1,-1],[-1,-1]]
 * 输出：3
 * 示例 4：
 *
 * 输入：grid = [[-1]]
 * 输出：1

 */
public class CountNegatives {
    public int countNegatives(int[][] grid) {
        int result = 0;
        for (int i = grid.length - 1 ; i >= 0 ; i--){
            for (int j = grid[0].length - 1 ; j >= 0 ; j--){
                if (grid[i][j]>=0){
                    break;
                }else {
                    result++;
                }
            }
        }
        return result;
    }
}
