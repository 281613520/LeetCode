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


    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer,List<Integer>> matrix = new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            matrix.putIfAbsent(manager[i], new ArrayList<>());
            matrix.get(manager[i]).add(i);
        }

        return dfs1376(headID,informTime,matrix);
    }

    private int dfs1376(int id, int[] informTime, Map<Integer,List<Integer>> matrix) {
        List<Integer> next = matrix.getOrDefault(id,new ArrayList<>());

        int res = 0;

        for (Integer i : next) {
            res = Math.max(res,dfs1376(i,informTime,matrix));
        }

        return informTime[id] + res;
    }

    public int numOfMinutes2(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer,Integer> cahce = new HashMap<>();
        int res = 0;
        for (int i = 0 ; i < n ;i++){
            res = Math.max(res,dfs13762(headID,manager,informTime,cahce,i));
        }

        return res;
    }

    private int dfs13762(int headID, int[] manager, int[] informTime, Map<Integer, Integer> cahce, int cur) {
        if (cur == headID){
            return 0;
        }

        if (!cahce.containsKey(cur)){
            int res = dfs13762(headID,manager,informTime,cahce,manager[cur]) + informTime[manager[cur]];
            cahce.put(cur,res);
        }

        return cahce.get(cur);
    }

    public boolean canReach(int[] arr, int start) {
        if(arr[start] == 0) return true;
           boolean[] visited = new boolean[arr.length];

           Deque<Integer> queue = new ArrayDeque<>();
           queue.add(start);
           visited[start] = true;

           while (!queue.isEmpty()){
               Integer cur = queue.poll();
               Integer front = cur - arr[cur];
               Integer back  = cur + arr[cur];

               if (front >=0 && !visited[front]){
                   if (arr[front] == 0){
                       return true;
                   }

                   queue.add(front);
                   visited[front]  = true;
               }

               if (back < arr.length && !visited[back]){
                   if (arr[back] == 0){
                       return true;
                   }

                   queue.add(back);
                   visited[back]  = true;
               }
           }

           return false;
    }

}
