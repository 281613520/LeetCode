package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Merge {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1 || intervals.length == 0) return intervals;

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        List<int[]> res = new ArrayList<>();

        int i = 0;
        while (i < intervals.length){
            int[] cur = new int[2];
            cur[0] = intervals[i][0];
            cur[1] = intervals[i][1];
            int j = i + 1;
            for ( ; j < intervals.length ; j++){
                if (cur[1] >= intervals[j][0] ){
                    cur[1] = Math.max(cur[1],intervals[j][1]);
                }else {
                    break;
                }
            }

            res.add(cur);

            i = j;
        }

        int[][] res2 = new int[res.size()][2];

        for (int a = 0 ; a < res.size() ; a++){
            res2[a] = res.get(a);
        }

        return res2;
    }
}
