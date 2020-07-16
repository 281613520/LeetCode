package dfs;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 785. 判断二分图
 *
 * 给定一个无向图graph，当这个图为二分图时返回true。
 *
 * 如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。
 *
 * graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边：
 *  graph[i] 中不存在i，并且graph[i]中没有重复的值。
 *
 *
 * 示例 1:
 * 输入: [[1,3], [0,2], [1,3], [0,2]]
 * 输出: true
 * 解释:
 * 无向图如下:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * 我们可以将节点分成两组: {0, 2} 和 {1, 3}。
 *
 * 示例 2:
 * 输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * 输出: false
 * 解释:
 * 无向图如下:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * 我们不能将节点分割成两个独立的子集。
 *
 * 染色法
 * 找出相连的边中，两个端点必定属于不同的集合，只要所有的端点满足这个结果即可
 * 即为标记节点为ab，不断的传染下去
 */
public class IsBipartite {
    private static final int UNCOLORED = 0;
    private static final int RED = 1;
    private static final int GREEN = 2;
    boolean result = true;

    public boolean isBipartite(int[][] graph) {
        int m = graph.length;
        // node 为  0,1，...m-1
        int[] color  = new int[m];
        Arrays.fill(color,UNCOLORED);

        for (int i = 0 ; i < m && result; i ++){
            if (color[i] == UNCOLORED){
               dfs(color,i,RED,graph);
            }
        }

        return result;
    }

    private void dfs(int[] color,int node,int c, int[][] graph) {
        color[node] = c;
        int nextColor = c == RED? GREEN : RED;
        for (int curNode : graph[node]){
            if (color[curNode] == c){
                result = false;
                return;
            }else if (color[curNode] == UNCOLORED){
                dfs(color,curNode,nextColor,graph);
                if (!result){
                    return;
                }
            }
        }

    }
}
