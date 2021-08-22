package context.week255;

import context.week5.UndergroundSystem;

import java.util.HashSet;
import java.util.Set;

public class Solution2 {
    public static String findDifferentBinaryString(String[] nums) {
        int len = nums[0].length();

        Set<Integer> set = new HashSet<>();

        for (String cur: nums){
            set.add(Integer.parseInt(cur,2));
        }

        int max = 1;
        int tmp = len;
        while (tmp >0){
            max = max * 2;
            tmp--;
        }

        StringBuilder str = new StringBuilder();
        for (int i = 0 ;  i < max ;i++){
            if (!set.contains(i)){
                str.append(Integer.toBinaryString(i));
                break;
            }
        }
        String res = str.toString();
        if (res.length() < len){
            int num = len - res.length();
            while (num >0){
                res = "0"+ res;
                num--;
            }
            return res;
        }else {
            return res;
        }
    }

    public static void main(String[] args) {
        findDifferentBinaryString(new String[]{"01","10"});
    }
}
