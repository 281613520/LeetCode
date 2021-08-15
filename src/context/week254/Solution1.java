package context.week254;

public class Solution1 {
    public int numOfStrings(String[] patterns, String word) {
        int res = 0;

        for(String cur: patterns){
            if (word.contains(cur)){
                res++;
            }
        }

        return res;
    }
}
