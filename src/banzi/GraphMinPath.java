package banzi;

import java.util.Arrays;

public class GraphMinPath {

    //floyd算法
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] g = new int[n][n];

        for (int i = 0 ;i < n;i++){
            for (int j = 0 ; j < n ; j++){
                g[i][j] = i == j ? 0 : 0x3f3f3f3f;
            }
        }
        for (int[] e : edges) {
            int a = e[0], b = e[1], c = e[2];
            g[a][b] = g[b][a] = Math.min(g[a][b], c);
        }

        floyd(g);

        int ans = -1, cnt = n + 10;
        for (int i = 0; i < n; i++) {
            int cur = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && g[i][j] <= distanceThreshold) cur++;
            }
            if (cur <= cnt) {
                cnt = cur; ans = i;
            }
        }
        return ans;
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

    int[] dijkstra(int[][] g, int x) {
        int n = g.length;
        // 起始先将所有的点标记为「未更新」和「距离为正无穷」
        boolean[] vis = new boolean[n];
        int[] dist = new int[n];
        Arrays.fill(dist, 0x3f3f3f3f);
        // 只有起点最短距离为 0
        dist[x] = 0;
        // 有多少个点就迭代多少次
        for (int k = 0; k < n; k++) {
            // 每次找到「最短距离最小」且「未被更新」的点 t
            int t = -1;
            for (int i = 0; i < n; i++) {
                if (!vis[i] && (t == -1 || dist[i] < dist[t])) t = i;
            }
            // 标记点 t 为已更新
            vis[t] = true;
            // 用点 t 的「最小距离」更新其他点
            for (int i = 0; i < n; i++) dist[i] = Math.min(dist[i], dist[t] + g[t][i]);
        }
        return dist;
    }
}
