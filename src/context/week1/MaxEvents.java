package context.week1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 endDayi 。
 * <p>
 * 你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。注意，一天只能参加一个会议。
 * <p>
 * 请你返回你可以参加的 最大 会议数目。
 * <p>
 * 示例 1：
 * <p>
 * 输入：events = [[1,2],[2,3],[3,4]]
 * 输出：3
 * 解释：你可以参加所有的三个会议。
 * 安排会议的一种方案如上图。
 * 第 1 天参加第一个会议。
 * 第 2 天参加第二个会议。
 * 第 3 天参加第三个会议。
 * 示例 2：
 * <p>
 * 输入：events= [[1,2],[2,3],[3,4],[1,2]]
 * 输出：4
 * 示例 3：
 * <p>
 * 输入：events = [[1,4],[4,4],[2,2],[3,4],[1,1]]
 * 输出：4
 * 示例 4：
 * <p>
 * 输入：events = [[1,100000]]
 * 输出：1
 * 示例 5：
 * <p>
 * 输入：events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
 * 输出：7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-number-of-events-that-can-be-attended
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxEvents {
    public int maxEvents(int[][] events) {
        boolean[] vis = new boolean[100005];

        Arrays.sort(events, Comparator.comparingInt(o -> o[1]));

        int res=0;
        for(int[] it:events){
            for(int i=it[0];i<=it[1];i++){
                if(!vis[i]){
                    vis[i]=true;
                    res++;
                    break;
                }
            }
        }
        return res;
    }
}
