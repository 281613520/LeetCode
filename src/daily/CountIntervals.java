package daily;

import context.week5.UndergroundSystem;

import java.util.Map;
import java.util.TreeMap;

public class CountIntervals {
    TreeMap<Integer,Integer> intervals;
    int ans = 0;

    public CountIntervals() {
        intervals = new TreeMap<>();
        ans = 0;
    }

    public void add(int left, int right) {
        // 找到上一个left 小于当前right的

        Map.Entry<Integer, Integer> interval = intervals.floorEntry(right);

        while (interval != null && interval.getValue() >= left){

            int l = interval.getKey();
            int r = interval.getValue();
            ans -= r-l +1;
            // 看看有没有机会合并
            left = Math.min(l,left);
            right = Math.max(r,right);

            intervals.remove(l);

            interval = intervals.floorEntry(right);
        }

        ans+= right - left +1;

        intervals.put(left,right);
    }

    public int count() {
        return ans;
    }
}
