package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 8 间牢房排成一排，每间牢房不是有人住就是空着。
 * <p>
 * 每天，无论牢房是被占用或空置，都会根据以下规则进行更改：
 * <p>
 * 如果一间牢房的两个相邻的房间都被占用或都是空的，那么该牢房就会被占用。
 * 否则，它就会被空置。
 * （请注意，由于监狱中的牢房排成一行，所以行中的第一个和最后一个房间无法有两个相邻的房间。）
 * <p>
 * 我们用以下方式描述监狱的当前状态：如果第 i 间牢房被占用，则 cell[i]==1，否则 cell[i]==0。
 * <p>
 * 根据监狱的初始状态，在 N 天后返回监狱的状况（和上述 N 种变化）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：cells = [0,1,0,1,1,0,0,1], N = 7
 * 输出：[0,0,1,1,0,0,0,0]
 * 解释：
 * 下表概述了监狱每天的状况：
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 * <p>
 * 示例 2：
 * <p>
 * 输入：cells = [1,0,0,1,0,0,1,0], N = 1000000000
 * 输出：[0,0,1,1,1,1,1,0]
 */
public class PrisonAfterNDays {
    public static int[] prisonAfterNDays(int[] cells, int N) {
        if (N == 0) {
            return cells;
        }
        int count = N;
        boolean flag = false;
        List<List<Integer>> ceshi = new ArrayList<>();
        while (N > 0) {
            int[] result = new int[cells.length];
            result[0] = 0;
            result[cells.length - 1] = 0;
            List<Integer> tp = new ArrayList<>();
            for (int i = 1; i < cells.length - 1; i++) {
                if ((cells[i - 1] == 1 && cells[i + 1] == 1) || (cells[i - 1] == 0 && cells[i + 1] == 0)) {
                    result[i] = 1;
                } else {
                    result[i] = 0;
                }
            }

            for (int i = 0; i < cells.length; i++) {
                cells[i] = result[i];
                tp.add(result[i]);
            }

            if (ceshi.contains(tp)) {
                flag = true;
                break;
            } else {
                ceshi.add(tp);
            }
            N--;
        }

        if (flag) {
            int a = N - 1;
            int b = a % ceshi.size();
            List<Integer> r = ceshi.get(b);
            int[] s = new int[r.size()];
            for (int i = 0 ; i < r.size() ; i++){
                s[i] = r.get(i);
            }
            return s;
        } else {
            return cells;
        }

    }

    public static void main(String[] args) {
        int[] cells = new int[]{0, 1, 0, 1, 1, 0, 0, 1};
        prisonAfterNDays(cells, 100);
    }
}
