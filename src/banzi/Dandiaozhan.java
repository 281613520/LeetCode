package banzi;

import java.sql.ClientInfoStatus;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Dandiaozhan {

    public int[] finalPrices(int[] prices) {
        // 存储递减的下表
        Deque<Integer> queue = new ArrayDeque<>();
        int[] res = new int[prices.length];
        int n = prices.length;

       /* for (int i = n - 1; i >= 0; i--) {
            while (!queue.isEmpty() && prices[queue.peek()] > prices[i]) {
                queue.poll();
            }
            int tmp_res = queue.isEmpty() ? prices[i] : prices[i] - prices[queue.peek()];
            res[i] = tmp_res;
            queue.push(i);
        }*/

        for (int i = 0 ; i < n ; i++){
            while (!queue.isEmpty() && prices[queue.peek()] >= prices[i]){
                int index = queue.poll();
                res[index] -= prices[i];
            }
            queue.push(i);
            res[i] = prices[i];
        }

        return res;
    }

    public int[] nextLargerNodes(ListNode head) {
        List<Integer> nodes = new ArrayList<>();
        ListNode tmp = head;
        while (tmp != null){
            nodes.add(tmp.val);
            tmp = tmp.next;
        }

        int[] nums = new int[nodes.size()];

        for (int i = 0; i < nodes.size(); i++) {
           nums[i] = nodes.get(i);
        }

        int[] res=  new int[nodes.size()];
        Deque<Integer> queue = new ArrayDeque<>();

        for (int i= 0 ; i < nodes.size() ; i++){
            while (!queue.isEmpty() && nums[i] > nums[queue.peek()]){
                int index = queue.poll();
                res[index] = nums[i];
            }

            queue.push(i);
            res[i] = 0;
        }

        return res;
    }
}
