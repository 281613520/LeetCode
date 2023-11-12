package daily;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class RangeModule {

    private List<int[]> intervals;

    public RangeModule() {
        intervals = new ArrayList<>();
    }

    public void addRange(int left, int right) {
        for (int i = intervals.size() - 1; i >= 0; i--) {
            int tleft = intervals.get(i)[0];
            int tright = intervals.get(i)[1];
            if (left <= tright && tleft <= right) {
                if (left <= tleft) {
                    if (right < tright) {
                        right = tright;
                    }
                } else {
                    left = tleft;
                    if (right < tright) {
                        right = tright;
                    }
                }
                intervals.remove(i);
            }
        }
        intervals.add(new int[]{left, right});
    }

    public boolean queryRange(int left, int right) {
        for (int[] interval : intervals) {
            if (left >= interval[0] && right <= interval[1]) {
                return true;
            }
        }

        return false;
    }

    public void removeRange(int left, int right) {
        for (int i = intervals.size() - 1; i >= 0; i--) {
            int tleft = intervals.get(i)[0];
            int tright = intervals.get(i)[1];

            if (left <= tright && tleft <= right) {

                intervals.remove(i);

                if (left <= tleft) {
                    if (right < tright) {
                        intervals.add(new int[]{right, tright});
                    }
                } else {
                    if (right < tright) {
                        intervals.add(new int[]{tleft, left});
                        intervals.add(new int[]{right, tright});
                        break;
                    } else {
                        intervals.add(new int[]{tleft, left});
                    }
                }
            }
        }
    }
}
