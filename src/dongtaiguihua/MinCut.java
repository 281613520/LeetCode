package dongtaiguihua;

import java.util.Arrays;

/**
 *
 * 132. 分割回文串 II
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
 *
 * 返回符合要求的 最少分割次数 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 * 示例 2：
 *
 * 输入：s = "a"
 * 输出：0
 * 示例 3：
 *
 * 输入：s = "ab"
 * 输出：1

 */
public class MinCut {
    public int minCut(String s) {
        int[] dp = new int[s.length()];
        Arrays.fill(dp,Integer.MAX_VALUE);

        boolean[][] palindrome = new boolean[s.length()][s.length()];
        for (boolean[] booleans : palindrome) {
            Arrays.fill(booleans, true);
        }

        for (int i = 1 ; i < s.length() ; i++){
            for (int j = i - 1 ; j >= 0 ; j--){
                palindrome[j][i] = s.charAt(i) == s.charAt(j) && palindrome[j+1][i-1];
            }
        }

        for (int i = 0 ; i < s.length() ; i++){
            if (palindrome[0][i]){
                dp[i] = 0;
            }else {
                for (int j = 0 ;  j < i ; j++){
                    if (palindrome[j + 1][i]){
                        dp[i] = Math.min(dp[j]+1,dp[i]);
                    }
                }
            }
        }

        return dp[s.length() - 1];
    }
}
