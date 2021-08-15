package context.week237;

import java.util.*;

public class Solution3 {
    public static int[] getOrder(int[][] tasks) {
        Queue<Task> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.processTime != o2.processTime) {
                return o1.processTime - o2.processTime;
            } else {
                return o1.location - o2.location;
            }
        });

        List<Task> taskList = new ArrayList<>();

        long startTime = Integer.MAX_VALUE;

        for (int i = 0; i < tasks.length; i++) {
            Task task = new Task(tasks[i][0], tasks[i][1], i);
            startTime = Math.min(startTime, task.startTime);
            taskList.add(task);
        }

        taskList.sort(Comparator.comparingInt(o -> o.startTime));

        int[] res = new int[tasks.length];

        int i = 0; // taskList中
        int j = 0; // res中

        long now = startTime;

        while (i < tasks.length) {
            while (i < tasks.length && taskList.get(i).startTime <= now){
                queue.add(taskList.get(i));
                i++;
            }

            // 没有任务了 直接跳到下一个任务的start time
            if (queue.isEmpty()){
                now = taskList.get(i).startTime;
                while (i < tasks.length && taskList.get(i).startTime <= now){
                    queue.add(taskList.get(i));
                    i++;
                }
            }

            Task cur = queue.poll();
            now += cur.processTime;
            res[j] = cur.location;
            j++;
        }

        while (!queue.isEmpty()){
            Task cur = queue.poll();
            res[j] = cur.location;
            j++;
        }
        return res;
    }

    static class Task {
        int startTime;
        int processTime;
        int location;

        public Task(int startTime, int processTime, int location) {
            this.startTime = startTime;
            this.processTime = processTime;
            this.location = location;
        }
    }

    public static void main(String[] args) {
        //{{1,2},{2,4},{3,2},{4,1}}
        //{{100,100},{1000000000,1000000000}}
        getOrder(new int[][]{{100,100},{1000000000,1000000000}});
    }
}
