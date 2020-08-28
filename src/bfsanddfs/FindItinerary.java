package bfsanddfs;


import java.util.*;

/**
 * 332. 重新安排行程
 *
 * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。
 * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
 *
 * 提示：
 *
 * 如果存在多种有效的行程，请你按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
 * 所有的机场都用三个大写字母表示（机场代码）。
 * 假定所有机票至少存在一种合理的行程。
 * 所有的机票必须都用一次 且 只能用一次。
 *
 * 示例 1：
 *
 * 输入：[["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * 输出：["JFK", "MUC", "LHR", "SFO", "SJC"]
 * 示例 2：
 *
 * 输入：[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 解释：另一种有效的行程是["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
 *
 * 这一题还是比较有意思的
 *
 * 我们化简本题题意：给定一个 n 个点 m 条边的图，要求从指定的顶点出发，经过所有的边恰好一次（可以理解为给定起点的「一笔画」问题），使得路径的字典序最小。
 *
 * 这种「一笔画」问题与欧拉图或者半欧拉图有着紧密的联系，下面给出定义：
 *
 * 1.通过图中所有边恰好一次且行遍所有顶点的通路称为欧拉通路。（不能回到原点）
 * 2.通过图中所有边恰好一次且行遍所有顶点的回路称为欧拉回路。（能回到原点）
 * 3.具有欧拉回路的无向图称为欧拉图。
 * 4.具有欧拉通路但不具有欧拉回路的无向图称为半欧拉图。
 * 这道题目已经指明肯定有回路，如果没有指明的话，还需要自己去判断一下
 * 判断依据如下：
 * 如果没有保证至少存在一种合理的路径，我们需要判别这张图是否是欧拉图或者半欧拉图，具体地：
 *
 * 对于无向图 G，G 是欧拉图当且仅当 G 是连通的且没有奇度顶点。
 *
 * 对于无向图 G，G 是半欧拉图当且仅当 G 是连通的且 G 中恰有 2 个奇度顶点。
 *
 * 对于有向图 G，G 是欧拉图当且仅当 G 的所有顶点属于同一个强连通分量且每个顶点的入度和出度相同。
 *
 * 对于有向图 G，G 是半欧拉图当且仅当 G 的所有顶点属于同一个强连通分量且
 * 恰有一个顶点的出度与入度差为 1；恰有一个顶点的入度与出度差为 1；所有其他顶点的入度和出度相同。
 *
 * 寻找欧拉通路的算法
 *
 * Hierholzer 算法用于在连通图中寻找欧拉路径，其流程如下：
 *
 * 从起点出发，进行深度优先搜索。
 *
 * 每次沿着某条边从某个顶点移动到另外一个顶点的时候，都需要删除这条边。
 *
 * 如果没有可移动的路径，则将所在节点加入到栈中，并返回。
 */
public class FindItinerary {
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new ArrayList<>();
        String start = "JFK";

        Map<String,List<String>> map = new HashMap<>();

        for (List<String> ticket : tickets) {
            map.computeIfAbsent(ticket.get(0),k->new ArrayList<>()).add(ticket.get(1));
        }

        map.forEach((k,v) -> Collections.sort(v));

        dfs(start,map,res);

        return res;
    }

    private void dfs(String start, Map<String, List<String>> map, List<String> res) {
        List<String> edge = map.get(start);
        while (edge != null && edge.size() > 0) {
            String p = edge.remove(0);
            dfs(p,map, res);
        }
        res.add(0, start);
    }
}
