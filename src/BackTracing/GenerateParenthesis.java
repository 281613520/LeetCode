package BackTracing;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    private void backtrack(List<String> res ,String tmp , int left , int right,int max){
        if (tmp.length() == max * 2){
            res.add(tmp);
            return;
        }

        if (left < max){
            backtrack(res, tmp+"(", left+1, right, max);
        }
        if (right < left) {
            backtrack(res, tmp + ")", left, right + 1, max);
        }
    }


}
