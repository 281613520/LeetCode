package zifuchuan;


import java.util.*;

/**
 * 336. 回文对
 * 给定一组 互不相同 的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：["abcd","dcba","lls","s","sssll"]
 * 输出：[[0,1],[1,0],[3,2],[2,4]]
 * 解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 * 示例 2：
 *
 * 输入：["bat","tab","cat"]
 * 输出：[[0,1],[1,0]]
 * 解释：可拼接成的回文串为 ["battab","tabbat"]
 *
 * 利用回文串特点
 * s1+s2为回文
 * 1.s1.length == s2.length
 * 2.s1.length < s2.length s lls  s2的前半段回文  后半段是s1的反转
 * 2.s1.length > s2.length   s1 后半段回文  前半段是 s2的反转
 *
 * 比如例1中 lls 和s lls 前半段是回文 这样只要找后面的反转即可 sssll 中  ss回文  可以找到lls sss回文 但是找不到ll
 * 反转的字符串  直接将源字符串反转 存在map中即可
 */
public class PalindromePairs {
    Map<String,Integer> reverseWordLocation = new HashMap<>();
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0 ; i < words.length ; i++) {
            reverseWordLocation.put(new StringBuilder(words[i]).reverse().toString(),i);
        }


        for (int i = 0 ; i < words.length ; i++){
            String cur = words[i];
            int length = cur.length();
            if (length == 0) continue;
            // 分析字符串的中回文的部分是哪块  需要前后半段的判断
            for (int j = 0 ; j <= length ; j++){
                // 判断 后半段 所以是 i，res
                if (isPalindrome(cur, j, length - 1)) {
                    int leftId = findWord(cur, 0, j - 1);
                    if (leftId != -1 && leftId != i) {
                        res.add(Arrays.asList(i, leftId));
                    }
                }
                //判断前半段 所以是 res，i
                if (j != 0 && isPalindrome(cur, 0, j - 1)) {
                    int rightId = findWord(cur, j, length - 1);
                    if (rightId != -1 && rightId != i) {
                        res.add(Arrays.asList(rightId, i));
                    }
                }
            }
        }
        return res;

    }

    public boolean isPalindrome(String s, int left, int right) {
        int len = right - left + 1;
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(left + i) != s.charAt(right - i)) {
                return false;
            }
        }
        return true;
    }

    public int findWord(String s, int left, int right) {
        return reverseWordLocation.getOrDefault(s.substring(left, right + 1), -1);
    }
}
