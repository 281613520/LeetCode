package context.week5;

import java.util.HashMap;
import java.util.Map;

/**
 * 在整数数组中，如果一个整数的出现频次和它的数值大小相等，我们就称这个整数为「幸运数」。
 *
 * 给你一个整数数组 arr，请你从中找出并返回一个幸运数。
 *
 * 如果数组中存在多个幸运数，只需返回 最大 的那个。
 * 如果数组中不含幸运数，则返回 -1 。
 */
public class FindLucky {
    public int findLucky(int[] arr) {
        int res = -1;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i : arr){
            if (map.containsKey(i)){
                map.put(i,map.get(i) + 1);
            }else {
                map.put(i,1);
            }
        }

       for (Map.Entry<Integer , Integer> cur:map.entrySet()){
           if (cur.getKey().equals(cur.getValue()) && cur.getKey() > res){
               res = cur.getKey();
           }
       }
        return res;
    }
}
