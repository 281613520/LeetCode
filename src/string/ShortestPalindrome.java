package string;

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
 * 构成回文串 s+s1
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
}
