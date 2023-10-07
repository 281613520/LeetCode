package daily;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class StockSpanner {

    int nums = 0;

    Deque<int[]> deque;
    public StockSpanner() {
        deque = new LinkedList<>();
        deque.push(new int[]{-1, Integer.MAX_VALUE});
        nums = -1;
    }

    public int next(int price) {
        nums++;
        while (price >= deque.peek()[1]){
            deque.pop();
        }
        int res = nums - deque.peek()[0];
        deque.push(new int[]{nums,price});
        return res;
    }
}
