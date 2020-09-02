package string;

import java.util.Arrays;

/**
 * 214. 最短回文串
 * <p>
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "aacecaaa"
 * 输出: "aaacecaaa"
 * 示例 2:
 * <p>
 * 输入: "abcd"
 * 输出: "dcbabcd"
 *
 *
 * 构成回文串 回文串为 s' + s
 * 只要找到 s中前x个字符串，能够构成回文，再降剩余的反转，加到前面来
 * 但是这样子时间肯定是不够的，因为一个个判断太慢了
 * 就先将整体反转  加在s后面
 * 这样就能很快的找了
 * i从s的末尾开始，j也是s的末尾，只要 （0,i）和（j，last）相同，就找到了这个回文串 然后再正常拼接即可
 *
 */

//todo 明天写一下思路
public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        String ss = s + new StringBuilder(s).reverse().toString();
        for (int i = s.length(), j = i; i > 0; i--, j++) {
            if (ss.substring(0, i).equals(ss.substring(j))) {
                return new StringBuilder(s.substring(i)).reverse().append(s).toString();
            }
        }
        return "";
    }


    //kmp算法
    public String shortestPalindrome2(String s) {
        int n = s.length();
        int[] fail = new int[n];
        Arrays.fill(fail, -1);
        for (int i = 1; i < n; ++i) {
            int j = fail[i - 1];
            while (j != -1 && s.charAt(j + 1) != s.charAt(i)) {
                j = fail[j];
            }
            if (s.charAt(j + 1) == s.charAt(i)) {
                fail[i] = j + 1;
            }
        }
        int best = -1;
        for (int i = n - 1; i >= 0; --i) {
            while (best != -1 && s.charAt(best + 1) != s.charAt(i)) {
                best = fail[best];
            }
            if (s.charAt(best + 1) == s.charAt(i)) {
                ++best;
            }
        }
        String add = (best == n - 1 ? "" : s.substring(best + 1));
        StringBuffer ans = new StringBuffer(add).reverse();
        ans.append(s);
        return ans.toString();
    }
}
