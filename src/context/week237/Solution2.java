package context.week237;

import java.util.Arrays;

public class Solution2 {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);

        int res = 0;

        int i = 0;

        while (i <= costs.length - 1) {
            if (coins < costs[i]) {
                break;
            }
            res++;
            coins -= costs[i];
            i++;
        }

        return res;

    }
}
