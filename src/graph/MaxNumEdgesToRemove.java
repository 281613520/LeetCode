package graph;

/**
 * 1579. 保证图可完全遍历
 * Alice 和 Bob 共有一个无向图，其中包含 n 个节点和 3  种类型的边：
 * <p>
 * 类型 1：只能由 Alice 遍历。
 * 类型 2：只能由 Bob 遍历。
 * 类型 3：Alice 和 Bob 都可以遍历。
 * 给你一个数组 edges ，其中 edges[i] = [typei, ui, vi] 表示节点 ui 和 vi 之间存在类型为 typei 的双向边。
 * 请你在保证图仍能够被 Alice和 Bob 完全遍历的前提下，找出可以删除的最大边数。
 * 如果从任何节点开始，Alice 和 Bob 都可以到达所有其他节点，则认为图是可以完全遍历的。
 * <p>
 * 返回可以删除的最大边数，如果 Alice 和 Bob 无法完全遍历图，则返回 -1 。
 */
public class MaxNumEdgesToRemove {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int res = 0;
        int[] parent_a = new int[n];
        int cnt_a = n;
        int[] parent_b = new int[n];
        int cnt_b = n;

        for (int i = 0; i < n; i++) {
            parent_a[i] = i;
            parent_b[i] = i;
        }

        for (int[] cur : edges) {
            int type = cur[0];
            int i = cur[1] - 1;
            int j = cur[2] - 1;

            int i_a = find(i, parent_a);
            int j_a = find(j, parent_a);

            if (type == 3) {
                if (i_a == j_a) {
                    res++;
                    continue;
                }
            }

            int i_b = find(i, parent_b);
            int j_b = find(j, parent_b);

            if (type == 3) {
                union(parent_b, i_b, j_b);
                union(parent_a, i_a, j_a);
                cnt_b--;
                cnt_a--;
            }
        }


        for (int[] cur : edges) {
            int type = cur[0];
            int i = cur[1] - 1;
            int j = cur[2] - 1;

            int i_a = find(i, parent_a);
            int i_b = find(i, parent_b);
            int j_a = find(j, parent_a);
            int j_b = find(j, parent_b);

            if (type == 1) {
                if (i_a != j_a) {
                    union(parent_a, i_a, j_a);
                    cnt_a--;
                } else {
                    res++;
                }
            } else if (type == 2) {
                if (i_b != j_b) {
                    union(parent_b, i_b, j_b);
                    cnt_b--;
                } else {
                    res++;
                }
            }
        }

        if (cnt_a != 1 || cnt_b != 1) {
            return -1;
        }

        return res;
    }

    private int find(int index, int[] parents) {
        return parents[index] != index ? parents[index] = find(parents[index], parents) : parents[index];
    }

    private void union(int[] parent, int index1, int index2) {
        parent[find(index1, parent)] = find(index2, parent);
    }
}
