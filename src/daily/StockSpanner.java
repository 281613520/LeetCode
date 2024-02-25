package daily;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class StockSpanner {

    int nums = 0;

    Deque<int[]> deque;
    public StockSpanner() {
        deque = new ArrayDeque<>();
        deque.push(new int[]{-1, Integer.MAX_VALUE});
        nums = -1;
    }

    public int next(int price) {
        //   存单调递减的
        nums++;

        while (!deque.isEmpty() && deque.peek()[1] < price){
            deque.pop();
        }

        int[] cur = deque.peek();
        int res = nums - cur[0];
        deque.push(new int[]{nums,price});
        return res;
    }
}
