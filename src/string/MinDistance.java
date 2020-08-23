package string;

/**
 * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 *
 *  
 *
 * 示例：
 *
 * 输入: "sea", "eat"
 * 输出: 2
 * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
 */
public class MinDistance {
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        if (m * n == 0){
            return m+n;
        }
        int[][] dp = new int[m][n];

        if (word2.charAt(0) == word1.charAt(0)){
            dp[0][0] = 1;
        }
        for (int i = 1 ; i < m ; i++){
            if (word2.charAt(0) == word1.charAt(i)){
                dp[i][0] = 1;
            }else {
                if (dp[i -1][0] == 1) {
                    dp[i][0] = 1;
                }else {
                    dp[i][0] = 0;
                }
            }
        }

        for (int i = 1 ; i < n ; i++){
            if (word1.charAt(0) == word2.charAt(i)){
                dp[0][i] = 1;
            }else {
                if (dp[0][i-1] == 1) {
                    dp[0][i] = 1;
                }else {
                    dp[0][i] = 0;
                }
            }
        }


        for (int i = 1 ; i < m ; i++){
            for (int j = 1 ; j < n ; j++){
                if (word1.charAt(i) == word2.charAt(j)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }

        return m + n  - 2 * dp[m-1][n-1];
    }

    /**
     * "park"
     * "spake"
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(minDistance("a","ab"));
    }
}
