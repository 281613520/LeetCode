package string;

/**
 *给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 * 示例 1:
 *
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 * 示例 2:
 *
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 */
public class CountSubstrings {
    int res = 0;
    public int countSubstrings(String s) {
        if(s == null || s.length() == 0)
            return 0;

        for (int i = 0 ; i < s.length() ; i++){
            help(i,i,s);
            help(i,i+1,s);
        }

        return res;

    }

    private void help(int left , int right , String s){
        while(left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            res++;
            left--;
            right++;
        }
    }
}
