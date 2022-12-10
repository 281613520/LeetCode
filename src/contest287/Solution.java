package contest287;

import context.week5.UndergroundSystem;

import java.lang.reflect.Array;
import java.util.*;

public class Solution {

    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer,Integer> map = new HashMap<>();
        Set<Integer> winner = new HashSet<>();
        for (int[] cur : matches){
            int win = cur[0];
            int loser = cur[1];
            winner.add(win);
            map.put(loser,map.getOrDefault(loser,0) + 1);
        }

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        map.forEach((k,v) -> {
            if (v == 1){
                list2.add(k);
            }
        });

        winner.forEach( v -> {
            if (!map.containsKey(v)){
                list1.add(v);
            }
        });


        List<List<Integer>> ans = new ArrayList<>();


        list1.sort(Comparator.comparingInt(o -> o));
        list2.sort(Comparator.comparingInt(o -> o));

        ans.add(list1);
        ans.add(list2);

        return ans;
    }


    public int maximumCandies(int[] candies, long k) {
        return 1;
    }
}
