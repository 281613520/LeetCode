package dfs;

public class NumIslands {
    public int numIslands(char[][] grid) {
        if (grid == null) return 0;

        int res = 0;
        // 1.记录每个点是否被扫过
        // 2.满足条件就退出

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    help(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void help(char[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] != '1') {
            return;
        }
        if (grid[x][y] == '1') {
            grid[x][y] = '2';
        }

        help(grid, x, y + 1);
        help(grid, x, y - 1);
        help(grid, x - 1, y);
        help(grid, x + 1, y);
    }
}
