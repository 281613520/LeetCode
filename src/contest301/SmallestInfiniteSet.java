package contest301;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SmallestInfiniteSet {
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    boolean[] flags = new boolean[1001];

    public SmallestInfiniteSet() {
        for (int i = 1 ; i <= 1000 ; i++){
            queue.add(i);
        }
        Arrays.fill(flags,true);
    }

    public int popSmallest() {
        int res = queue.poll();
        flags[res] = false;
        return res;
    }

    public void addBack(int num) {
        if (flags[num]) return;

        flags[num] = true;
        queue.add(num);
    }
}
