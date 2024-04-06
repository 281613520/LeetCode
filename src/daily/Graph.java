package daily;

import java.util.Arrays;

public class Graph {
    private int[][] dists;

    public Graph(int n, int[][] edges) {
        dists = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dists[i], Integer.MAX_VALUE);
            dists[i][i] = 0;
        }

        for (int[] edge : edges) {
            dists[edge[0]][edge[1]] = edge[2];
        }
        floyd(dists);
    }

    public void addEdge(int[] edge) {
        int x = edge[0], y = edge[1], cost = edge[2];
        if (cost >= dists[x][y]) {
            return;
        }

        dists[x][y] = cost;
        floyd(dists);
    }

    public int shortestPath(int node1, int node2) {
        return dists[node1][node2] == Integer.MAX_VALUE? -1 : dists[node1][node2];
    }

    void floyd(int[][] g) {
        int n = g.length;
        // floyd 基本流程为三层循环: [枚举中转点 - 枚举起点 - 枚举终点] => 松弛操作
        for (int p = 0; p < n; p++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    g[i][j] = Math.min(g[i][j], g[i][p] + g[p][j]);
                }
            }
        }
    }
}
