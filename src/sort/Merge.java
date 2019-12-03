package sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 1,2,8,15
 * 3,6,10,18
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: [[1,4],[4,5]]
 * <p>
 * 1,4
 * 4,5
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
public class Merge {
    public List<Interval> merge(List<Interval> intervals) {
        int[] left = new int[intervals.size()];
        int[] right = new int[intervals.size()];
        for (int i = 0; i < intervals.size(); i++) {
            left[i] = intervals.get(i).start;
            right[i] = intervals.get(i).end;
        }
        List<Interval> result = new ArrayList<>();
        
        int start = 1;
        int j = 0;
        while (start < left.length){
            int cur = start;
            while (left[cur] <= right[j]){
                j++;
                cur++;
            }
            result.add(new Interval(left[start - 1],right[j]));
            start = j + 2;
            if (start >= left.length){
                result.add(new Interval(left[start - 1],right[j]));
            }
            j++;
        }
        return result;
    }
}
