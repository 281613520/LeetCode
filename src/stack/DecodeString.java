package stack;


import java.util.Stack;

/**
 * 394. 字符串解码
 * <p>
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * 示例:
 * <p>
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 */
public class DecodeString {
    public static String decodeString(String s) {
        String res = "";

        Stack<String> stringStack = new Stack<>();
        Stack<Integer> numsStack = new Stack<>();
        int nums = 0;

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                nums = nums * 10 + s.charAt(i) - '0';
            } else if (s.charAt(i) == '[') {
                numsStack.push(nums);
                stringStack.push(res);
                nums = 0;
                res = new String("");
            } else if ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                res += s.charAt(i);
            } else if (s.charAt(i) == ']') {
                int k = numsStack.pop();

                String cur = stringStack.pop();
                String tmp = "";
                while (k > 0) {
                    tmp = tmp + res;
                    k--;
                }
                res = cur + tmp;
            }

        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(decodeString("3[a2[c]]"));
    }
}
