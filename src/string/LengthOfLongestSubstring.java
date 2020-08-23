package string;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 分别为 i j
 * j一直往下 遇到有相同的 就将i更新一下位置，在更新位置的时候，
 * map中存储的是重复值的最新地址，这时候比较一下 i 和出现重复的值的下一位大小，并取较大值
 * 1.i后出现了这个值，这时候属于出现重复，
 * 2.或者在i前面出现了，i不必要挪
 * 总得来说就是维护一个窗口，当遇到重复值的时候，看看i是否包含了这个值
 */
public class LengthOfLongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        int res = 0;
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0,j = 0 ; j < s.length() ; j++){
            if (map.containsKey(s.charAt(j))){
                i = Math.max(i,map.get(s.charAt(j)) + 1);
            }
            map.put(s.charAt(j),j);
            res = Math.max(res,j-i+1);
        }

        return res;
    }
}
