package dongtaiguihua;


/**
 * 516. 最长回文子序列
 *
 * 给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。
 *
 * 示例 1:
 * 输入:
 *
 * "bbbab"
 * 输出:
 *
 * 4
 * 一个可能的最长回文子序列为 "bbbb"。
 *
 * 示例 2:
 * 输入:
 *
 * "cbbd"
 * 输出:
 *
 * 2
 *
 */
public class LongestPalindromeSubseq {
    public static int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];

        for (int j = 0 ; j < s.length() ; j++){
            for (int i = j ; i >= 0 ; i--){
                if (i == j ){
                    dp[i][j] = 1;
                    continue;
                }
                if (j-i ==1){
                    if (s.charAt(i) == s.charAt(j)){
                        dp[i][j] = 2;
                    }else {
                        dp[i][j] = 1;
                    }
                }else {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1],dp[i+1][j]);
                    }
                }
            }
        }

        return dp[0][s.length()-1];
    }

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("cbbd"));
    }
}
