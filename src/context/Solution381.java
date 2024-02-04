package context;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution381 {
    public int minimumPushes(String word) {
        int length = word.length();
        int k = length / 8;
        int remain = length % 8;
        return 4*(1+k)*k + remain * (k+1);
    }


    public int minimumPushes2(String word) {
        int[] wordCount = new int[26];
        int nums = word.length();

        for (int i = 0 ; i < nums ; i++ ){
            wordCount[word.charAt(i) - 'a']++;
        }

        Arrays.sort(wordCount);

        int res = 0;

        int count = 0;
        for (int i = 25 ; i >= 0 ; i--){
            res += wordCount[i] * (count/8 +1);
            count++;
        }

        return res;

    }


    public int[] countOfPairs(int n, int x, int y) {
        int[] res = new int[n];
        return res;
    }

    public static void main(String[] args) {
        Solution381 solution381 = new Solution381();
        solution381.minimumPushes("abcde");
    }
}
