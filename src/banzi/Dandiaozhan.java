package banzi;

import java.sql.ClientInfoStatus;
import java.util.*;

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


    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        int res = 0;

        // 找
        Deque<Integer> queue = new ArrayDeque<>();
        queue.push(0);

        for (int i = 1; i < n ; i++){
            if (nums[i] < nums[queue.peek()]){
                queue.push(i);
            }
        }

        for (int j = n-1 ; j>=0 ; j--){
            while (!queue.isEmpty() && nums[queue.peek()] <= nums[j]) {
                int pos = queue.pop();
                res = Math.max(res,j-pos);
            }
        }

        return res;
    }


    public int largestRectangleArea(int[] heights) {
        //
        int n = heights.length;
        int ans = 0;

        int[] l = new int[n];
        int[] r = new int[n];

        Arrays.fill(l, -1); Arrays.fill(r, n);

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0 ; i < n ; i++ ){
            while (!deque.isEmpty() && heights[deque.peek()] > heights[i]){
                r[deque.pop()] = i;
            }
            deque.push(i);
        }

        deque.clear();

        for (int i = n-1 ; i >= 0 ; i-- ){
            while (!deque.isEmpty() && heights[deque.peek()] > heights[i]){
                l[deque.pop()] = i;
            }
            deque.push(i);
        }

        for (int i = 0 ; i < n ; i ++){
            int t = heights[i];
            int a = l[i];
            int b = r[i];
            ans = Math.max(ans,t*(b-a-1));
        }
        return ans;
    }


    public int maximumScore(int[] heights, int k) {
        int n = heights.length;
        int ans = 0;

        int[] l = new int[n];
        int[] r = new int[n];

        Arrays.fill(l, -1); Arrays.fill(r, n);

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0 ; i < n ; i++ ){
            while (!deque.isEmpty() && heights[deque.peek()] > heights[i]){
                r[deque.pop()] = i;
            }
            deque.push(i);
        }

        deque.clear();

        for (int i = n-1 ; i >= 0 ; i-- ){
            while (!deque.isEmpty() && heights[deque.peek()] > heights[i]){
                l[deque.pop()] = i;
            }
            deque.push(i);
        }

        for (int i = 0 ; i < n ; i ++){
            int t = heights[i];
            int a = l[i];
            int b = r[i];
            if (a +1 <= k && k<=b-1) {
                ans = Math.max(ans, t * (b - a - 1));
            }
        }
        return ans;
    }

}
