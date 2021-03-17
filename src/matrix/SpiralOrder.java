package matrix;

import java.util.ArrayList;
import java.util.List;


/**
 * 54. 螺旋矩阵
 */
public class SpiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        int m = matrix.length;
        int n = matrix[0].length;
        int nums = m * n;

        int top = 0;
        int bottom = matrix.length - 1;
        int right = matrix[0].length - 1;
        int left = 0;

        while (nums >= 1) {
            for (int i = left; i <= right && nums >= 1; i++) {
                res.add(matrix[top][i]);
                nums--;
            }
            top++;

            for (int i = top; i <= bottom && nums >= 1; i++) {
                res.add(matrix[i][right]);
                nums--;
            }
            right--;

            for (int i = right; i >= left && nums >= 1; i--) {
                res.add(matrix[bottom][i]);
                nums--;
            }
            bottom--;

            for (int i = bottom; i >= top && nums >= 1; i--) {
                res.add(matrix[i][left]);
                nums--;
            }
            left++;

        }

        return res;
    }
}
