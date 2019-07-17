package dongtaiguihua;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 */
public class UniquePathsWithObstacles {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] result = new int[row][col];
        for (int i = 0 ; i < row ; i++){
            if (obstacleGrid[i][0] != 1) {
                result[i][0] = 1;
            }else {
                break;
            }
        }

        for (int i = 0 ; i < col ; i++){
            if (obstacleGrid[0][i] != 1) {
                result[0][i] = 1;
            }else {
                break;
            }
        }

        for (int i = 1 ; i < row;i++){
            for (int j = 1 ; j < col ; j++){
                if (obstacleGrid[i][j] == 1)continue;
                result[i][j] = result[i-1][j] + result[i][j-1];
            }
        }

        return result[row-1][col-1];
    }
}
