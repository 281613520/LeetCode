package context.week8;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 给你一棵有 n 个节点的无向树，节点编号为 0 到 n-1 ，它们中有一些节点有苹果。通过树上的一条边，
 * 需要花费 1 秒钟。你从 节点 0 出发，请你返回最少需要多少秒，可以收集到所有苹果，并回到节点 0 。
 * <p>
 * 无向树的边由 edges 给出，其中 edges[i] = [fromi, toi] ，表示有一条边连接 from 
 * 和 toi 。除此以外，还有一个布尔数组 hasApple ，其中 hasApple[i] = true 代表节点 i 有一个苹果，否则，节点 i 没有苹果。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
 * 输出：8
 * 解释：上图展示了给定的树，其中红色节点表示有苹果。一个能收集到所有苹果的最优方案由绿色箭头表示。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
 * 输出：6
 * 解释：上图展示了给定的树，其中红色节点表示有苹果。一个能收集到所有苹果的最优方案由绿色箭头表示。
 * 示例 3：
 * <p>
 * 输入：n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 10^5
 * edges.length == n-1
 * edges[i].length == 2
 * 0 <= fromi, toi <= n-1
 * fromi < toi
 * hasApple.length == n
 */
public class MinTime {
    public static int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        int res = 0;
        Set<Integer> set = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (int[] nums : edges) {
            if (hasApple.get(nums[1])) {
                if (nums[0] != 0 && !set.contains(nums[0]) && !hasApple.get(nums[0])) {
                    set.add(nums[0]);
                    stack.push(nums[0]);
                }
                res += 2;
            }
        }

        while (!stack.isEmpty()) {
            int i = stack.pop();
            for (int[] nums : edges) {
                if (nums[1] == i) {
                    if (nums[0] != 0 && !set.contains(nums[0]) && !hasApple.get(nums[0])) {
                        set.add(nums[0]);
                        stack.push(nums[0]);
                    }
                    res += 2;
                    break;
                }
            }

        }


        return res;
    }
}
