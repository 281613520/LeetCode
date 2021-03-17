package matrix;


/**
 * 59. 螺旋矩阵 II
 */
public class GenerateMatrix {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int nums = n * n;

        int top = 0;
        int bottom = n - 1;
        int right = n - 1;
        int left = 0;
        int count = 1;

        while (nums >= 1) {
            for (int i = left; i <= right && nums >= 1; i++) {
                res[top][i]=count;
                count++;
                nums--;
            }
            top++;

            for (int i = top; i <= bottom && nums >= 1; i++) {
                res[i][right]=count;
                count++;
                nums--;
            }
            right--;

            for (int i = right; i >= left && nums >= 1; i--) {
                res[bottom][i]=count;
                count++;
                nums--;
            }
            bottom--;

            for (int i = bottom; i >= top && nums >= 1; i--) {
                res[i][left]=count;
                count++;
                nums--;
            }
            left++;

        }

        return res;
    }
}
