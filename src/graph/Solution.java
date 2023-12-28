package graph;


import java.util.*;


public class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        // 0 没访问过  1 环上 2 安全
        int[] visited = new int[graph.length];

        List<Integer> ans = new ArrayList<>();

        for (int i = 0 ; i < graph.length ; i++){
            if (dfs(graph,visited,i)){
                ans.add(i);
            }
        }

        return ans;

    }

    private boolean dfs(int[][] graph, int[] visited, int i) {
        if (visited[i] > 0){
            return visited[i] == 2;
        }
        visited[i] = 1;
        for (int j : graph[i]) {
            if (!dfs(graph,visited,j)){
                return false;
            }
        }
        visited[i] = 2;
        return true;
    }


    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        List<Integer> room0 = rooms.get(0);
        visited[0] = true;
        Queue<Integer> queue = new ArrayDeque<>(room0);
        while (!queue.isEmpty()){
            int cur = queue.poll();
            visited[cur] = true;
            for (Integer i : rooms.get(cur)) {
                if (!visited[i]){
                    queue.add(i);
                }
            }

        }

        for (boolean b : visited) {
            if (!b){
                return false;
            }
        }

        return true;
    }
}
