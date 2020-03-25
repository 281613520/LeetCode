package greed;

/**
 * 如上图所示，电影院的观影厅中有 n 行座位，行编号从 1 到 n ，且每一行内总共有 10 个座位，列编号从 1 到 10 。
 * <p>
 * 给你数组 reservedSeats ，包含所有已经被预约了的座位。比如说，researvedSeats[i]=[3,8] ，它表示第 3 行第 8 个座位被预约了。
 * <p>
 * 请你返回 最多能安排多少个 4 人家庭 。4 人家庭要占据 同一行内连续 的 4 个座位。隔着过道的座位（比方说 [3,3] 和 [3,4]）不是连续的座位
 * ，但是如果你可以将 4 人家庭拆成过道两边各坐 2 人，这样子是允许的。
 * <p>
 * 2345 4567 6789
 * 1234 3456 5678
 */
public class MaxNumberOfFamilies {
    //todo youhua
    public static int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        //Arrays.sort(reservedSeats, Comparator.comparingInt(o -> o[0]));
        int[][] seats = new int[n][10];

        for (int[] nums : reservedSeats) {
            seats[nums[0] - 1][nums[1] - 1] = 1;
        }

        int res = 0;

        for (int i = 0; i < n; i++) {
            int[] cur = seats[i];
            if (cur[1] != 1 && cur[2] != 1 && cur[3] != 1 && cur[4] != 1 && cur[5] != 1 && cur[6] != 1 && cur[7] != 1 && cur[8] != 1) {
                res += 2;
            } else if (cur[3] != 1 && cur[4] != 1 && cur[5] != 1 && cur[6] != 1) {
                res++;
            } else {
                if (cur[1] != 1 && cur[2] != 1 && cur[3] != 1 && cur[4] != 1) res++;
                if (cur[5] != 1 && cur[6] != 1 && cur[7] != 1 && cur[8] != 1) res++;
            }
        }

        return res;

    }

    public static void main(String[] args) {
        maxNumberOfFamilies(3, new int[][]{{1, 2}, {1, 3}, {1, 8}, {2, 6}, {3, 1}, {3, 10}});
    }
}
